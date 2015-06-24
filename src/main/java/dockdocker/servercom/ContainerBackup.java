/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

import dockdocker.servercom.interfaces.IContainerBackup;
import dockdocker.servercom.interfaces.ISSHHandler;
import dockdocker.servercom.resources.Configuration;

/**
 *
 * @author Bert
 */
public class ContainerBackup implements IContainerBackup {

    private ISSHHandler han;

    /**
     * constructor for ContainerBackup
     * @param han ssh handler
     */
    public ContainerBackup(ISSHHandler han) {
        this.han = han;
    }

    /**
     * backups a container
     *
     * @param containerName name of the container
     * @return json string with key message and either success of failure as
     * value
     */
    public String backupContainer(String containerName) {
        //check if dir exist otherwise create it
        System.out.println("checking if " + Configuration.defaultLoc + "/ exist"); 
        System.out.println(han.getLogin() + " : " + han.getPassword());
        String dir = han.runCommand("ls " + Configuration.defaultLoc);
        if (dir.equals("ls: cannot access " + Configuration.defaultLoc + ": No such file or directory")) {
            han.runCommand("mkdir " + Configuration.defaultLoc);
        }
        System.out.println("writing to " + Configuration.defaultLoc + "/");
        han.runCommand("rm -f " + Configuration.defaultLoc + "/" + containerName + ".tar");
        String containerID = han.runCommand("docker inspect -f {{.Id}} " + containerName);
        han.runCommand("docker commit " + containerID + " " + containerName + "_img");

        String result = han.runCommand("docker save " + containerName + "_img > " + Configuration.defaultLoc + "/" + containerName + ".tar");

        if (result.equals("")) {
            return "{message: \"success\"}";
        } else {
            return "{message: \"failure\"}";
        }
    }

}
