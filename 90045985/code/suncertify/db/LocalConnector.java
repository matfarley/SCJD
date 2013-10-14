/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import java.rmi.RemoteException;
import server.RemotableDB;

/**
 * Concrete implementation of the <code>Connector</code> interface, used to
 * connect to a local database.  This object will act as the client when 
 * connecting with the server and receive the stub from the server.
 * 
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */
public class LocalConnector implements Connector {
    private String path;
    private RemotableDB db;
    
    /**
     * Creates a new <Code>LocalConnector</code> object
     * 
     * @param path
     * @throws RemoteException
     */
    public LocalConnector(String path) throws RemoteException, DatabaseException{
        this.path = path;
        db = new Database(path);
    }
    
    /**
     * 
     * @return      A Database object set with a path only
     */
    @Override public RemotableDB getDatabase(){
        return this.db;
    }
}
