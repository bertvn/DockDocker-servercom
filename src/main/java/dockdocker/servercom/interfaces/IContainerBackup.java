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
public interface IContainerBackup {
    /**
     * backups container
     * @param containerName container to be backupped
     * @return result "{message: \"success\"}" or "{message: \"failure\"}"
     */
    String backupContainer(String containerName);
}
