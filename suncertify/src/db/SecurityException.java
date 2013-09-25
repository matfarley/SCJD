/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *  Custom exception used for issues that arise with database and record 
 *  locking
 * 
 * @author matthewfarley
 */
public class SecurityException extends DatabaseException{
    
/** Creates new SecurityException */
    public SecurityException(){
    }
    
/** Creates new form SecurityException 
 *  
 * @param message       Message to be passed to the super constructor
 */   
    public SecurityException(String message){
        super(message);
    }
    
}
