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
public class MoveContainer implements IMoveContainer{
    private IServer sender;
    private IServer receiver;
    
    public MoveContainer(IServer server1, IServer server2){
        this.sender = server1;
        this.receiver = server2;
    }
    
    /**
     * transfers container from server sender to server receiver
     * @param container name of container
     * @return String indicating where it went wrong
     */
    public String transferContainer(String container){
        boolean hasVolume = true;
        String volume = sender.getSSH().runCommand("docker inspect -f {{.Config.Volumes}} " + container);
        if(volume.equals("<no value>")){
            hasVolume = false;
        }
        
        
        return null;
        
    }
    
    private void transferVolume(String container){
        
    }
    
}
