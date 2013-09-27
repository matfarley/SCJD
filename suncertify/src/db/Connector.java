/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

/**
 * Used as a Type so the <code>ViewController</code> object  can dynamically 
 * assign a different concrete Connector class depending on the mode that 
 * the program is running in.
 * 
 * @author Matthew Farley 90045985
 */
public interface Connector {
    
    public Database getDatabase();
}
