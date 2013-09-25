/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;

/**
 * Value Class used to represent a contractor record.  The object is passe back
 * and forth between the database and the controller.  
 * Data is all stored as strings to help with database activities
 * 
 * @author matthewfarley
 */
public class Contractor {
    // Variables contain values for the length of each field in bytes.    
    static final int OFFSET_LENGTH = 70;
    //constant values for the valid and deleted flags
    //static final byte FLAG_VALID = 00;
    //static final byte FLAG_DELETED = (byte)0x8000;
    static final int FLAG_LENGTH = 2;
    static final int NAME_LENGTH = 32;
    static final int CITY_LENGTH = 64;
    static final int SPECIALTIES_LENGTH = 64;
    static final int STAFF_LENGTH = 6;
    static final int RATE_LENGTH = 8;
    static final int CUSTOMER_LENGTH = 8;
    static final int RECORD_LENGTH = FLAG_LENGTH + NAME_LENGTH + CITY_LENGTH + 
            SPECIALTIES_LENGTH + STAFF_LENGTH + RATE_LENGTH +  CUSTOMER_LENGTH ;
    
    private boolean isValid;
    private String recordNo;
    private String name;
    private String city;
    private String specialty;
    private String staffNo;
    private String costPerHour;
    private String customer;
    
    
    
    /**
     * Creates a new Contractor instance
     * 
     * @param isValid       Flag, is the record valid, if not it is deleted
     * @param recordNo      Record no.  
     * @param name          Name of contractor.
     * @param city          City of operation
     * @param specialty     Contractors specialty
     * @param staffNo       Number of staff
     * @param costPerHour   ...
     * @param customer      id number of the booking customer
     */
    public Contractor(String name, String city, String specialty, 
            String staffNo, String costPerHour, String customer){
        super();
//    public Contractor(boolean isValid, String recordNo, String name, 
//            String city, String specialty, String staffNo, String costPerHour,
//            String customer)
        //Ignoring the flag values until I figure out how to evaluate the bytes
        //this.isValid = isValid;
        //this.recordNo = recordNo;
        this.name = name;
        this.city = city;
        this.specialty = specialty;
        this.staffNo = staffNo;
        this.costPerHour = costPerHour;
        this.customer = customer;
    }
    
    /**
     * Creates a new Contractor instance.  Calls existing constructor
     * 
     * @param fields        An arrayList containing field contents as Strings
     */
    public Contractor(List<String> fields){
        this(fields.get(0), fields.get(1), fields.get(2), fields.get(3) , 
                fields.get(4), fields.get(5) );
    }
    

    /**
     * 
     * @return isValid    Flag, is the record valid, if not it is deleted
     */
    public boolean isValid(){
        return isValid;
    }
    
    /**
     * 
     * @return recordNo    The Contractors record no. in the database.
     */
    public String getRecordNo(){
        return recordNo;
    }
    
     /**
     * 
     * @return Name   Contractors name.
     */
    public String getName(){
        return name;
    }
    
     /**
     * 
     * @return city    City of operation
     */
    public String getCity(){
        return city;
    }
    
    
    /**
     * 
     * @return specialty     Contractors specialty
     */
    public String getSpecialty(){
        return specialty;
    }
    
     /**
     * 
     * @return staffNo    Number of staff
     */
    public String getStaffNo(){
        return staffNo;
    }
    
        /**
     * 
     * @return costPerHour   ...
     */
    public String getCostPerHour(){
        return costPerHour;
    }
     
    /**
     * 
     * @return customer    id number of the booking customer
     */
    public String getCustomer(){
        return customer;
    }
    
    /**
     * 
     * @param isValid    Flag, is the record valid, if not it is deleted
     */
    public void setIsValid(boolean isValid){
        this.isValid = isValid;
    }
    
    /**
     * 
     * @param recordNo    The Contractors record no. in the database.
     */
    public void setRecordNo(String recordNo){
        this.recordNo = recordNo;
    }
    
     /**
     * 
     * @param name   Contractors name.
     */
    public void setName(String name){
        this.name = name;
    }
    
     /**
     * 
     * @param city    City of operation
     */
    public void setCity(String city){
        this.city = city;
    }
    
    /**
     * 
     * @param specialty     Contractors specialty 
     */
    public void setSpecialty(String specialty){
        this.specialty = specialty;
    }
    
     /**
     * 
     * @param staffNo    Number of staff
     */
    public void setStaffNo(String staffNo){
        this.staffNo = staffNo;
    }
    
     /**
     * 
     * @param costPerHour   ...
     */
    public void setCostPerHour(String costPerHour){
        this. costPerHour = costPerHour;
    }
     
    /**
     * 
     * @param customer    id number of the booking customer
     */
    public void setCustomer(String customer){
        this.customer = customer;
    }
}
