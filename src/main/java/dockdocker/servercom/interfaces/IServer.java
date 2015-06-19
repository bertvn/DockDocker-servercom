/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom.interfaces;

/**
 *
 * @author Bert
 */
public interface IServer {
    public ISSHHandler getSSH();
    
    public ISCPHandler getSCP();
    
    public IContainerBackup getContainerBackup();
    
    public IContainerRestore getContainerRestore();
    
    public IVolumeBackup getVolumeBackup();
    
    public IVolumeRestore getVolumeRestore();
}
