/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEngineTest;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  This class does all the work of the database, instantiating the files,
 *  reading, writing and searching.
 * 
 * @author matthewfarley
 */
public class DBEngineTest {
    private static short DELETE_FLAG = (short)0x8000;
    private static short VALID_FLAG = (short)00;
    
    private String path;
    private StringBuilder blankRecord = 
            new StringBuilder(Contractor.RECORD_LENGTH);
    private File file;
    private RandomAccessFile dbFile;
    private int dbLength;
    //Map used to store a record of the starting point of each record.
    private Map<List<String>, Long> recordLocation = new HashMap<List<String>, Long>();
    
    
    /** Creates new DBEngine 
     *  
     * @param path      The location of the database file
     */
    public DBEngineTest(String path){
        this.path = path;
        try{
            file = new File(path);
            dbFile = new RandomAccessFile(file, "rw");
            dbLength = (int)dbFile.length();
            
        }catch(IOException ioe){
            System.out.println("IOException\n" + ioe);
        }//figure out how to handle exceptions later.  Throw Database Exception?
    }
    
    public void delete(long location){
        try{
            synchronized(dbFile){
                dbFile.seek(location);
                //dbFile.writeShort(DELETE_FLAG);
                dbFile.writeShort(VALID_FLAG);
            }
        }catch(IOException ioe){
             System.out.println("Exception while deleting records\n" +
                    " to database file\n" + ioe);
        }
    } 
    
    
    //Find records 1 String
    public List<Contractor> search(SearchMode mode, String query){
        List<Contractor> results = new ArrayList<Contractor>();
        Pattern p = Pattern.compile(query);
        Matcher m = null; //Primary matcher used when there is only 1 search term
        Matcher m2 = null; //Used only when searching on both Name and Location
        
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
    
    
    
    //Overloaded find method!!
     public List<Contractor> search(String nameQuery, 
             String locationQuery ){
        List<Contractor> results = new ArrayList<Contractor>();
        Pattern pName = Pattern.compile(nameQuery);
        Pattern pLocation = Pattern.compile(locationQuery);
  
        for(Contractor c : getAllRecords()){
               Matcher mName = pName.matcher(c.getName());
                Matcher mLocation = pLocation.matcher(c.getCity());
      

                if(mName.find() && mLocation.find()){
                    results.add(c);
                }    
        }
        return results;
    }
            
            

    //public xxx buildRecordNumbers();
    
    //trying to get a single record out of the DB.
    public byte[] readRecord(int offset){
        // pass in the offset - when used in a loop start with the offset then
        // increment by RECORD_LENGTH i.e
        //for(i = Contractor.OFFSET_LENGTH;i < dbFile.length; i + 
        //      Contractor.RECORD_LENGTH){
        //    readRecord(i);
        // need to wrap it in a method to return a Contractor object, then add 
        // to an arrayList.
        
        //use if statement to check if records are null.  If so then use
        
        // if records are not null then get the offset(location in record)
        // from the map
        
        byte[] temp = new byte[Contractor.RECORD_LENGTH];
        try{
            dbFile.seek(offset);
            dbFile.readFully(temp);

        }catch(IOException ioe){
            System.out.println("Exception while reading records\n" +
                    " from database file\n" + ioe);
        }
        
        //calls addToRecordNumbers() when creating
        
        return temp;
    }
    
    
    //trying to get byte array into a Contractor record!
    public Contractor convertRecord(byte[] record){
        //Contains all the Contractor constants so that the fileds can be 
        //pulled from the byte[] in a loop.
        int[] fieldLength = new int[]{Contractor.NAME_LENGTH, Contractor.CITY_LENGTH,
           Contractor.SPECIALTIES_LENGTH, Contractor.STAFF_LENGTH, 
            Contractor.RATE_LENGTH, Contractor.CUSTOMER_LENGTH};
        
        //ArrayList length is the same as the number of text feilds.
        List<String> fields = new ArrayList<String>();
        
        String temp = null;
        int tempInt = 0;
        
        int loopOffset = Contractor.FLAG_LENGTH; 
        
        for(int i = 0; i < fieldLength.length; i ++ ){
            //skips the 2 byte flag in the byte[]
            tempInt = ((int)record[0] << 8) + (record[1] & 0xff);
            temp = new String(record, loopOffset, fieldLength[i]);
            temp.trim();
            fields.add(temp);
            loopOffset += fieldLength[i];
        }
        return new Contractor(tempInt,fields);
    }
    
        public List<Contractor> getAllRecords(){
        List<Contractor> contractors = new ArrayList<Contractor>();
        Contractor temp = null;
        //run read record in a loop, passing it in to convert record, takiong 
        //the result and putting it into an arrayList
        
        // pass in the offset - when used in a loop start with the offset then
        // increment by RECORD_LENGTH i.e
        //for(i = Contractor.OFFSET_LENGTH;i < dbFile.length; i + 
        //      Contractor.RECORD_LENGTH){
        //    readRecord(i);
        // need to wrap it in a method to return a Contractor object, then add 
        // to an arrayList.
        
        for(int i = Contractor.OFFSET_LENGTH; i < dbLength; i += 
              Contractor.RECORD_LENGTH){
            temp = convertRecord(readRecord(i));
            recordLocation.put(Collections.unmodifiableList(
                    Arrays.asList(temp.getName(), temp.getCity())),new Long(i));
                    
            contractors.add(temp);
        }
        
        return contractors;
    }
        
        public void getLocation(List<String> key){
            
            if(recordLocation.containsKey(key)){
                System.out.println("found " + recordLocation.get(key));
            }
            else{
                System.out.println("not found");
            }
        }

}


