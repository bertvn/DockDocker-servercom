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
public interface IContainerRestore {
    /**
     * restore container from containerName.tar
     * @param containerName name of tar file
     * @return result "{message: \"success\"}" or "{message: \"failure\"}"
     */
    String restoreContainer(String containerName);
}
