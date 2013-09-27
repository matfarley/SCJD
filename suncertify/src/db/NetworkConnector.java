/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import server.*;

/**
 * Concrete implementation of the <code>Connector</code> interface, used to
 * connect to a remote database
 * 
 * @author 90045985
 */
public class NetworkConnector implements Connector {
    private String path;
    private int port;
    private ServerDatabase db;
    
    /**
     * Creates a new <Code>LocalConnector</code> object
     * 
     * @param path
     * @param port
     */
    public NetworkConnector(String path, int port){
        this.path = path;
        this.port = port;
        //db = new ServerDatabase(path);
    }
    
    /**
     * 
     * @return      A server database object set with a path and port
     */
    @Override public Database getDatabase(){
        return this.db;
    }
}
