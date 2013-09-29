/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.*;
import java.util.*;


/**
 *  This class does all the work of the database, instantiating the files,
 *  reading, writing and searching.  Methods are Synchronized on the database
 * source file
 * 
 * @author matthewfarley
 */
public class DBEngine {
    private static short DELETE_FLAG = (short)0x8000;
    private static short VALID_FLAG = (short)00;
    
    private String path;
    private StringBuilder blankRecord = 
            new StringBuilder(Contractor.RECORD_LENGTH);
    private File file;
    private RandomAccessFile dbFile;
    private long dbLength;
    //List containing all database records as Contractor objects
    private List<Contractor> dbRecords;
    //Map used to store a record of the starting point of each record.
    private Map<List<String>, Long> recordLocations = 
            new HashMap<List<String>, Long>(); 
    
    
    /** Creates new DBEngine 
     *  
     * @param path      The location of the database file
     */
    public DBEngine(String path){
        this.path = path;
        try{
            file = new File(path);
            dbFile = new RandomAccessFile(file, "rw");
            dbLength = dbFile.length();
            dbRecords = getAllRecords();
            
        }catch(IOException ioe){
            System.out.println("IOException\n" + ioe);
        }//figure out how to handle exceptions later.  Throw Database Exception?
    }
    
   
    //addNewRecord(){
    //  needs to add an identifier and location to Map
    //  location will be the current length of the file before writing.
    //  dbLength will need to be updated
    //}
    
    


    /**
     * Finds a single record in a Collection of records
     * 
     * @param key   Key representing record in Map of file locations
     * @return      long representing location of record in database
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
     * Checks to see if a record is valid and then writes a customer number to
     * the correct position in the database file.
     * 
     * @param key
     * @param customer
     * @throws RecordNotFoundException 
     */
    public void bookContractor(List<String> key, String customer)
            throws RecordNotFoundException{
        long offset = getLocation(key);
        //Deleted files are not sent to the view, so shouldnt be selectable, 
        // but are checked just in case.
        
        //Use offset to read the file - get the record and then check the flag
        //if the file is deleted throw a RecordNotFoundException.
        
        //Contractor recordToCheck = convertRecord(readRecord(offset));
        //check flag!
        
        //else...
        offset += (Contractor.RECORD_LENGTH - Contractor.CUSTOMER_LENGTH);
        write(offset, customer);

    }
    
    
    // public Contractor getSingleRecord()
//    
//}
    // 
    
    
    
    
    /**
     * Most basic level of reading the database file.  Reads a single record at
     * a certain position and returns the record as a byte[].
     * 
     * @param offset      The starting point of the record
     * @return byte[]     A single record in a byte[]
     */
    public byte[] readRecord(long offset){
        
        //use if statement to check if records are null.  If so then use
        
        // if records are not null then get the offset(offset in record)
        // from the map
        //need to check that it isn't end of file!
        
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
     * Basic method that writes to the database.  can accept a whole record or 
     * an individual field i.e customer no.
     * 
     * @param location      Location in file as a long
     * @param data        String representation of a record or field.
     */
    public void write(long location, String data){
        try{
            synchronized(dbFile){
                dbFile.seek(location);
                dbFile.write(data.getBytes());
            }
        }catch(IOException ioe){
             System.out.println("Exception while writing records\n" +
                    " to database file\n" + ioe);
        }
    } 
    

    
    /**
     * Changes the flag for a record indicating that it is either deleted or 
     * valid.
     * 
     * @param location          Location in file
     * @param flag              2 byte flag valid or deleted.
     */
    public void writeFlag(long location, byte[] flag){
        try{
            synchronized(dbFile){
                dbFile.seek(location);
                dbFile.write(flag);
            }
        }catch(IOException ioe){
                System.out.println("Exception while writing flag\n" +
                    " to database file\n" + ioe);
        }
    }
    
    
        //working! - will replace write flag?
    public void delete(long location){
        try{
            synchronized(dbFile){
                dbFile.seek(location);
                dbFile.writeShort(DELETE_FLAG);
                //dbFile.writeShort(VALID_FLAG);
            }
        }catch(IOException ioe){
             System.out.println("Exception while deleting records\n" +
                    " to database file\n" + ioe);
        }
    } 
    
    
    /**
     * Converts a <code>byte[]</code> "record" into a populated Contractor 
     * object.
     * 
     * @param record            The <code>byte[]</code> read from the dbFile.
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
     * @return List<Contractor>     Each record of the database as a Contractor 
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
            
            //Must check Flag at this point!
            // All records are added to the map but only records that are vaild
            // are sent to the view!
            
            //Adds contractor object to the List of valid contractors
            contractors.add(temp);
        }
        return contractors;
    }


}
