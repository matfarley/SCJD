/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 *  This class does all the work of the database, instantiating the files,
 *  reading, writing and searching.  Methods are Synchronized on the database
 * source file
 * 
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */
public class DBEngine {
    private static short DELETE_FLAG = (short)0x8000;
    private static short VALID_FLAG = (short)00;
    
    private String path;
    //Blank existingRecord does not include the flag which is written to the file as
    //a short.
    public String blankRecord = 
            new String(new byte[(Contractor.RECORD_LENGTH - Contractor.FLAG_LENGTH)]);  
    private File file;
    private RandomAccessFile dbFile;
    private long dbLength;
    //List containing all database records as Contractor objects
    private List<Contractor> dbRecords;
    //Map used to store a existingRecord of the starting point of each existingRecord.
    private Map<List<String>, Long> recordLocations = 
            new HashMap<List<String>, Long>(); 
    
    
    /** Creates new DBEngine 
     *  
     * @param path      The location of the database file
     */
    public DBEngine(String path)throws DatabaseException{
        this.path = path;
        try{
            file = new File(path);
            dbFile = new RandomAccessFile(file, "rw");
            dbLength = dbFile.length();
            dbRecords = getAllRecords();
            
        }catch(IOException ioe){
            throw new DatabaseException("Error Connecting to Database");          
        }
    }

    /**
     * Finds a single existingRecord in a Collection of records
     * 
     * @param key   Key representing existingRecord in Map of file locations
     * @return      long representing location of existingRecord in database
     * @throws RecordNotFoundException 
     */
    public long getLocation(List<String> key) throws RecordNotFoundException{
        if(recordLocations.containsKey(key)){
            return recordLocations.get(key);
        }
        else{
            throw new RecordNotFoundException(
                    "Records composite key of Name + City not found in database");
        }
    }
    
    /**
     * Builds new existingRecord to be written to database using methods of a
     * method local inner class.  The inner class was used because it fixes the 
     * problem of where to put the  offset and newRecord variables and solves 
     * the problem of initializing  and resetting them for each record
     *  
     * @param newContractor
     * @return record String
     */
    public String buildRecord(Contractor newContractor){
        
        class Buildit{ //Local class used to add a field to the string
            public StringBuilder newRecordString = new StringBuilder(blankRecord);;
            int offset = 0;
            
            public void writeRecord(String data, int fieldLength){
                newRecordString.replace(offset, (offset + data.length()), data);
                offset += fieldLength;
            }
        }
        
         Buildit b = new Buildit();
         b.writeRecord(newContractor.getName(), Contractor.NAME_LENGTH);
         b.writeRecord(newContractor.getCity(), Contractor.CITY_LENGTH);
         b.writeRecord(newContractor.getSpecialty(), Contractor.SPECIALTIES_LENGTH);
         b.writeRecord(newContractor.getStaffNo(), Contractor.STAFF_LENGTH );
         b.writeRecord(newContractor.getCostPerHour(), Contractor.RATE_LENGTH );
         return b.newRecordString.toString();        
    }
    
    /**
     * Uses <code>getLocation</code> and <code>writeFlag</code> methods to find
     * a record in the database and write a flag to indicate that it is deleted
     * 
     * @param key
     * @throws RecordNotFoundException 
     */
    public void deleteRecord(List<String> key)throws RecordNotFoundException{
        long location = getLocation(key);
        writeFlag(location, DELETE_FLAG);
    }
    
