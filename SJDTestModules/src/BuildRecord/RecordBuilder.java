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
public class RecordBuilder {
    //include this step so that the new string is padded with blanks.
public String blankRecord = new String(new byte[(Contractor.RECORD_LENGTH - Contractor.FLAG_LENGTH)]);    
    
    
    
    
 

        
    

        
    
     
     
     public String buildRecord(String name, String city, String specialty, String size, String rate){
         
         class Buildit{
               public StringBuilder newRecordString = new StringBuilder(blankRecord);;
               int offset = 0; 
               
               public void writeRecord(String data, int fieldLength){
         
               newRecordString.replace(offset, (offset + data.length()), data);
               offset = fieldLength;
               }
         }
         Buildit b = new Buildit();
         b.writeRecord(name, Contractor.NAME_LENGTH);
         b.writeRecord(city, Contractor.CITY_LENGTH);
         b.writeRecord(specialty, Contractor.SPECIALTIES_LENGTH);
         b.writeRecord(size, Contractor.STAFF_LENGTH );
         b.writeRecord(rate, Contractor.RATE_LENGTH );
         return b.newRecordString.toString(); 
               
               
         }
         
   
     }
     
