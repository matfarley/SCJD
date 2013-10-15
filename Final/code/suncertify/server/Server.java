/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import db.Database;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import java.util.Arrays;
import java.net.MalformedURLException;
        

/**
 *
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */
public class Server {
    private String path;
    private int port;
    private String dbName = "remoteDB"; //Name used with RMI registry
    private RemotableDB serverDB; //change to serverDatabase type
    //private RemotableDB dbStub; //database implementing the remote interface.
    private Registry registry;
    private String fullName;
    
    public Server(String path, int port) 
            throws RemoteException, MalformedURLException {
        this. path = path;
        this.port = port;
        //Binds to the registry on the local host
        fullName = "rmi://localhost:"+ port + "/" + dbName; 
        serverDB = new Database(path);
        registry = LocateRegistry.getRegistry();
        Naming.rebind(fullName, serverDB);         
        System.out.println(Arrays.toString(registry.list()));// Used for debugging
        System.out.println("serverDB bound"); // Used for debugging
    }
    
    public void close() throws NotBoundException, RemoteException{
        registry.unbind(dbName);
        UnicastRemoteObject.unexportObject(serverDB, true);
        System.out.println("serverDB unbound"); // used for debugging
    }

}
