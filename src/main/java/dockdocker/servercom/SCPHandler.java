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
    
    private ISSHHandler han;
    
    public SCPHandler(ISSHHandler han){
        this.han = han;
    }
    
    public String transferFile(String filename, String targetServer){
        String defaultpath = "";
        String result = han.runCommand("scp " + defaultpath + filename + " " + targetServer + ":" + defaultpath);
        
        return result;
    }
    
}
