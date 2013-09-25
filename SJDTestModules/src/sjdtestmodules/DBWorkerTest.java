/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sjdtestmodules;

/**
 *
 * @author matthewfarley
 */
public class DBWorkerTest {
    DBData db = new DBData();
    
    String getData(){
        return db.getData();
    }
}
