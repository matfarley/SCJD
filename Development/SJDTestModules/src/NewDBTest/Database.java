/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NewDBTest;

import java.io.*;
import java.util.*;
/**
 *
 * @author 90045985
 */
public class Database {
private String path;
    private File file = null;
    
    private RandomAccessFile dbFile = null;
    private StringBuilder blankRecord;
    private Map recordsMap;
    private long length;
     byte[] testRead;
     int offie = 70;
    
    
    
    /** Creates new DBEngineTest */
    public Database(String path){
        super();
        this.path = path;
        try{
            
            file = new File(path);
            
            
            
            dbFile = new RandomAccessFile(file, "rw");
            
            length = dbFile.length();
           
            
        }catch(IOException ioe){
            System.out.println("Exception at line 30"+ ioe);
        } //figure out what to do with exceptions later
        
    }
    
    public void readFile(){
        try{
            System.out.println("Offset length: " + offie);
            System.out.println("Single record");
            byte[] record = new byte[184];
            
            dbFile.seek(offie);
            dbFile.readFully(record);
            String recordString = new String(record);
            System.out.println(recordString);
            dbFile.seek(offie + record.length);
            dbFile.readFully(record);
            recordString = new String(record);
            System.out.println(recordString);
            dbFile.seek(offie + record.length + record.length);
            dbFile.readFully(record);
            recordString = new String(record);
            System.out.println(recordString);
            dbFile.seek(offie + record.length + record.length + record.length);
            dbFile.readFully(record);
            recordString = new String(record);
            System.out.println(recordString);
             dbFile.seek(offie + record.length + record.length + record.length + record.length);
            dbFile.readFully(record);
            recordString = new String(record);
            System.out.println(recordString);
              dbFile.seek(offie + record.length + record.length + record.length + record.length + record.length);
            dbFile.readFully(record);
            recordString = new String(record);
            System.out.println(recordString);
            
            System.out.println("Full dB");
            dbFile.seek(0);
            testRead = new byte[(int)dbFile.length()];
            
            dbFile.readFully(testRead, 0, testRead.length);
            String recordTest2 = new String(testRead);
            System.out.println("record = " + recordTest2);
            

            
            
           
        }catch(IOException ioe){
              System.out.println("Exception at line 39"+ ioe);
        }
        
    }
    
}


