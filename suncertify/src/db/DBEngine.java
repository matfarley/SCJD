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
    private String path;
    private StringBuilder blankRecord = 
            new StringBuilder(Contractor.RECORD_LENGTH);
    private File file;
    private RandomAccessFile dbFile;
    private int dbLength;
    //List containing all database records as Contractor objects
    private List<Contractor> dbRecords;
    //Map used to store a record of the starting point of each record.
    private Map<String, Long> recordNumbers; //is there a record no or a compound primary key?
    
    
    /** Creates new DBEngine 
     *  
     * @param path      The location of the database file
     */
    public DBEngine(String path){
        this.path = path;
        try{
            file = new File(path);
            dbFile = new RandomAccessFile(file, "rw");
            dbLength = (int)dbFile.length();
            dbRecords = getAllRecords();
            
        }catch(IOException ioe){
            System.out.println("IOException\n" + ioe);
        }//figure out how to handle exceptions later.  Throw Database Exception?
    }
    
    
    //public xxx buildRecordNumbers();
    //addToRecordNumbers()
    
    /**
     * Most basic level of reading the database file.  Reads a single record at
     * a certain position and returns the record as a byte[].
     * 
     * @param offset      The starting point of the record
     * @return byte[]     A single record in a byte[]
     */
    public byte[] readRecord(int offset){
        
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
        
        String temp = null;
        
        //skips the 2 byte flag in the byte[]
        int loopOffset = Contractor.FLAG_LENGTH; 
        
        for(int i = 0; i < fieldLength.length; i ++ ){
            temp = new String(record, loopOffset, fieldLength[i]).trim();
            fields.add(temp);
            loopOffset += fieldLength[i];
        }
        return new Contractor(fields);
    }
    
    
    /**
     * Runs <code>readRecord()</code> in a loop, passing it in to 
     * <code>convertRecord</code> as an argument, taking 
     * the result and putting it into an <code>ArrayList</code.
     * 
     * @return List<Contractor>     Each record of the database as a Contractor 
     * object, stored in an ArrayList.
     */
    public List<Contractor> getAllRecords(){
        List<Contractor> contractors = new ArrayList<Contractor>();
        Contractor temp = null;
        
        for(int i = Contractor.OFFSET_LENGTH; i < dbLength; 
                i += Contractor.RECORD_LENGTH){
            temp = convertRecord(readRecord(i));
             //calls addToRecordNumbers() when creating, adding location to Map.
            contractors.add(temp);
        }
        return contractors;
    }


}
