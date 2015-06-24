/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

import dockdocker.servercom.interfaces.ILoginDataRetriever;

/**
 *
 * @author Bert
 */
public class LoginDataRetriever implements ILoginDataRetriever {

    /**
     * returns login information for server servername
     * @param servername name of the server
     * @return new String[]{"user","ip","password"}
     * @throws Exception servername is not found
     */
    @Override
    public String[] getServerLogin(String servername) throws Exception{
        switch (servername) {
            case "server1":
                return new String[]{"ubuntu-0861465", "145.24.222.169", "HdC883"};
            case "server2":
                return new String[]{"ubuntu-0861465", "145.24.222.146", "64Bxx8"};
        }
        
         throw new Exception("server " + servername + " not found");
    }

}
