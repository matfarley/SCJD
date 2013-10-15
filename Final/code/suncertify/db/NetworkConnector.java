/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import server.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.Arrays;
import java.net.MalformedURLException;

/**
 * Concrete implementation of the <code>Connector</code> interface, used to
 * connect to a remote database.  Acts as a client.
 * 
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */
public class NetworkConnector implements Connector {
    private String path;
    private int port;
    private String dbName;
    private RemotableDB dbStub; //Use the Interface Type  RemotableDB
    private String fullName;
    
    /**
     * Creates a new <Code>LocalConnector</code> object
     * 
     * @param path
     * @param port
     * @throws RemoteException
     */
    public NetworkConnector(String path, int port) throws RemoteException,
            NotBoundException, MalformedURLException{  
        this.path = path; //local path is "rmi://127.0.0.1/"
        this.port = port;
        dbName = "remoteDB";
        fullName = path + dbName;
        Registry registry = LocateRegistry.getRegistry(port);
        System.out.println(Arrays.toString(registry.list()));// debugging
        dbStub = (RemotableDB)Naming.lookup(fullName);
    }

    /**
     * 
     * @return      A server database object set with a path and port
     */
    @Override public RemotableDB getDatabase(){
        return this.dbStub;
    }
}
