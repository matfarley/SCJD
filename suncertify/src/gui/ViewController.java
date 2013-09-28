/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import db.*;
import java.util.*;

/**
 * This class works as an intermediary between the view and the data portions 
 * of the program, packaging search results sets into TableModels so that they
 * can be displayed and taking search terms and packaging them into regex 
 * statements
 * 
 * @author matthewfarley
 */
public class ViewController {
    private Mode mode;
    private String path;
    private int port;
    private long clientID;
    //May have a problem here if the ServerDatabase object has too much extra
    //functionality - it will only have database functionality
    private Database db = null;
    //private Server Database object = null;
    
     
    /**
     * Creates a new ViewController.  Requires a port even for the local 
     * database, but us only used if the flag is Networked.
     * 
     * @param path      Database location
     * @param port      Server port
     * @param mode      Mode of operation
     */
    public ViewController(String path, int port, Mode mode){
        this.path = path;
        this.port = port;
        this.mode = mode;
        if(this.mode == Mode.DIRECT){
            //Instantiate a connector and retrieva a local database connection
            db = new LocalConnector(path).getDatabase();
            //db = new Database(path);
            
        }
        else{
            //Instantiate a connector and retrieva a local database connection
            db = new NetworkConnector(path, port).getDatabase();
        }
    }
    

    /**
     * Calls the database's <code>getAllRecords()</code> method and converts 
     * returned ArrayList into a TableModel that the MainWindow can use.
     * 
     * @return Table Model
     */
    public ContractorTableModel getContractors(){
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
     */
    public void bookContractor(String name, String city, String customerNo)
            throws RecordNotFoundException{
        db.bookContractor(Arrays.asList(name, city), customerNo);
    }
}
