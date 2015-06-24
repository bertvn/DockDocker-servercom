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
public interface IMoveContainer {
    /**
     * container that needs to be moved between servers
     * @param container name of the container
     * @return result "{message: \"success\"}" or "{message: \"failure\"}"
     */
    String transferContainer(String container);
}
