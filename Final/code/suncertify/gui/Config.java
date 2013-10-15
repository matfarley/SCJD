/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;


import java.io.Serializable;

/**
 * <data>Config<data/> is a data or transfer object used to save the 
 * locations of databases and or ports from one run of the application to the
 * next.
 * 
 *@author  Matthew Farley  90045985
 *09 October 2013	
 *@version 1.0
 */
public class Config implements Serializable{
    public static final String PROPERTIES_FILE_NAME = "suncertify.properties";
    private String dbURL = "";
    private String serverPath = "";
    private String localPath = "";
    private int localPort = 0;
    private int serverPort = 0;
    
    /**
     * 
     * @param path
     */ 
    public void setDbURL(String path){
        this.dbURL = path;
    }
    
    /**
     * 
     * @return
     */
    public String getDbURL(){
        return this.dbURL;
    }
    
     /**
     * 
     * @param path
     */ 
    public void setServerPath(String path){
        this.serverPath = path;
    }
    
    /**
     * 
     * @return
     */
    public String getServerPath(){
        return this.serverPath;
    } 
    
   /**
   *
   * @param path      the local path to be stored
   */
    public void setLocalPath(String path){
        this.localPath = path;
    }
    
   /**
   *
   * @return path      returns the local path
   */
    public String getLocalPath(){
        return localPath;
    }
    
   /**
   *
   * @param port      The port to be stored
   */
    public void setLocalPort(int port){
        this.localPort = port;
    }
    
    /**
    *
    * @return port      returns the port as an int
    */
    public int getLocalPort(){
        return localPort;
    }
    
    /**
    *
    * @param port      The port to be stored
    */
    public void setServerPort(int port){
        this.serverPort = port;
    }
    
    /**
    *
    * @return port      returns the port as an int
    */
    public int getServerPort(){
        return serverPort;
    }
}
