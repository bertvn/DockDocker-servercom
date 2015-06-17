/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;


import dockdocker.servercom.interfaces.*;

/**
 *
 * @author Bert
 */
public class Server implements IServer{
    private ISSHHandler ssh;
    private ISCPHandler scp;
    private IContainerBackup cb;
    private IContainerRestore cr;
    private IVolumeBackup vb;
    private IVolumeRestore vr;
    
    public Server(ISSHHandler ssh){
        this.ssh = ssh;
    }
    
    public Server(ISSHHandler ssh, ISCPHandler scp, IContainerBackup cb, IVolumeBackup vb){
        this.ssh = ssh;
        this.scp = scp;
        this.cb = cb;
        this.vb = vb;
    }
    
    public Server(ISSHHandler ssh, IContainerRestore cr, IVolumeRestore vr){
        this.ssh = ssh;
        this.cr = cr;
        this.vr = vr;
    }
    
    public ISSHHandler getSSH(){
        return ssh;
    }
    
    public ISCPHandler getSCP(){
        return scp;
    }
    
    public IContainerBackup getContainerBackup(){
        return cb;
    }
    
    public IContainerRestore getContainerRestore(){
        return cr;
    }
    
    public IVolumeBackup getVolumeBackup(){
        return vb;
    }
    
    public IVolumeRestore getVolumeRestore(){
        return vr;
    }
    
}
