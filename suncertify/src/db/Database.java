/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.*;

/**
 * A facade class used to fulfill the DB interface contract and bring together 
 * some of the functionality of the databases worker classes.  Methods in this
 * class call methods in the worker classes and pass in information from the 
 * view and controller.
 * 
 * 
 * @author matthewfarley
 */
public class Database implements DB {
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
    
    //The following methods are only used to fulfil the DB interface contract!
    public String[] read(int recNo) throws RecordNotFoundException{
        return new String[0];
    }
    
    public void update(int recNo, String[] data, long lockCookie)
            throws RecordNotFoundException, SecurityException{
    }
    
    public void delete(int recNo, long lockCookie)
            throws RecordNotFoundException, SecurityException{        
    }
    
    public int[] find(String[] criteria){
        return new int[0];
    }
    
    public int create(String[] data) throws DuplicateKeyException{
        return 0;
    }
    
    public long lock(int recNo) throws RecordNotFoundException{
        return 0;
    }
    
    public void unlock(int recNo, long cookie)
            throws RecordNotFoundException, SecurityException{
    }
    
    // methods from this point on are functional and are used in the program.
    
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
}


