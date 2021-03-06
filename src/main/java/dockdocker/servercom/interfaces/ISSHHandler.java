/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom.interfaces;

/**
 *
 * @author Bert
 */
public interface ISSHHandler {
    /**
     * runs command on server
     * @param command command that needs to be run
     * @return result of command
     */
    String runCommand(String command);
    
    /**
     * returns username@ip for the server
     * @return login information
     */
    String getLogin();
    /**
     * returns password for the server
     * @return password
     */
    String getPassword();
}
