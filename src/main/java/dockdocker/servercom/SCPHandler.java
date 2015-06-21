/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

import dockdocker.servercom.interfaces.ISCPHandler;
import dockdocker.servercom.interfaces.ISSHHandler;

/**
 *
 * @author Bert
 */
public class SCPHandler implements ISCPHandler{
    
    private SCPSSH han;
    
    public SCPHandler(SCPSSH han){
        this.han = han;
    }
    
    /**
     * transfers file
     * @param filename name of the file
     * @param targetServer user@ip of the server the file needs to go to
     * @param targetPassword password of the user
     * @return result of the command
     */
    public String transferFile(String filename, String targetServer, String targetPassword){
        String defaultpath = "tempDock/";
        //String result = han.runCommand("scp " + defaultpath + filename + " " + targetServer + ":" + defaultpath);
        String result = han.runCommandExtraPassword("scp " + defaultpath + filename + " " + targetServer + ":" + defaultpath,targetPassword);
        return result;
    }

}
