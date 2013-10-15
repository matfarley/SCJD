/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flagtest;

import java.util.*;

/**
 *
 * @author matthewfarley
 */
public class FlagTest {
    
    public static void main(String[] args){

        Integer ii = new Integer(0x8000);
        Integer ii3 = new Integer(0x8000);
        Integer ii2 = new Integer(00);
        byte[] b1 = new byte[]{ii.byteValue()};
        byte[] b3 = new byte[]{ii3.byteValue()};
        byte[] b2 = new byte[]{ii2.byteValue()};
        
        

        
        System.out.println(String.valueOf(0x8000));
        System.out.println(String.valueOf(00));
        
//        String s1 = new String(b1);
//        String s2 = new String(b2);
//        String s3 = new String(b3);
//        
//        int i1 = new Integer(String.valueOf(s1));
//        int i2 = new Integer(String.valueOf(s2));
//        int i3 = new Integer(String.valueOf(s3));

//       System.out.println("ii = " + s1);
//        System.out.println("ii2 = " + s2);
//        
//       System.out.println("i = " + i1);
//        System.out.println("i = " + i2);
//        System.out.println("i = " + i3);
////        
//       if(i1 == i3){
//           System.out.println("Test 1 - Equal");
//
//       }
//       else{
//           System.out.println("Test 1 - not equal");
//    }
       
//       
//       if(Byte.compare(flag1, flag2) == 0){
//           System.out.println("Test 1 - Equal");
//
//       }
//       else{
//           System.out.println("Test 1 - not equal");
//           
//       }
//           if(Arrays.equals(arrayflag1, arrayflag2)){
//           System.out.println("Test 2 - Equal");
//
//       }
//       else{
//           System.out.println("Test 2 - not equal");
//           
//       }
//
//       if(Byte.compare(arrayflag3, arrayflag4) == 0){
//           System.out.println("Test 3 - Equal");
//
//       }
//       else{
//           System.out.println("Test 3 - not equal");
//           
//       }
//       
//            if(arrayflag5 == arrayflag6){
//           System.out.println("Test 4 - Equal");
//
//       }
//       else{
//           System.out.println("Test 4 - not equal");
//           
//       }

    }
    
}
