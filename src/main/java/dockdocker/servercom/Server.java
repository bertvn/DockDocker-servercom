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
    
    /**
     * returns ssh handler
     * @return ssh handler
     */
    public ISSHHandler getSSH(){
        return ssh;
    }
    
    /**
     * returns scp handler
     * @return scp handler
     */
    public ISCPHandler getSCP(){
        return scp;
    }
    
    /**
     * returns container backup class
     * @return container backup
     */
    public IContainerBackup getContainerBackup(){
        return cb;
    }
    
    /**
     * returns container restore class
     * @return container restore
     */
    public IContainerRestore getContainerRestore(){
        return cr;
    }
    
    /**
     * returns volume backup class
     * @return volume backup
     */
    public IVolumeBackup getVolumeBackup(){
        return vb;
    }
    
    /**
     * returns volume restore class
     * @return volume restore
     */
    public IVolumeRestore getVolumeRestore(){
        return vr;
    }
    
}
