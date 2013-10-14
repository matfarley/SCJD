/*
 * DBLocationInput.java
 *
 * Created on 20 September 2013, 8:51 AM
 */

package gui;

import java.io.*;
import db.*;
import server.Server;
import java.rmi.RemoteException;

    

/**
 * Server for the database
 * 
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */
public class ServerWindow extends javax.swing.JDialog {
    private File file;
    private Config properties;
    private String path;
    private int port;
    private Server server = null;
 
    
    /** Creates new form DBLocationInput 
    */
    public ServerWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadConfig();
    }
    
    /**
    *
    * Looks for a Config file in the local directory.  If one is found the port 
    * and path will be loaded into the txtboxes.
    */
    void loadConfig(){
        file = new File(Config.PROPERTIES_FILE_NAME);
        if(file.exists()){     
            try{
                //Loading Config
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                properties = (Config)ois.readObject();
                ois.close();
                fis.close();
            }catch(IOException ioe){
            // code for handling exception
                System.out.println("Error loading config\n" + ioe);
            }
            catch(Exception e){
            // code for handling exception
                System.out.println("Error loading config\n" + e);
            }
            
            if (!properties.getServerPath().equals("")){
                txtPath.setText(properties.getServerPath());
            }
            
            if(properties.getServerPort() >= 0 ){
                txtPort.setText(String.valueOf(properties.getServerPort()));
            }
        }        
    }
    
    /**
    *
    * Saves the current database location and port settings from
    * txtPath and txtPort to a Config object.
    */
    void savConfig(){
        path = txtPath.getText();
        
        if(!txtPort.getText().equals("")){
            port = Integer.parseInt(txtPort.getText());
        }
        
        //If a File object hasnt been set, one is created
        if(file == null){
            file = new File(Config.PROPERTIES_FILE_NAME);
        }
        //If a Config object hasnt been set, one is created
        if(properties == null){
            properties = new Config();
        }
        
        properties.setServerPath(path);
        properties.setServerPort(port);
        
        try{
            //Serializing and saving the Config object
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(properties);
            oos.close();
            fos.close();
            
        }catch(IOException ioe){
            //Exception Handling HERE
            System.out.println("Error saving config\n" + ioe);
        }
        catch(Exception e){
            // code for handling exception
            System.out.println("Error saving config\n" + e);
        }
    }
    
 /**
 * Method tests whether the <code>port</code> is valid
 * 
 * @param port      Port to be used in networking
 * @return boolean is the port valid or not         
 */
     public boolean isValidPort(String port){
        if(port.length()> 4 || port.equals("")){
            return false;
        }
        else{
            try{
            Integer.parseInt(port);
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

        dlgServerMsg = new javax.swing.JOptionPane();
        btnExit = new javax.swing.JButton();
        lblPath = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        txtPath = new javax.swing.JTextField();
        btnStart = new javax.swing.JButton();
        lblPort = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Server");

        btnExit.setMnemonic('x');
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblPath.setText("Database Path:");

        lblMessage.setText("Please enter the location of your database.");
        lblMessage.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnStart.setMnemonic('S');
        btnStart.setText("Start Server");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        lblPort.setText("Server Port:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStart)
                        .addGap(18, 18, 18)
                        .addComponent(btnExit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPath)
                            .addComponent(lblPort))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPath)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPort)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(btnStart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(lblMessage))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
    try{
        savConfig();
        server.close();
        System.exit(0);
    }catch(Exception e){
        lblMessage.setText("Error closing connection");
        System.exit(0);
    }
    
}//GEN-LAST:event_btnExitActionPerformed

private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
    if(!(new File(txtPath.getText()).isFile()) && !isValidPort(txtPort.getText())){
        txtPath.setText("");
        txtPort.setText("");
        lblMessage.setText("The file and port you entered are invalid.  Please enter the location of your database.");
     }
     else if(!(new File(txtPath.getText()).isFile())){
        txtPath.setText("");
        lblMessage.setText("The file you entered does not exist.  Please enter the location of your database.");
    }
     else if(!isValidPort(txtPort.getText())){
        txtPort.setText("");
        lblMessage.setText("You must enter a valid port.  Please enter the location of your database.");
    }
     //Server runs here!
    else{
        savConfig();
        try{
            server = new Server(path, port);
            lblMessage.setText("Server running.  Waiting on port:" + port);
            txtPath.setEditable(false);
            txtPort.setEditable(false);
            btnStart.setEnabled(false);
        }catch(Exception e){
            dlgServerMsg.showMessageDialog(this,e);
        }
     }
     
}//GEN-LAST:event_btnStartActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final ServerWindow dialog = new ServerWindow(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        dialog.savConfig();
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnStart;
    private javax.swing.JOptionPane dlgServerMsg;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblPath;
    private javax.swing.JLabel lblPort;
    private javax.swing.JTextField txtPath;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables

}
