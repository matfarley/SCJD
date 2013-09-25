/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.table.AbstractTableModel;
import java.util.*;
import db.*;

/**
 *Table Model used to display data from the database.  Accepts Contractor 
 *objects from the controller and displays them.
 *
 * @author matthewfarley
 */
public class ContractorTableModel extends AbstractTableModel {
    private String[] headerNames = new String[]{"Record No.", "Name", "City", 
        "No. Staff", "Cost Per Hour", "Booking Customer no."};
    private ArrayList<String[]> contractorRecords = new ArrayList<String[]>();
    
    
    /**
    * Returns the column count of the table.
    *
    * @return An integer indicating the number or columns in the table.
    */ 
    public int getColumnCount(){
        return headerNames.length;
    }    
    
    /**
    * Returns the number of rows in the table.
    *
    * @return An integer indicating the number of rows in the table.
    */
    public int getRowCount(){
        return this.contractorRecords.size();
    }
    
     /**
     * Gets a value from a specified index in the table.
     *
     * @param row An integer representing the row index.
     * @param column An integer representing the column index.
     * @return The object located at the specified row and column.
     */
    public Object getValueAt(int row, int column) {
        String[] rowValues = this.contractorRecords.get(row);
        return rowValues[column];
    }
    
    
    /**
     * Sets the cell value at a specified index.
     *
     * @param obj The object that is placed in the table cell.
     * @param row The row index.
     * @param column The column index.
     */
    public void setValueAt(Object obj, int row, int column) {
        Object[] rowValues = this.contractorRecords.get(row);
        rowValues[column] = obj;
    }

    
     /**
     * Returns the name of a column at a given column index.
     *
     * @param column The specified column index.
     * @return A String containing the column name.
     */
    public String getColumnName(int column) {
        return headerNames[column];
    }

    /**
    * CAdds a contractor object to the table.
    * 
    * @param isValid       Flag, is the record valid, if not it is deleted
    * @param recordNo      Record no.  
    * @param name          Name of contractor.
    * @param city          City of operation
    * @param staffNo       Number of staff
    * @param costPerHour   ...
    * @param customer      id number of the booking customer
    */
    public void addContractorRecord(String recordNo, String name, 
            String city, String staffNo, String costPerHour, String customer){
        
        String[] temp = new String[]{recordNo, name, city, staffNo, costPerHour,
            customer};
        this.contractorRecords.add(temp);
        }
  
    /**
    * Overloaded method.  Accepts a <code>Contractor<code/> object and calls the
    * other <code>addContractorRecord()<code/> method 
    * 
    * @param contractor        A Contractor value object.
    */
    public void addContractorRecord(Contractor contractor){
        addContractorRecord(contractor.getRecordNo(), contractor.getName(), 
                contractor.getCity(), contractor.getStaffNo(), 
                contractor.getCostPerHour(), contractor.getCustomer());
    }   
}
