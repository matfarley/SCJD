/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

/**
 *
 * @author matthewfarley
 */
public class AddressTest {
  
    static public void main(String[] args){
      InetAddress address;
         try{

        address = InetAddress.getByName(null); //throws Unknown Host Exception
        System.out.println(address+"/testName");
        
        }catch(Exception e){
            System.out.println(e);//used for debugging
        }
    }
    
    
}
