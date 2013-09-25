/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NewDBTest;

/**
 *
 * @author 90045985
 */
public class Main {

    public static void main(String[] args){
        Database db = new Database("db-2x2_WORKING.db");
        db.readFile();
    }
}