    /**
     * Adds a new contractor record to the database.  If the given primary key
     * (Name + City) match an existing record, the existing record is checked
     * for validity.  If it is valid an exception is thrown, if it is marked
     * deleted it is overwritten.
     * 
     * @param newContractor
     * @throws db.DuplicateKeyException
     */
    public void addRecord(Contractor newContractor)
            throws DuplicateKeyException, DatabaseException{
        long location = 0;
        //extracts the primary key from the object to check if it already exists
        List<String> key = Arrays.asList(newContractor.getName(),
                newContractor.getCity());
        
        //Check if existingRecord exists
        if (recordLocations.containsKey(key)){
            
            /*check if the existingRecord is deleted.  If deleted then overwrite the 
             *deleted existingRecord with the new one!  If not deleted then throw a new
             * DuplicateKeyException
             */
            location = recordLocations.get(key);
            Contractor existingRecord = 
                    convertRecord(readRecord(recordLocations.get(key)));
            if(existingRecord.isValid()){
                throw new DuplicateKeyException("The contractor "
                        + newContractor.getName() + " already exists in " +
                        newContractor.getCity());
            }
            else{
                /* continue building existingRecord, overwriting the deleted version
                 * Add existingRecord to position of old record marked deleted in
                 * the file. This is because 2 records cannot exist with the
                 * same primary key */
                
                //write the flag first as a 2 byte short
                writeFlag(location, VALID_FLAG );
                //write record string after the flag.
                write((location + Contractor.FLAG_LENGTH),
                        buildRecord(newContractor));
                //Overall file length remains the same.
            }
        }      
        //If the new record doesn't exist in the list of current records, add it.
        else{
            location = dbLength;
            writeFlag(location, VALID_FLAG );
            write((location + Contractor.FLAG_LENGTH),
                        buildRecord(newContractor));
            //increment end of file 
            dbLength += Contractor.RECORD_LENGTH;
        }
    }
    
    /**
     * Checks to see if a existingRecord is valid and then writes a customer number to
     * the correct position in the database file.
     * 
     * @param key
     * @param customer
     * @throws RecordNotFoundException 
     */
    public void bookContractor(List<String> key, String customer)
            throws RecordNotFoundException, DatabaseException{
        long offset = getLocation(key);
        offset += (Contractor.RECORD_LENGTH - Contractor.CUSTOMER_LENGTH);
        write(offset, customer);
    }
    
    /**
     * Most basic level of reading the database file.  Reads a single existingRecord at
     * a certain position and returns the existingRecord as a byte[].
     * 
     * @param offset      The starting point of the existingRecord
     * @return byte[]     A single existingRecord in a byte[]
     */
    public byte[] readRecord(long offset){
        byte[] temp = new byte[Contractor.RECORD_LENGTH];
            try{
                synchronized(dbFile){
                    dbFile.seek(offset);
                    dbFile.readFully(temp);
                }
            }catch(IOException ioe){
                System.out.println("Exception while reading records\n" +
                    " from database file\n" + ioe);
        }
        return temp;
    }
    
    /**
     * Basic method that writes to the database.  can accept a whole existingRecord or 
     * an individual field i.e customer no.
     * 
     * @param location      Location in file as a long
     * @param data        String representation of a existingRecord or field.
     */
    public void write(long location, String data)throws DatabaseException{
        try{
            synchronized(dbFile){
                dbFile.seek(location);
                dbFile.write(data.getBytes());
            }
        }catch(IOException ioe){
            throw new DatabaseException("Exception while writing records\n" +
                    " to database file\n"); 
        }
    } 

    /**
     * Changes the flag for a record indicating that it is either deleted or 
     * valid.
     * 
     * @param location          Location in file
     * @param flag              2 byte flag valid or deleted.
     */
    public void writeFlag(long location, short flag){
        try{
            synchronized(dbFile){
                dbFile.seek(location);
                dbFile.writeShort(flag);
            }
        }catch(IOException ioe){
                System.out.println("Exception while writing flag\n" +
                    " to database file\n" + ioe);
        }
    }
     
