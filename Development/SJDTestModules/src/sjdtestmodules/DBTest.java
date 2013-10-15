/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sjdtestmodules;

/**
 *
 * @author matthewfarley
 */
public class DBTest {
    DBWorkerTest dbwork = new DBWorkerTest();
    LockMgr lock;
    
    DBTest(){
        
    }
    
    DBTest(LockMgr lock){
        this.lock = lock;
    }
    
    String getData(){
        return dbwork.getData();
    }
}
