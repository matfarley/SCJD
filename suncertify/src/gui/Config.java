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
 * @author Matthew Farley  90045985
 */
public class Config implements Serializable{
    private String networkPath = "";
    private String localPath = "";
    private int port = 0;
    
 /**
 *
 * @param path      the database networkPath to be stored
 */
    public void setNetworkPath(String path){
        this.networkPath = path;
    }
    
 /**
 *
 * @return path      returns the path
 */
    public String getNetworkPath(){
        return networkPath;
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
    public void setPort(int port){
        this.port = port;
    }
    
 /**
 *
 * @return port      returns the port as an int
 */
    public int getPort(){
        return port;
    }
}
