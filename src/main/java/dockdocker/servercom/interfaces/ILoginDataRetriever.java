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
public interface ILoginDataRetriever {
    /**
     * returns login information for server servername
     * @param servername name of the server
     * @return String array containing {"username","ip","password"}
     * @throws Exception throws exception if server can't be found
     */
    String[] getServerLogin(String servername) throws Exception;
}
