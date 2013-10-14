/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BuildRecord;

/**
 *
 * @author 90045985
 */
public class Runner {
    public static void main(String[] args){
        RecordBuilder rb = new RecordBuilder();
        String test = rb.buildRecord("Matt", "Auck", "Testing", "12", "$13");
        System.out.println(test);
        System.out.println(test.length());
    }

}
