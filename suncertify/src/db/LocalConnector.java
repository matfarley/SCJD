/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

/**
 * Concrete implementation of the <code>Connector</code> interface, used to
 * connect to a local database
 * 
 * @author 90045985
 */
public class LocalConnector implements Connector {
    private String path;
    private Database db;
    
    /**
     * Creates a new <Code>LocalConnector</code> object
     * 
     * @param path
     */
    public LocalConnector(String path){
        this.path = path;
        db = new Database(path);
    }
    
    /**
     * 
     * @return      A Database object set with a path only
     */
    @Override public Database getDatabase(){
        return this.db;
    }
}
