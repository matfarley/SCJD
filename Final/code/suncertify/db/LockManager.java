/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.util.*;

/**
 * Manages the record locking for the database.  Locks the DBEngine while
 * reading and writing is occurring to the database
 * 
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */
public class LockManager {
    private Map<List<String>, Long> lockRegister;
    private DBEngine db;  // Reference to the DBEngine object used for locking
    
    /**
     * Creates a new LockManager object
     */
    public LockManager(DBEngine db){
        lockRegister = new HashMap<List<String>, Long>();
        this.db = db;
    }
    
    /**
     * Locks the record and DBEngine object if a record is considered to be 
     * unlocked.  Uses wait until a record is available and then executes code.
     * 
     * @param key
     * @param clientID
     */
    public void lockRecord(List<String> key, long clientID){
        synchronized(db){
            while(isLocked(key, clientID)){
                try{
                    db.wait(5*1000);
                }catch(InterruptedException ie){
                    System.out.println(ie);
                }
                
            }
            //Code to lock record
            //Used to create an unmodifiable list, so that the hascode wont change
            String name = key.get(0);
            String location = key.get(1);
            lockRegister.put(Collections.unmodifiableList(
                    Arrays.asList(name, location)), new Long(clientID));
        }
    }
     
    /**
     * calls the <code>lockRecord(List<String> key, long clientID)</code> 
     * passing in the values from a Contractor object
     * 
     * @param contractor
     * @param clientID
     */
    public void lockRecord(Contractor contractor, long clientID){
        lockRecord(Arrays.asList(contractor.getName(), contractor.getCity()),
                clientID);
     }
    
    /**
     * Unlocks a contractor record by removing it from the Map.
     * 
     * @param key
     * @param clientID
     */
    public void unlockRecord(List<String> key, long clientID){
        //If a client has the lock on a record
        if(lockRegister.containsKey(key) && lockRegister.get(key) == clientID ){
            synchronized(db){
                lockRegister.remove(key);
                db.notifyAll();
            }
        }
    }
    
    /**
     * calls the <code>unlockRecord(List<String> key, long clientID)</code> 
     * passing in the values from a Contractor object
     * 
     * @param contractor
     * @param clientID
     */
    public void unlockRecord(Contractor contractor, long clientID){
        unlockRecord(Arrays.asList(contractor.getName(), contractor.getCity()),
                clientID);
    }
    
    /**
     * Checks the map of locked records for a clientID.  if a record is not in 
     * the map it is not locked.  If a record is in the Map and the clientID
     * matches that stored with the key then that record is treated as unlocked 
     * because the client already has the lock.  If the record is in the Map and
     * the id does not match that stored then the record is locked.
     * @param key
     * @param clientID
     * @return
     */
    private boolean isLocked(List<String> key, long clientID){
        if(lockRegister.containsKey(key)){
            if(lockRegister.get(key) == clientID){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }    
    }

}
