/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import db.*;
import java.rmi.RemoteException;
import java.util.*;
import java.util.regex.*;
import server.RemotableDB;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

/**
 * This class works as an intermediary between the view and the data portions 
 * of the program, packaging search results sets into TableModels so that they
 * can be displayed and taking search terms and packaging them into regex 
 * statements
 * 
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */
public class ViewController {
    private Mode mode;
    private String path;
    private int port;
    private long clientID;
    private RemotableDB db = null;

    /**
     * Creates a new ViewController.  Requires a port even for the local 
     * database, but us only used if the flag is Networked.
     * 
     * @param path      Database location
     * @param port      Server port
     * @param mode      Mode of operation
     * @throws RemoteException
     */
    public ViewController(String path, int port, Mode mode)
            throws RemoteException, NotBoundException, DatabaseException, 
            MalformedURLException{
        this.path = path;
        this.port = port;
        this.mode = mode;
        if(this.mode == Mode.DIRECT){
            //Instantiate a connector and retrieva a local database connection
            
            db = new LocalConnector(path).getDatabase();
            //db = new Database(path);
            
        }
        else if(this.mode == Mode.NETWORKED) {
            //Instantiate a connector and retrieva a local database connection
            db = new NetworkConnector(path, port).getDatabase();
        }
    }
    
    /**
     * Calls the database's <code>getAllRecords()</code> method and converts 
     * returned ArrayList into a TableModel that the MainWindow can use.
     * 
     * @return Table Model
     * @throws RemoteException;
     */
    public ContractorTableModel getContractors() throws RemoteException{
        List<Contractor> temp = db.getAllRecords();
        ContractorTableModel tableModel = new ContractorTableModel();
        for(Contractor c : temp){
            tableModel.addContractorRecord(c);
        }
        return tableModel;
    }
    
    //accepts info from the view and turns it into a contractor object
    public Contractor createContractor(){
        return null;
    }
    
    /**
     * packages user input from the view into a list and sends it to the 
     * database.  Takes a contractor name and city to be used as a compound
     * primary key in the database.
     * 
     * @param name          Name of contractor
     * @param city          City of operation
     * @param customerNo    id number for booking customer or "        " to 
     * cancel a booking
     * @throws RecordNotFoundException 
     * @throws RemoteException
     */
    public void bookContractor(String name, String city, String customerNo)
            throws RecordNotFoundException, RemoteException, DatabaseException{
        db.bookContractor(Arrays.asList(name, city), customerNo, clientID);
    }
    
    /**
     * Accepts user search terms for either a name or location field.
     * calls the overloaded Database.search method passing in the user's search
     * term and a mode flag, either NAME or LOCATION.  The returned result is
     * then converted into a new ContractorTableModel and sent back to 
     * the calling class.
     * 
     * @param mode      Enum constant.  Determines which contractor attribute to
     *                  search
     * @param query     The regex query as a string
     * @return          ContractorTableModel
     * @throws java.util.regex.PatternSyntaxException
     * @throws RemoteException;
     */
    public ContractorTableModel searchDB(SearchMode mode, String query)
            throws PatternSyntaxException, RemoteException{
        List<Contractor> temp = db.searchDB(mode, query);
        ContractorTableModel tableModel = new ContractorTableModel();
        
        for(Contractor c : temp){
            tableModel.addContractorRecord(c);
        }
        return tableModel;
    }
    
    /**
     * Accepts user search terms for a name and location.
     * calls the overloaded Database.search method passing in the user's search
     * terms.  The returned result is  then converted into a new 
     * ContractorTableModel and sent back to the calling class.
     * 
     * @param nameQuery         The regex query in the name field as a string
     * @param locationQuery     The regex query in the city field as a string
     * @return                  ContractorTableModel
     * @throws                  PatternSyntaxException
     * @throws RemoteException
     */
    public ContractorTableModel searchDB(String nameQuery, String locationQuery )
             throws PatternSyntaxException, RemoteException {
        List<Contractor> temp = db.searchDB(nameQuery, locationQuery);
        ContractorTableModel tableModel = new ContractorTableModel();
        
        for(Contractor c : temp){
            tableModel.addContractorRecord(c);
        }
        return tableModel;
    }
    
    /**
     * Receives information from the view and packages it in to a new contractor
     * object.  The new contractor is then passed to the Database.addRecord
     * method
     * 
     * @param name
     * @param city
     * @param specialties
     * @param staffNo
     * @param rate
     * @param customer
     * @throws db.DuplicateKeyException
     * @throws RemoteException
     */
    public void addRecord(String name, String city, String specialties, 
            String staffNo, String rate, String customer)
            throws DuplicateKeyException, RemoteException, DatabaseException{
        Contractor newContractor = new Contractor(Contractor.VALID_FLAG, name, 
                city, specialties, staffNo, rate, "");
        db.addRecord(newContractor, clientID);
    }
    
    /**
     * Takes user input from the gui and packages it up so as to call the 
     * databases delete method
     * 
     * @param name
     * @param city
     * @throws RecordNotFoundException 
     * @throws RemoteException
     */
    public void deleteRecord(String name, String city) 
            throws RecordNotFoundException, RemoteException{
        db.deleteRecord(Arrays.asList(name, city), clientID);
    } 
}
