/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.*;
import java.util.regex.*;
import server.RemotableDB;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * A facade class used to fulfill the DB interface contract and bring together 
 * some of the functionality of the databases worker classes.  Methods in this
 * class call methods in the worker classes and pass in information from the 
 * view and controller.
 * 
 * 
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */
public class Database extends UnicastRemoteObject implements DB, RemotableDB { 
    private String path;
    private DBEngine db;
    private LockManager lockManager;
    
     /**
     * Creates a new Database object
     */
    public Database()throws RemoteException{
    }
    
    /**
     * Creates a new Database object
     * @param path
     */
    public Database(String path) throws RemoteException, DatabaseException{
        this.path = path;
        db = new DBEngine(path);
        lockManager = new LockManager(db);
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
     * @throws RemoteException
     */     
    public List<Contractor> getAllRecords() throws RemoteException{
       return db.getAllRecords();
    }

    /**
     * Calls the DBEngine method <code>bookContractor()</code> passing in the
     * values the user has entered.
     * 
     * @param key       Values for compound primary key
     * @param customer  number of booking customer or "        " to clear an
     * existing booking
     * @param clientID  The random number generated when the client object was 
     * created.  Used for record locking
     * @throws RecordNotFoundException
     * @throws RemoteException
     */
    public void bookContractor(List<String> key, String customer, long clientID)
            throws RecordNotFoundException, RemoteException, DatabaseException{
        lockManager.lockRecord(key, clientID);
        db.bookContractor(key, customer);
        lockManager.unlockRecord(key, clientID);
    }
    
    /**
     * Calls the DBEngine objects <code>deleteRecord</code> method
     * 
     * @param key
     * @param clientID  The random number generated when the client object was 
     * created.  Used for record locking
     * @throws RecordNotFoundException
     * @throws RemoteException
     */
    public void deleteRecord(List<String> key, long clientID)
            throws RecordNotFoundException, RemoteException{
        lockManager.lockRecord(key, clientID);
        db.deleteRecord(key);
        lockManager.unlockRecord(key, clientID);
    }

    /**
     * Calls the overloaded DBEngine method <code>search</code> passing in the
     * search mode and the query entered by the user
     * @param mode      Enum constant.  Determines which contractor attribute to
     *                  search
     * @param query     The regex query as a string
     * @return          An ArrayList of Contractor objects that match the 
     *                  search conditions.
     * @throws java.util.regex.PatternSyntaxException
     * @throws RemoteException
     */
    public List<Contractor> searchDB(SearchMode mode, String query)
            throws PatternSyntaxException, RemoteException{
        return db.search(mode, query);
    }
    
    /**
     * Calls the overloaded DBEngine method <code>search</code> passing in the
     * name and location queries entered by the user
     * 
     * @param nameQuery         The regex query in the name field as a string
     * @param locationQuery     The regex query in the city field as a string
     * @return                  An ArrayList of Contractor objects that match 
     *                          the search conditions.
     * @throws                  PatternSyntaxException
     */
    public List<Contractor>searchDB(String nameQuery, String locationQuery )
             throws PatternSyntaxException, RemoteException {
        return db.search(nameQuery, locationQuery); 
    }
    
    /**
     * Adds a new record to the database by calling the DBEngine addContrator
     * method
     * 
     * @param newContractor
     * @param clientID  The random number generated when the client object was 
     * created.  Used for record locking
     * @throws db.DuplicateKeyException
     * @throws RemoteException
     */
    public void addRecord(Contractor newContractor, long clientID)
            throws DuplicateKeyException, RemoteException, DatabaseException{
        lockManager.lockRecord(newContractor, clientID);
        db.addRecord(newContractor);
        lockManager.unlockRecord(newContractor, clientID);
    }
    
}


