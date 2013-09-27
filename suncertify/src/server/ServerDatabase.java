/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;


import db.*;
/**
 *
 * @author 90045985
 */
public class ServerDatabase extends Database{
    private int port;
    private String path;
    private DBEngine db;

    public ServerDatabase(String path, int port){
        this.path = path;
        this.port = port;
    }
}
