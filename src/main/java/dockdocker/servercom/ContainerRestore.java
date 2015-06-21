/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

import dockdocker.servercom.interfaces.IContainerRestore;
import dockdocker.servercom.interfaces.ISSHHandler;

/**
 *
 * @author Bert
 */
public class ContainerRestore implements IContainerRestore {

    private ISSHHandler han;
    private String defaultLoc = "tempDock";

    public ContainerRestore(ISSHHandler han) {
        this.han = han;
    }

    /**
     * restores a container
     *
     * @param containerName name of the container
     * @return json string with key message and either success of failure as
     * value
     */
    public String restoreContainer(String containerName) {
        String begin = han.runCommand("docker ps -l"); 
        
        //regex NAMES\n([0-9A-Za-z]*) $1
        String oldID = begin.replaceAll(".*NAMES\n([0-9A-Za-z]*) .*", "$1");
        
        han.runCommand("rm -f " + defaultLoc + "/*");
        han.runCommand("tar xzf outfile.tar.gz");
        
        han.runCommand("docker load < " + defaultLoc + "/" + containerName + ".tar");
        han.runCommand("docker run " + containerName);
        
        String status = han.runCommand("docker ps -l"); 
        
        //regex NAMES\n([0-9A-Za-z]*) $1
        String containerID = status.replaceAll(".*NAMES\n([0-9A-Za-z]*) .*", "$1");
        if(containerID.equals(oldID)){
            return "{message: \"failure\"}";
        }
        han.runCommand("docker start " + containerID);
        return "{message: \"success\"}";
    }
}
