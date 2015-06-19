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
    private String defaultLoc = "tempDock";
    
    public ContainerBackup(ISSHHandler han){
        han = han;
    }
    
    /**
     * backups a container
     * @param containerName name of the container
     * @return json string with key message and either success of failure as value
     */
    public String backupContainer(String containerName){
        String imageName = han.runCommand("docker inspect -f {{.Config.Image}} " + containerName);
        String imageID = han.runCommand("docker inspect -f {{.Image}} " + containerName);
        
        String dir = han.runCommand("ls " + defaultLoc);
        if(dir.equals("ls: cannot access " + defaultLoc + ": No such file or directory")){
            han.runCommand("mkdir " + defaultLoc);
        }
        
        han.runCommand("rm -f " + defaultLoc + "/" + containerName + ".tar");
        String containerID = han.runCommand("docker inspect -f {{.Id}} " + containerName);
        han.runCommand("docker commit " + containerID + " " + containerName);
        
        String result = han.runCommand("docker save " + containerName + " > " + defaultLoc + "/" + containerName + ".tar");
        
        if(result.equals("")){
            return "{message: \"success\"}";
        }else{
            return "{message: \"failure\"}";
        }
    }
    
    
}
