/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**Custom exception used for instances where a requested record is not found in
 * the current database
 *
 * @author matthewfarley
 */
public class DuplicateKeyException extends DatabaseException {
 
/** Creates new RecordNotFOund */
    public DuplicateKeyException(){
    }
    
/** Creates new RecordNotFoundException 
 *  
 * @param message       Message to be passed to the super constructor
 */   
    public DuplicateKeyException(String message){
        super(message);
    }
}
