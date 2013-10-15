/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

/**
 *
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */
public class AppLauncher {
    
    /**
     * Takes command line arguments and depending on the argument, launches
     * the application in the appropriate mode.  The in stand alone or 
     * networking mode, a modal JDialog is launched on top of the main window 
     * to accept a database location.  in server mode the JDialog accepts 
     * the database location and waits for a connection
     * 
     * @param args. Command line arguments are used to
     * launch the different operating modes for the program.
     * "server" launches the server application.  "alone" launches the 
     * application in stand alone mode.  No argument launches the application
     * in networked mode.
     * 
     */
    public static void main(String[] args) {
        if(args.length == 0){
            NetworkDBLocation nw = new NetworkDBLocation(new javax.swing.JFrame(),
                true);
            nw.setVisible(true);
        }
        else if(args[0].equalsIgnoreCase("alone")){
             LocalDBLocation loc = 
                new LocalDBLocation(new javax.swing.JFrame(), true);
             loc.setVisible(true);
        }
        else if(args[0].equalsIgnoreCase("server")){
             ServerWindow sw = new ServerWindow(new javax.swing.JFrame(), true);       
             sw.setVisible(true);
        }
    }
}
