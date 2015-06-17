/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

import dockdocker.servercom.interfaces.IContainerBackup;
import dockdocker.servercom.interfaces.ISSHHandler;

/**
 *
 * @author Bert
 */
public class ContainerBackup implements IContainerBackup{
    private ISSHHandler han;
    
    public ContainerBackup(ISSHHandler han){
        han = han;
    }
    
    public String backupContainer(String containerName){
        String imageName = han.runCommand("docker inspect -f {{.Config.Image}} " + containerName);
        String imageID = han.runCommand("docker inspect -f {{.Image}} " + containerName);
        
        String result = han.runCommand("docker save -o " + containerName + ".tar " + imageID);
        
        if(result.equals("")){
            return "{message: \"success\"}";
        }else{
            return "{message: \"failure\"}";
        }
    }
    
    
}
