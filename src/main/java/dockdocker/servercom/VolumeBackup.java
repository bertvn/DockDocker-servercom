/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

import dockdocker.servercom.interfaces.ISSHHandler;
import dockdocker.servercom.interfaces.IVolumeBackup;

/**
 *
 * @author Bert
 */
public class VolumeBackup implements IVolumeBackup{
    
    public VolumeBackup(ISSHHandler han){
    
    }
    
    /**
     * backups a volume
     * @param containerName name of the container of the volume
     * @return json string with key message and either success of failure as value
     */
    public String backupVolume(String containerName){
        String result = "";
        if(result.equals("")){
            return "{message: \"success\"}";
        }else{
            return "{message: \"failure\"}";
        }
    }
}
