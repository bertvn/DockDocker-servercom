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
    /**
     * returns an SSH handler
     * @return  SSH handler
     */
    ISSHHandler getSSH();
    
    /**
     * returns a file transfer handler
     * @return file transfer handler
     */
    ITransferFile getTF();
    
    /**
     * returns a container backup handler
     * @return container backup handler
     */
    IContainerBackup getContainerBackup();
    
    /**
     * returns a container restore handler
     * @return container restore handler
     */
    IContainerRestore getContainerRestore();
}
