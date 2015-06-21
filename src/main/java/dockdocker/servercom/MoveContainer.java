/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

import dockdocker.servercom.interfaces.IMoveContainer;
import dockdocker.servercom.interfaces.IServer;

/**
 *
 * @author Bert
 */
public class MoveContainer implements IMoveContainer {

    private IServer sender;
    private IServer receiver;

    public MoveContainer(IServer server1, IServer server2) {
        this.sender = server1;
        this.receiver = server2;
    }

    /**
     * transfers container from server sender to server receiver
     *
     * @param container name of container
     * @return String indicating where it went wrong
     */
    public String transferContainer(String container) {
        boolean hasVolume = true;
        String volume = sender.getSSH().runCommand("docker inspect -f {{.Config.Volumes}} " + container);
        System.out.println(volume);
        if (volume.equals("<no value>")) {
            hasVolume = false;
        }
        System.out.println("has volume:" + hasVolume);
        String backup = sender.getContainerBackup().backupContainer(container);
        //if backup !== failure
        System.out.println("backup: ");
        System.out.println(backup);
        
        String scp = sender.getSCP().transferFile(container + ".tar", receiver.getSSH().getLogin(), receiver.getSSH().getPassword());
        //if scp !== failure
        System.out.println("scp: ");
        System.out.println(scp);
        
        
        String restore = receiver.getContainerRestore().restoreContainer(container);
        //if restore !== failure
        //System.out.println(scp);
        
        if (hasVolume == false) {
            return restore;
        }

        transferVolume(container, restore);
        return restore;
    }

    private void transferVolume(String container, String newContainer) {
        
    }

}
