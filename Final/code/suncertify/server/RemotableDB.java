/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import db.Contractor;
import db.DuplicateKeyException;
import db.RecordNotFoundException;
import db.DatabaseException;
import db.SearchMode;
import java.rmi.*;
import java.util.*;
import java.util.regex.PatternSyntaxException;
/**
 *  Allows a remote database to be used with RMI.  only the methods outlined in
 *  this interface can be called remotely.  All Methods must throw a 
 * RemoteException
 * 
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */
public interface RemotableDB extends Remote{
    
    // methods from this point on are functional and are used in the program.
    
    
    /**
     * Calls the DBEngine method <code>getAllRecords()</code> and returns a 
     * full list of the database records in an ArrayList.
     * 
     * @return      An ArrayList of Contractor objects
     */     
    public List<Contractor> getAllRecords()throws RemoteException;

    /**
     * Calls the DBEngine method <code>bookContractor()</code> passing in the
     * values the user has entered.
     * 
     * @param key       Values for compound primary key
     * @param customer  number of booking customer or "        " to clear an
     * existing booking
     * @throws RecordNotFoundException 
     */
    public void bookContractor(List<String> key, String customer, long clientID)
            throws RecordNotFoundException, RemoteException, DatabaseException;
    
    
    /**
     * Calls the DBEngine objects <code>deleteRecord</code> method
     * 
     * @param key
     * @throws RecordNotFoundException 
     */
    public void deleteRecord(List<String> key, long clientID)
            throws RecordNotFoundException, RemoteException;
    
    
    /**
     * Calls the overloaded DBEngine method <code>search</code> passing in the
     * search mode and the query entered by the user
     * @param mode      Enum constant.  Determines which contractor attribute to
     *                  search
     * @param query     The regex query as a string
     * @return          An ArrayList of Contractor objects that match the 
     *                  search conditions.
     * @throws java.util.regex.PatternSyntaxException
     */
    public List<Contractor> searchDB(SearchMode mode, String query)
            throws PatternSyntaxException, RemoteException;
    
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
             throws PatternSyntaxException, RemoteException;
    
    /**
     * Adds a new record to the database by calling the DBEngine addContrator
     * method
     * 
     * @param newContractor
     * @throws db.DuplicateKeyException
     */
    public void addRecord(Contractor newContractor, long clientID)
            throws DuplicateKeyException, RemoteException, DatabaseException;
   
}
