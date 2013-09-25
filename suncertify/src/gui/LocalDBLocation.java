/*
 * DBLocationInput.java
 *
 * Created on 20 September 2013, 8:51 AM
 */

package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author  90045985
 */
public class LocalDBLocation extends javax.swing.JDialog {
private static final String PROPERTIES_FILE_NAME = "suncertify.properties";
    private File file;
    private Config properties;
    private String path;
    private MainWindow view;
    
     /** Creates new form DBLocationInput with access to the MainWindow 
      * object */
    public LocalDBLocation(java.awt.Frame parent, boolean modal, 
            MainWindow view) {
        this(parent, modal);
        this.view = view;   
    }
    
    /** Creates new form DBLocationInput */
    public LocalDBLocation(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadConfig();
    }

    /**
 *
 * Looks for a Config file in the local directory.  If one is found the 
 * path will be loaded into the txtbox.
 */
    void loadConfig(){
        file = new File(PROPERTIES_FILE_NAME);
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
            
            if (!properties.getLocalPath().equals("")){
                txtPath.setText(properties.getLocalPath());
            }
        }
    }
    
    /**
 *
 * Saves the current database location settings from
 * txtPath to a Config object.

 */
    void savConfig(){
        path = txtPath.getText();
       //If a File object hasnt been set, one is created
        if(file == null){
            file = new File(PROPERTIES_FILE_NAME);
        }
        
        //If a Config object hasnt been set, one is created
        if(properties == null){
            properties = new Config();
        }
        
        properties.setLocalPath(path);
        
        //Serializing and saving the Config object
        try{
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
        
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnExit = new javax.swing.JButton();
        lblPath = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        txtPath = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Database Path");

        btnExit.setMnemonic('x');
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblPath.setText("Database Path:");

        lblMessage.setText("Please enter the location of your local database.");
        lblMessage.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setMnemonic('C');
        jButton1.setText("Connect");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(btnExit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lblPath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
            .addComponent(lblMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPath)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(lblMessage))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
    savConfig();
    System.exit(0);
}//GEN-LAST:event_btnExitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if(new File(txtPath.getText()).exists()){
        savConfig();
        view.setPath(path);
        this.dispose();
    }
    else{
        txtPath.setText("");
        lblMessage.setText("The File you entered does not exist.  Please enter the location of your database.");
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LocalDBLocation dialog = new LocalDBLocation(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        //savConfig(); //Need to call this!!
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblPath;
    private javax.swing.JTextField txtPath;
    // End of variables declaration//GEN-END:variables

}
