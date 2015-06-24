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
public class Server implements IServer {

    private ISSHHandler ssh;
    private ITransferFile tf;
    private IContainerBackup cb;
    private IContainerRestore cr;

    /**
     * constructor for server
     * @param ssh ssh handler
     */
    public Server(ISSHHandler ssh) {
        this.ssh = ssh;
    }

    /**
     * constructor for server
     * @param ssh ssh handler
     * @param tf transfer file handler
     * @param cb container backup handler
     */
    public Server(ISSHHandler ssh, ITransferFile tf, IContainerBackup cb) {
        this.ssh = ssh;
        this.tf = tf;
        this.cb = cb;
    }

    /**
     * constructor for server
     * @param ssh ssh handler
     * @param cr container restore handler
     */
    public Server(ISSHHandler ssh, IContainerRestore cr) {
        this.ssh = ssh;
        this.cr = cr;
    }

    /**
     * returns ssh handler
     *
     * @return ssh handler
     */
    public ISSHHandler getSSH() {
        return ssh;
    }

    /**
     * returns transfer file handler
     *
     * @return transfer file handler
     */
    public ITransferFile getTF() {
        return tf;
    }

    /**
     * returns container backup class
     *
     * @return container backup
     */
    public IContainerBackup getContainerBackup() {
        return cb;
    }

    /**
     * returns container restore class
     *
     * @return container restore
     */
    public IContainerRestore getContainerRestore() {
        return cr;
    }

}
