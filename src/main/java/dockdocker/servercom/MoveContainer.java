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

    private final IServer sender;
    private final IServer receiver;

    /**
     * constructor for movecontainer
     * @param server1 server where the container runs
     * @param server2 server where the container will be copied to
     */
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
    @Override
    public String transferContainer(String container) {
        
        String backup = sender.getContainerBackup().backupContainer(container);
        //if backup !== failure
        System.out.println("backup: ");
        System.out.println(backup);
        
        String scp = sender.getTF().transfer(container + ".tar");
        //if scp !== failure
        System.out.println("scp: ");
        System.out.println(scp);
        
        String restore = receiver.getContainerRestore().restoreContainer(container);

        return restore;
    }

}
