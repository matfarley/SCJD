/*
 * MainWindow.java
 *
 * Created on 20 September 2013, 8:50 AM
 */

package gui;

import java.util.Random;
import java.text.*;
import db.*;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.util.regex.*;
/**
 * Provides the Main viewing window and database display for the application.
 * Most of the programs exceptions are propagated up to this class, which 
 * handles most of the exceptions by displaying dialog boxes.
 * 
 * Various dialog boxes are produced to take user input before invoking methods
 * on the view controller which packages data sent and data received by the 
 * MainWindow.
 * 
 * 
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */
public class MainWindow extends javax.swing.JFrame {
    private Mode mode;
    private String path;
    private int port;
    private long clientID;
    private ContractorTableModel tableData;
    private ViewController controller;
    
    public MainWindow(){
        initComponents();
    }
    
    /** Creates new form MainWindow */
    public MainWindow(String path, int port, Mode mode) throws NotBoundException {
        this();
        try{
            this.toFront();
            genClientID();
            this.mode = mode;
            controller = new ViewController(path, port, mode);
            tableData = controller.getContractors();
            setUpTable();
            setVisible(true);
        }catch(RemoteException re){
            dlgWarning.showMessageDialog(this, 
                        "There was a problem connecting to the remote " +
                        "database\n" + re, "RemoteException Thrown",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
        }catch(DatabaseException re){
            dlgWarning.showMessageDialog(this, 
                        "There was a problem connecting to the database\n " + re,
                        "DatabaseException Thrown",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception re){
            dlgWarning.showMessageDialog(this, 
                        "There was a problem connecting to the database\n " + re,
                        "Exception Thrown",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /** 
     * @param path      Path for database
     */
    public void setPath(String path){
        this.path = path;
    }
     
    /** 
     * @param port      Port for networking
     */
    public void setPort(int port){
        this.port = port;
    }
    
    /**
     * 
     * @param mode      Mode of operation. NETWORKED or DIRECT
     */
    public void setMode(Mode mode){
        this.mode = mode;
    }
    
     /** 
     * Generates a random number to be used as a Client ID when connecting to
     * the server.
     */
    private void genClientID(){
        Random r = new Random();
        clientID = r.nextLong();
    }
    
    /**
     * Used to help with jTable construction
     */
    private void setTableColumnSize(){
        javax.swing.table.TableColumn column = null;
        int tableSize = tblDisplay.getModel().getColumnCount();
        
        for (int i = 0; i < tableSize; i++) {
            switch(i){
                case 0:
                    column = tblDisplay.getColumnModel().getColumn(i);
                    column.setPreferredWidth(Contractor.NAME_LENGTH);
                    break;
                case 1:
                    column = tblDisplay.getColumnModel().getColumn(i);
                    column.setPreferredWidth(Contractor.CITY_LENGTH);
                    break;
                case 2:
                    column = tblDisplay.getColumnModel().getColumn(i);
                    column.setPreferredWidth(Contractor.SPECIALTIES_LENGTH);
                    break;
                case 3:
                    column = tblDisplay.getColumnModel().getColumn(i);
                    column.setPreferredWidth(Contractor.STAFF_LENGTH);
                    break;
                case 4:
                    column = tblDisplay.getColumnModel().getColumn(i);
                    column.setPreferredWidth(Contractor.RATE_LENGTH);
                    break;
                case 5:
                    column = tblDisplay.getColumnModel().getColumn(i);
                    column.setPreferredWidth(Contractor.CUSTOMER_LENGTH);
                    break;    
            }
        }
    }
    
    /**
     * Adds a table model to the jTable.  Method can be used to refresh the
     * table after the model has been updated
     */
    private void setUpTable(){
        this.tblDisplay.setModel(tableData);
        setTableColumnSize();
    }
    
    /**
     * Used to test user input for text fields
     * 
     * @param textToTest
     * @param fieldLength
     * @return boolean
     */
    private boolean isValidText(String textToTest, int fieldLength){
        if(textToTest.equals("")){
            
            return false;
        }
       else if(textToTest.length() > fieldLength){
            return false;
        }
       else{
           return true; 
        }
    }
    
    /**
     * Used to test user input for whole number fields
     * 
     * @param intToTest
     * @param fieldLength
     * @return boolean
     */
    private boolean isValidInt(String intToTest, int fieldLength){
        if(intToTest.equals("") || intToTest.length() > fieldLength ){
            return false;
        }
        else{
           try{
               Integer.parseInt(intToTest);
               return true;
               
           }catch(NumberFormatException nfe){
               return false;
           }
        }   
    }
    
    /**
     * Used to test user input for fields that take a decimal number.
     * 
     * @param doubleToTest
     * @param fieldLength
     * @return boolean
     */
    private boolean isValidDouble(String doubleToTest, int fieldLength){
        if(doubleToTest.equals("") || doubleToTest.length() > fieldLength ){
            return false;
        }
        else{
           try{
               Double.parseDouble(doubleToTest);
               return true;
               
           }catch(NumberFormatException nfe){
               return false;
           }
        }   
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgWarning = new javax.swing.JOptionPane();
        dlgBookContractor = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        txtCustomerNo = new javax.swing.JTextField();
        lblCustNoMessage = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        dlgAddRecord = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        txtAddName = new javax.swing.JTextField();
        txtAddCity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtAddStaffNo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtAddHourlyRate = new javax.swing.JTextField();
        btnSubmitNewRecord = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAddSpecialty = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDisplay = new javax.swing.JTable();
        txtSearchName = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnBook = new javax.swing.JButton();
        chkLocation = new javax.swing.JCheckBox();
        chkName = new javax.swing.JCheckBox();
        btnShowAll = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchLocation = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        btnClearSearch = new javax.swing.JButton();
        btnClearBooking = new javax.swing.JButton();

        dlgBookContractor.setTitle("Book Contractor");

        jLabel3.setText("Customer No.");

        lblCustNoMessage.setText("Enter an 8 digit customer number");
        lblCustNoMessage.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSubmit.setMnemonic('S');
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlgBookContractorLayout = new javax.swing.GroupLayout(dlgBookContractor.getContentPane());
        dlgBookContractor.getContentPane().setLayout(dlgBookContractorLayout);
        dlgBookContractorLayout.setHorizontalGroup(
            dlgBookContractorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgBookContractorLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCustomerNo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnSubmit)
                .addContainerGap(111, Short.MAX_VALUE))
            .addComponent(lblCustNoMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        dlgBookContractorLayout.setVerticalGroup(
            dlgBookContractorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgBookContractorLayout.createSequentialGroup()
                .addGap(0, 48, Short.MAX_VALUE)
                .addGroup(dlgBookContractorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCustomerNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubmit))
                .addGap(27, 27, 27)
                .addComponent(lblCustNoMessage))
        );

        dlgAddRecord.setTitle("Add Record");

        jLabel4.setText("Contractor Name:");

        jLabel5.setText("Contractor City:");

        jLabel6.setText("Contractor Specialty:");

        jLabel7.setText("Contractor Staff No.:");

        jLabel8.setText("Hourly Rate:  $");

        btnSubmitNewRecord.setMnemonic('u');
        btnSubmitNewRecord.setText("Submit");
        btnSubmitNewRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitNewRecordActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtAddSpecialty.setColumns(20);
        txtAddSpecialty.setLineWrap(true);
        txtAddSpecialty.setRows(5);
        jScrollPane2.setViewportView(txtAddSpecialty);

        javax.swing.GroupLayout dlgAddRecordLayout = new javax.swing.GroupLayout(dlgAddRecord.getContentPane());
        dlgAddRecord.getContentPane().setLayout(dlgAddRecordLayout);
        dlgAddRecordLayout.setHorizontalGroup(
            dlgAddRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgAddRecordLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(dlgAddRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dlgAddRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dlgAddRecordLayout.createSequentialGroup()
                            .addGroup(dlgAddRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(dlgAddRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2)
                                .addComponent(txtAddCity)
                                .addComponent(txtAddName)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dlgAddRecordLayout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAddStaffNo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAddHourlyRate)))
                    .addGroup(dlgAddRecordLayout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(btnSubmitNewRecord)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        dlgAddRecordLayout.setVerticalGroup(
            dlgAddRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgAddRecordLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(dlgAddRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAddName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(dlgAddRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAddCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(dlgAddRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dlgAddRecordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtAddStaffNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtAddHourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSubmitNewRecord)
                .addGap(52, 52, 52))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("All About Improvement Ltd.");

        tblDisplay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblDisplay);

        btnSearch.setMnemonic('s');
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnAdd.setMnemonic('A');
        btnAdd.setText("Add Record");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnBook.setMnemonic('b');
        btnBook.setText("Book Selected");
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });

        btnShowAll.setMnemonic('o');
        btnShowAll.setText("Show All Records");
        btnShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAllActionPerformed(evt);
            }
        });

        jLabel1.setText("Name");

        jLabel2.setText("Location");

        btnDelete.setMnemonic('D');
        btnDelete.setText("Delete Record");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClearSearch.setMnemonic('c');
        btnClearSearch.setText("Clear Fields");
        btnClearSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSearchActionPerformed(evt);
            }
        });

        btnClearBooking.setMnemonic('l');
        btnClearBooking.setText("Clear Booking");
        btnClearBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearBookingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 410, Short.MAX_VALUE)
                        .addComponent(btnClearBooking)
                        .addGap(18, 18, 18)
                        .addComponent(btnBook)
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel1))
                            .addComponent(chkLocation)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chkName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSearchLocation)
                                    .addComponent(txtSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnClearSearch)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 349, Short.MAX_VALUE)
                        .addComponent(btnShowAll)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSearch)
                                .addComponent(btnClearSearch))
                            .addComponent(chkName))
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearchLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkLocation)))
                    .addComponent(btnShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnClearBooking)
                    .addComponent(btnBook))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        //Search by both name and location
        if(chkName.isSelected() && chkLocation.isSelected()){
            //Checking to see if text is entered
            if (txtSearchName.getText().equals("") ||
                    txtSearchLocation.getText().equals("")){
                dlgWarning.showMessageDialog(this, 
                        "Please enter a search argument in the text box",
                        "No Argument in Search Field",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            //The Actual Work of the Method is done in this block
            else{
                try{
                    tableData = controller.searchDB(txtSearchName.getText(), 
                            txtSearchLocation.getText());
                    setUpTable();
                }catch(PatternSyntaxException pse){
                    dlgWarning.showMessageDialog(this, 
                            "Please enter a valid java regex statement",
                            "Incorrect Search Syntax",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                    txtSearchName.setText("");
                    txtSearchLocation.setText("");
                }
                catch(RemoteException re){
                    dlgWarning.showMessageDialog(this, re);
                }
            }
        }
        //Search by name only
        else if(chkName.isSelected() && !chkLocation.isSelected()){
            //Checking to see if text is entered
            if (txtSearchName.getText().equals("")){
                dlgWarning.showMessageDialog(this, 
                        "Please enter a search argument in the text box",
                        "No Argument in Search Field",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            //if Name is selected but there is text in the location box
            else if(!txtSearchLocation.getText().equals("")){
                dlgWarning.showMessageDialog(this, 
                        "You have entered an argument in the location field but have" +
                        " not selected\n that field as a search parameter." +
                        "\n\nPlease clear the field or check the check box.",
                        "No Argument in Search Field",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
        }
            //The Actual Work of the Method is done in this block
            else{
                try{
                    tableData = controller.searchDB(SearchMode.NAME, 
                        txtSearchName.getText());
                    setUpTable();
                }catch(PatternSyntaxException pse){
                    dlgWarning.showMessageDialog(this, 
                            "Please enter a valid java regex statement",
                            "Incorrect Search Syntax",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                    txtSearchName.setText("");
                }
                catch(RemoteException re){} //Handle this!!!!
            }
        }
        //Search by location only
        else if(!chkName.isSelected() && chkLocation.isSelected()){
            
            //Checking to see if text is entered
            if (txtSearchLocation.getText().equals("")){
                dlgWarning.showMessageDialog(this, 
                "Please enter a search argument in the text box",
                "No Argument in Search Field",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            //Location is selected and there is text in the name box
             else if(!txtSearchName.getText().equals("")){
                dlgWarning.showMessageDialog(this, 
                "You have entered an argument in the name field but have" +
                " not selected\n that field as a search parameter." +
                "\n\nPlease clear the field or check the check box.",
                "No Argument in Search Field",javax.swing.JOptionPane.ERROR_MESSAGE);
        }
            //The Actual Work of the Method is done in this block
            else{
                try{
                    tableData = controller.searchDB(SearchMode.LOCATION, 
                        txtSearchLocation.getText());
                    setUpTable();
                }catch(PatternSyntaxException pse){
                    dlgWarning.showMessageDialog(this, 
                            "Please enter a valid java regex statement",
                            "Incorrect Search Syntax",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                    txtSearchLocation.setText("");
                }
                catch(RemoteException re){
                    dlgWarning.showMessageDialog(this, re);
                }
            }
        }
        else{
            //If no check box is selected the show warning dialog.
             dlgWarning.showMessageDialog(this, 
                "Please select a check box next to the field(s) you want to search",
                "No Search Field Selected",javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    //Check if a record is selected!
        if(tblDisplay.getSelectedRow() != -1){
            int confirm = dlgWarning.showConfirmDialog(this,"Are you sure you " +
                    "want to delete this record?", "Confirm Deletion", 
                    javax.swing.JOptionPane.YES_NO_OPTION);
            
            if(confirm == javax.swing.JOptionPane.YES_OPTION){
                try{
                    controller.deleteRecord(tblDisplay.getValueAt(
                    tblDisplay.getSelectedRow(), 0).toString(), 
                    tblDisplay.getValueAt(tblDisplay.getSelectedRow(),1).toString());
                    tableData = controller.getContractors();
                    setUpTable();
                }catch(RecordNotFoundException rnfe){
                     dlgWarning.showMessageDialog(this, 
                         "Record could not be found in the database",
                         "Record Not Found",
                          javax.swing.JOptionPane.ERROR_MESSAGE);
                     txtSearchLocation.setText("");
                }catch(RemoteException re){} //Handle this!!!!
            }
            //if didnt want to delete
            else{
                return ;
            }
        }
        else{
            //joptionPane - please select a row
            dlgWarning.showMessageDialog(this, 
                    "Please select a Contractor from the table",
                    "No Record Selected",javax.swing.JOptionPane.ERROR_MESSAGE);
        }  
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAllActionPerformed
        try{
            tableData = controller.getContractors();
            setUpTable();
            txtSearchName.setText("");
            txtSearchLocation.setText("");
            chkName.setSelected(false);
            chkLocation.setSelected(false);
        }catch(RemoteException re){
            dlgWarning.showMessageDialog(this, re);
        }
    }//GEN-LAST:event_btnShowAllActionPerformed

private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookActionPerformed

    //Check if a record is selected!
    if(tblDisplay.getSelectedRow() != -1){
        dlgBookContractor.setSize(450, 125);
        dlgBookContractor.setVisible(true);
    }
    else{
        //joptionPane - please select a row
        dlgWarning.showMessageDialog(this, 
                "Please select a Contractor from the table",
                "No Record Selected",javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_btnBookActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        //opens a dialog to accept the new record details   
        dlgAddRecord.setSize(429, 294);
        txtAddName.setText("");
        txtAddCity.setText("");
        txtAddSpecialty.setText("");
        txtAddStaffNo.setText("");
        txtAddHourlyRate.setText("");
        dlgAddRecord.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        
    // Make sure id is 8 chars long and is a digit or blank.  
    //when booking you must enter a number
    if(txtCustomerNo.getText().length() == 8){
            try{
                //use parseInt() to check that the text is a number.
                int tempCustNo = Integer.parseInt(txtCustomerNo.getText());
                controller.bookContractor(
                            tblDisplay.getValueAt(tblDisplay.getSelectedRow(), 0).toString(), 
                            tblDisplay.getValueAt(tblDisplay.getSelectedRow(), 1).toString(), 
                            String.valueOf(tempCustNo));
                tableData = controller.getContractors();
                setUpTable();
                //Clear text so it doesn't appear next time a record is booked
                txtCustomerNo.setText(""); 
                dlgBookContractor.dispose();
            
            }catch(NumberFormatException nfe){
                txtCustomerNo.setText("");
                lblCustNoMessage.setText("Please enter a valid 8 digit "
                        + "customer no.");
            }catch(RecordNotFoundException rnfe){
                dlgWarning.showMessageDialog(this, 
                        rnfe.getMessage(),
                        "Record Not Found",javax.swing.JOptionPane.ERROR_MESSAGE);
            }catch(RemoteException re){
                dlgWarning.showMessageDialog(this, 
                        re.getMessage(),"Remote Exception",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }catch(DatabaseException dbe){
                dlgWarning.showMessageDialog(this, 
                        dbe.getMessage(),"Remote Exception",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }  
        }
        else{
            txtCustomerNo.setText("");
            lblCustNoMessage.setText("Please enter a valid 8 digit"
                    + " customer no.");
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

private void btnClearSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSearchActionPerformed
    txtSearchName.setText("");
    txtSearchLocation.setText("");
    chkName.setSelected(false);
    chkLocation.setSelected(false);
}//GEN-LAST:event_btnClearSearchActionPerformed

private void btnSubmitNewRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitNewRecordActionPerformed
    // Testing user input
    if(!isValidText(txtAddName.getText(), Contractor.NAME_LENGTH)){
         dlgWarning.showMessageDialog(this, "The contractor name must be less "
                 + "than or equal to " + Contractor.NAME_LENGTH + " characters"
                 + " long and cannot be blank", "Invalid Contractor Name",
                 javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    else if(!isValidText(txtAddCity.getText(), Contractor.CITY_LENGTH)){
        dlgWarning.showMessageDialog(this, "The contractor city must be less "
                 + "than or equal to " + Contractor.CITY_LENGTH + " characters"
                 + " long and cannot be blank", "Invalid Contractor City",
                 javax.swing.JOptionPane.ERROR_MESSAGE);
         txtAddCity.setText("");
    }
    else if(!isValidText(txtAddSpecialty.getText(), 
            Contractor.SPECIALTIES_LENGTH)){
        dlgWarning.showMessageDialog(this, "The contractor specialty must be "
                + "less than or equal to " + Contractor.SPECIALTIES_LENGTH + 
                " characters long and cannot be blank", "Invalid Contractor City",
                 javax.swing.JOptionPane.ERROR_MESSAGE);
        txtAddSpecialty.setText("");
    }
    else if(!isValidInt(txtAddStaffNo.getText(), Contractor.STAFF_LENGTH)){
         dlgWarning.showMessageDialog(this, "The staff number must be a whole "
                 + "number less than or equal to " + Contractor.STAFF_LENGTH +
                 " digits long and cannot be blank", "Invalid Staff No",
                 javax.swing.JOptionPane.ERROR_MESSAGE);
         txtAddStaffNo.setText("");
    }
    else if(!isValidDouble(txtAddHourlyRate.getText(), Contractor.RATE_LENGTH)){
         dlgWarning.showMessageDialog(this, "The hourly rate must be a decimal "
                 + "number less than or equal to " + Contractor.RATE_LENGTH +
                 " digits long and cannot be blank", "Invalid Hourly Rate",
                 javax.swing.JOptionPane.ERROR_MESSAGE);
         txtAddHourlyRate.setText("");
    }
    else{
        try{ //Do the work here
            DecimalFormat df = new DecimalFormat("#.00");
            controller.addRecord(txtAddName.getText().trim(), 
                    txtAddCity.getText().trim(), txtAddSpecialty.getText().trim(), 
                    txtAddStaffNo.getText().trim(), new String("$" + 
                    df.format(Double.parseDouble(txtAddHourlyRate.getText().trim()))), "");
            tableData = controller.getContractors();
            setUpTable();
            dlgAddRecord.dispose();
            
        }catch(DuplicateKeyException dke){
             dlgWarning.showMessageDialog(this, dke.getMessage(),
                        "Duplicate Primary Key", javax.swing.JOptionPane.ERROR_MESSAGE);
        }catch(RemoteException re){
             dlgWarning.showMessageDialog(this, 
                        re.getMessage(),"Remote Exception",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
        }catch(DatabaseException dbe){
                dlgWarning.showMessageDialog(this, 
                        dbe.getMessage(),"Database Exception",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }  
    }
}//GEN-LAST:event_btnSubmitNewRecordActionPerformed

private void btnClearBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearBookingActionPerformed
    //Check if a record is selected!
    if(tblDisplay.getSelectedRow() != -1){
        try{
            controller.bookContractor(
                    tblDisplay.getValueAt(tblDisplay.getSelectedRow(), 0).toString(), 
                    tblDisplay.getValueAt(tblDisplay.getSelectedRow(), 1).toString(), 
                    "        ");
            tableData = controller.getContractors();
            setUpTable();
            }catch(RecordNotFoundException rnfe){
                dlgWarning.showMessageDialog(this, 
                        rnfe.getMessage(),
                        "Record Not FOund",javax.swing.JOptionPane.ERROR_MESSAGE);
            }catch(RemoteException re){
                dlgWarning.showMessageDialog(this, 
                        re.getMessage(),"Remote Exception",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch(DatabaseException dbe){
                dlgWarning.showMessageDialog(this, 
                        dbe.getMessage(),"Remote Exception",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            } 
    }
    else{
        dlgWarning.showMessageDialog(this, 
                "Please select a Contractor from the table",
                "No Record Selected",javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_btnClearBookingActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnClearBooking;
    private javax.swing.JButton btnClearSearch;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnShowAll;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnSubmitNewRecord;
    private javax.swing.JCheckBox chkLocation;
    private javax.swing.JCheckBox chkName;
    private javax.swing.JDialog dlgAddRecord;
    private javax.swing.JDialog dlgBookContractor;
    private javax.swing.JOptionPane dlgWarning;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCustNoMessage;
    private javax.swing.JTable tblDisplay;
    private javax.swing.JTextField txtAddCity;
    private javax.swing.JTextField txtAddHourlyRate;
    private javax.swing.JTextField txtAddName;
    private javax.swing.JTextArea txtAddSpecialty;
    private javax.swing.JTextField txtAddStaffNo;
    private javax.swing.JTextField txtCustomerNo;
    private javax.swing.JTextField txtSearchLocation;
    private javax.swing.JTextField txtSearchName;
    // End of variables declaration//GEN-END:variables

}
