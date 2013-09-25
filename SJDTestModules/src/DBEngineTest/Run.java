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
        //DBEngineTest db = new DBEngineTest ("H:\\SJDwork\\MF_SJD\\db-2x2_WORKING.db");
        DBEngineTest db = new DBEngineTest ("db-2x2_WORKING.db");
//        String record = new String(db.readRecord(Contractor.OFFSET_LENGTH));
//        System.out.println(record);
        //System.out.println(db.convertRecord(db.readRecord(0)));
//        Contractor c1 = db.convertRecord(db.readRecord(Contractor.OFFSET_LENGTH));
//        System.out.println(c1.getName());
//        System.out.println(c1.getCity());
//        System.out.println(c1.getSpecialty());
//        System.out.println(c1.getStaffNo());
//        System.out.println(c1.getCostPerHour());
//        System.out.println(c1.getCustomer());
        
        List<Contractor> cList = db.getAllRecords();
        System.out.println(cList.size());
        Contractor c1 = null;
        for(int i = 0; i < cList.size(); i++){
          c1 = cList.get(i);
          System.out.println(c1.getName());
          System.out.println(c1.getCity());
          System.out.println(c1.getSpecialty());
          System.out.println(c1.getStaffNo());
          System.out.println(c1.getCostPerHour());
          System.out.println(c1.getCustomer());
        }
    }
}
