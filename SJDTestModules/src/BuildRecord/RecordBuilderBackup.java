/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BuildRecord;

import DBEngineTest.Contractor;

/**
 *
 * @author 90045985
 */
public class RecordBuilderBackup {
    
    
    
 
    //include this step so that the new string is padded with blanks.
public String protoRecordString = new String(new byte[(Contractor.RECORD_LENGTH - Contractor.FLAG_LENGTH)]);
    public StringBuilder blankRecord = null;
   int offset = 0;       
    

        
    
     public void writeRecord(String data, int fieldLength){
         
         blankRecord.replace(offset, (offset + data.length()), data);
         offset = fieldLength;

    }
     
     public String buildRecord(String name, String city, String specialty, String size, String rate){
         blankRecord = new StringBuilder(protoRecordString);
         
         writeRecord(name, Contractor.NAME_LENGTH);
         writeRecord(city, Contractor.CITY_LENGTH);
         writeRecord(specialty, Contractor.SPECIALTIES_LENGTH);
         writeRecord(size, Contractor.STAFF_LENGTH );
         writeRecord(rate, Contractor.RATE_LENGTH );
         
         return blankRecord.toString();


         
         
         
     }
     
     

}
