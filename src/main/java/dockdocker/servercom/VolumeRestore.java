/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

import dockdocker.servercom.interfaces.ISSHHandler;
import dockdocker.servercom.interfaces.IVolumeRestore;

/**
 *
 * @author Bert
 */
public class VolumeRestore implements IVolumeRestore{
    public VolumeRestore(ISSHHandler han){}
    
    /**
     * restores a volume
     * @param containerName name of the container of the volume
     * @return json string with key message and either success of failure as value
     */
    public String restoreVolume(String containerName){
        return null;
    }
}
