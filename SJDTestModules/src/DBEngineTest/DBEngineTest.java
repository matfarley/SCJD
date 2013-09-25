/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEngineTest;

import java.io.*;
import java.util.*;




/**
 *  This class does all the work of the database, instantiating the files,
 *  reading, writing and searching.
 * 
 * @author matthewfarley
 */
public class DBEngineTest {
    private String path;
    private StringBuilder blankRecord = 
            new StringBuilder(Contractor.RECORD_LENGTH);
    private File file;
    private RandomAccessFile dbFile;
    private int dbLength;
    //Map used to store a record of the starting point of each record.
    private Map<String, Long> recordNumbers;
    
    
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
    
    //public xxx getRecords(){}
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
        
        int loopOffset = Contractor.FLAG_LENGTH; 
        
        for(int i = 0; i < fieldLength.length; i ++ ){
            //skips the 2 byte flag in the byte[]
            temp = new String(record, loopOffset, fieldLength[i]);
            temp.trim();
            fields.add(temp);
            loopOffset += fieldLength[i];
        }
        return new Contractor(fields);
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
            contractors.add(temp);
        }
        
        return contractors;
    }

}
