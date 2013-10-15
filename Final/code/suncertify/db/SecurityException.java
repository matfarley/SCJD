/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *  Custom exception used for issues that arise with database and record 
 *  locking
 * 
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
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
