/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBEngineTest;

import java.util.*;

/**
 *
 * @author matthewfarley
 */
public class Run {
    public static void main(String[] args){
        DBEngineTest db = new DBEngineTest ("H:\\SJDwork\\MF_SJD\\db-2x2_WORKING.db");
        //DBEngineTest db = new DBEngineTest ("db-2x2_WORKING.db");

        //db.delete(Contractor.OFFSET_LENGTH);
        
        //List<Contractor> cList = db.getAllRecords();
//        List<Contractor>  cList = db.search(SearchMode.LOCATION,"Whoville");
        List<Contractor>  cList = db.search("Dogs","Whoville");
        System.out.println(cList.size());
        Contractor c1 = null;
        for(int i = 0; i < cList.size(); i++){
          c1 = cList.get(i);
          
//          System.out.println(c1.isValid());
          System.out.println(c1.getName());
          System.out.println(c1.getCity());
          System.out.println(c1.getSpecialty());
          System.out.println(c1.getStaffNo());
          System.out.println(c1.getCostPerHour());
          System.out.println(c1.getCustomer());
          
//          db.getLocation(Arrays.asList(c1.getName(), c1.getCity()));
        
        }
    
    
    }
    
}