    /**
     * Converts a <code>byte[]</code> "existingRecord" into a populated Contractor 
     * object.
     * 
     * @param existingRecord            The <code>byte[]</code> read from the dbFile.
     * @return Contractor       a populated Contractor object
     */
    public Contractor convertRecord(byte[] record){
        //Contains all the Contractor constants so that the fileds can be 
        //pulled from the byte[] in a loop.
        int[] fieldLength = new int[]{Contractor.NAME_LENGTH, 
            Contractor.CITY_LENGTH, Contractor.SPECIALTIES_LENGTH, 
            Contractor.STAFF_LENGTH, Contractor.RATE_LENGTH, 
            Contractor.CUSTOMER_LENGTH};
        
        List<String> fields = new ArrayList<String>();
        int flag = 0;
        String temp = null;
        
        //skips the 2 byte flag in the byte[] as this converted to an int
        int loopOffset = Contractor.FLAG_LENGTH; 
        
        for(int i = 0; i < fieldLength.length; i ++ ){
            //Takes 2 items from the byte[] and makes a 2 byte int.
            flag = ((int)record[0] << 8) + (record[1] & 0xff);
            temp = new String(record, loopOffset, fieldLength[i]).trim();
            fields.add(temp);
            loopOffset += fieldLength[i];
        }
        //can re-write the constructor to take a boolean for the flag!
        return new Contractor(flag, fields);
    }
    
    /**
     * Runs <code>readRecord()</code> in a loop, passing it in to 
     * <code>convertRecord</code> as an argument, taking 
     * the result and putting it into an <code>ArrayList</code>.
     * The resulting Contractor is then recorded in a Map using the name and
     * city fields as a key to find the location.
     * 
     * @return List<Contractor>     Each existingRecord of the database as a Contractor 
     * object, stored in an ArrayList.
     */
    public List<Contractor> getAllRecords(){
        List<Contractor> contractors = new ArrayList<Contractor>();
        Contractor temp = null;
        
        for(int i = Contractor.OFFSET_LENGTH; i < (int)dbLength; 
                i += Contractor.RECORD_LENGTH){
            temp = convertRecord(readRecord(i));
            
            //adds contractor primary key to the Map of locations in the db file
            recordLocations.put(Collections.unmodifiableList(
                    Arrays.asList(temp.getName(), temp.getCity())),new Long(i));

            if(temp.isValid()){
            //Adds contractor object to the List of valid contractors
            contractors.add(temp);
            }
        }
        return contractors;
    }
    
    /**
     * Calls <code>getAllRecords()</code> and searches through the valid 
     * contractor objects for either a name or a city.  The search mode is
     * determined by the mode flag which is of enum type SearchMode.
     * 
     * @param mode      Enum constant.  Determines which contractor attribute to
     *                  search
     * @param query     The regex query as a string
     * @return          An ArrayList of Contractor objects that match the 
     *                  search conditions.
     * @throws          PatternSyntaxException
     */
    public List<Contractor> search(SearchMode mode, String query)
            throws PatternSyntaxException{
        List<Contractor> results = new ArrayList<Contractor>();
        Pattern p = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        Matcher m = null; 
        
        for(Contractor c : getAllRecords()){
   
            if(mode.equals(SearchMode.NAME)){
                m = p.matcher(c.getName());
            }
            else if(mode.equals(SearchMode.LOCATION)){
                m = p.matcher(c.getCity());
            }
            
            if(m.find()){
                results.add(c);
            }  
        }
        return results;
    }
    
    /**
     * Calls <code>getAllRecords()</code> and searches through the valid 
     * contractor objects for a matching name and city.  
     * 
     * @param nameQuery         The regex query in the name field as a string
     * @param locationQuery     The regex query in the city field as a string
     * @return                  An ArrayList of Contractor objects that match 
     *                          the search conditions.
     * @throws                  PatternSyntaxException
     */
     public List<Contractor> search(String nameQuery, String locationQuery )
             throws PatternSyntaxException {
        List<Contractor> results = new ArrayList<Contractor>();
        Pattern pName = Pattern.compile(nameQuery, Pattern.CASE_INSENSITIVE);
        Pattern pLocation = Pattern.compile(locationQuery, 
                Pattern.CASE_INSENSITIVE);
  
        for(Contractor c : getAllRecords()){
            Matcher mName = pName.matcher(c.getName());
            Matcher mLocation = pLocation.matcher(c.getCity());
            
            if(mName.find() && mLocation.find()){
                results.add(c);
            }    
        }
        return results;
    }
}
