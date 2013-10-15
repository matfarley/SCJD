/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import java.io.Serializable;

/**
 * Value Class used to represent a contractor record.  The object is passe back
 * and forth between the database and the controller.  
 * Data is all stored as strings to help with database activities
 * 
 * @author matthewfarley
 */
public class Contractor implements Serializable {
    // Variables contain values for the length of each field in bytes.    
    public static final int OFFSET_LENGTH = 70;
    //constant values for the valid and deleted flags
    public static final int DELETED_FLAG = 0x8000;
    public static final int VALID_FLAG = 00;
    public static final int FLAG_LENGTH = 2;
    public static final int NAME_LENGTH = 32;
    public static final int CITY_LENGTH = 64;
    public static final int SPECIALTIES_LENGTH = 64;
    public static final int STAFF_LENGTH = 6;
    public static final int RATE_LENGTH = 8;
    public static final int CUSTOMER_LENGTH = 8;
    public static final int RECORD_LENGTH = FLAG_LENGTH + NAME_LENGTH + CITY_LENGTH + 
            SPECIALTIES_LENGTH + STAFF_LENGTH + RATE_LENGTH +  CUSTOMER_LENGTH ;
    
    private boolean isValid;
    private String[] identifier; //used to find record position in file
    private String name;
    private String city;
    private String specialty;
    private String staffNo;
    private String costPerHour;
    private String customer;
    
    
    
    /**
     * Creates a new Contractor instance
     * 
     * @param flag          Flag, is the record valid, if not it is deleted 
     * @param name          Name of contractor.
     * @param city          City of operation
     * @param specialty     Contractors specialty
     * @param staffNo       Number of staff
     * @param costPerHour   ...
     * @param customer      id number of the booking customer
     */
    public Contractor(int flag, String name, String city, String specialty, 
            String staffNo, String costPerHour, String customer){
        super();
        
        //Checking flag
        if(flag == VALID_FLAG){
            this.isValid = true;
        }
        else if(flag == DELETED_FLAG){ 
            this.isValid = false;
        }
        
        this.identifier = new String[]{name, city};
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
    public Contractor(int flag, List<String> fields){
        this(flag, fields.get(0), fields.get(1), fields.get(2), fields.get(3) , 
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
     * @return identifier    The Contractors unique identifier
     */
    public String[] getIdentifier(){
        return identifier;
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
     * @param identifier    The Contractors unique identifier
     */
    public void setIdentifier(String name, String city){
        this.identifier = new String[]{name, city};
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
