/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**Custom exception used for instances where a user attempts to create a new 
 * record with the same primary key as an existing record
 *
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */
public class RecordNotFoundException extends DatabaseException {
 
/** Creates new RecordNotFOund */
    public RecordNotFoundException(){
    }
    
/** Creates new RecordNotFoundException 
 *  
 * @param message       Message to be passed to the super constructor
 */   
    public RecordNotFoundException(String message){
        super(message);
    }
}
