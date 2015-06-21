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
        this.han = han;
    }
    
    /**
     * backups a container
     * @param containerName name of the container
     * @return json string with key message and either success of failure as value
     */
    public String backupContainer(String containerName){
        /*
        //todo remove
        String imageName = han.runCommand("docker inspect -f {{.Config.Image}} " + containerName);
        System.out.println("imageName: " + imageName);
        String imageID = han.runCommand("docker inspect -f {{.Image}} " + containerName);
        System.out.println("imageID: " + imageID);
        //end
        */
        //check if dir exist otherwise create it
        System.out.println("checking if " + defaultLoc + "/ exist");
        System.out.println(han);
        System.out.println(han.getLogin() + " : " + han.getPassword());
        String dir = han.runCommand("ls " + defaultLoc);
        if(dir.equals("ls: cannot access " + defaultLoc + ": No such file or directory")){
            han.runCommand("mkdir " + defaultLoc);
        }
        System.out.println("writing to " + defaultLoc + "/");
        han.runCommand("rm -f " + defaultLoc + "/" + containerName + ".tar");
        String containerID = han.runCommand("docker inspect -f {{.Id}} " + containerName);
        han.runCommand("docker commit " + containerID + " " + containerName + "_img");
        
        String result = han.runCommand("docker save " + containerName + "_img > " + defaultLoc + "/" + containerName + ".tar");
        
        if(result.equals("")){
            return "{message: \"success\"}";
        }else{
            return "{message: \"failure\"}";
        }
    }
    
    
}
