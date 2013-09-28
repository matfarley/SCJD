/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.*;

/**
 *  Will Eventually implement the DB interface
 * @author matthewfarley
 */
public class Database {
    private String path;
    private DBEngine db;
    
     /**
     * Creates a new Database object
     */
    public Database(){
    }
    
    /**
     * Creates a new Database object
     * @param path
     */
    public Database(String path){
        this.path = path;
        db = new DBEngine(path);
    }
    
    /**
     * Calls the DBEngine method <code>getAllRecords()</code> and returns a 
     * full list of the database records in an ArrayList.
     * 
     * @return      An ArrayList of Contractor objects
     */     
    public List<Contractor> getAllRecords(){
       return db.getAllRecords();
    }

    
    /**
     * Calls the DBEngine method <code>bookContractor()</code> passing in the
     * values the user has passed into the view.
     * 
     * @param key       Values for compound primary key
     * @param customer  number of booking customer or "        " to clear an
     * existing booking
     * @throws RecordNotFoundException 
     */
    public void bookContractor(List<String> key, String customer)
            throws RecordNotFoundException{
        db.bookContractor(key, customer);
        
    }
    
    //bookContractor
    
}


