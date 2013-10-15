/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 * Custom Exception which will act as a parent to more specific database 
 * oriented exceptions
 * 
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */

/** Creates new DatabaseException */
public class DatabaseException extends Exception{
    public DatabaseException(){
    }
    
/** Creates new DatabaseException 
 *  
 * @param message       Message to be passed to the super constructor
 */    
    public DatabaseException(String message){
        super(message);
    }
    
}
