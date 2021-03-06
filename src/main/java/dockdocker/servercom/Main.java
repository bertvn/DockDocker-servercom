/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

import dockdocker.servercom.interfaces.IContainerBackup;
import dockdocker.servercom.interfaces.ILoginDataRetriever;
import dockdocker.servercom.interfaces.IMoveContainer;
import dockdocker.servercom.interfaces.ISSHHandler;
import dockdocker.servercom.interfaces.IServer;
import static spark.Spark.*;

/**
 *
 * @author Bert
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //set spark port
        port(4610);

        get("/ssh/:command/:server", (request, response) -> {
            ILoginDataRetriever loginData = new LoginDataRetriever();
            try {
                String[] login = loginData.getServerLogin(request.params(":server"));
                System.out.println(login[0] + "@" + login[1] + " : " + login[2]);
                ISSHHandler han1 = new SSHHandler(login[0] + "@" + login[1], login[2]);
                return han1.runCommand(request.params(":command"));
            } catch (Exception e) {
                //log exception
                System.out.println(e.toString());
                response.status(404);
                return response;
            }
        });

        get("/backup/:name/:server", (request, response) -> {
            ILoginDataRetriever loginData = new LoginDataRetriever();
            try {
                String[] login = loginData.getServerLogin(request.params(":server"));
                ISSHHandler han1 = new SSHHandler(login[0] + "@" + login[1], login[2]);
                IContainerBackup cb = new ContainerBackup(han1);
                return cb.backupContainer(request.params(":name"));
            } catch (Exception e) {
                //log exception
                response.status(404);
                return response;
            }
        });

        get("/move/:name/:server1/:server2", (request, response) -> {
            ILoginDataRetriever loginData = new LoginDataRetriever();
            try {
                String[] login1 = loginData.getServerLogin(request.params(":server1"));
                String[] login2 = loginData.getServerLogin(request.params(":server2"));
                ISSHHandler han1 = new SSHHandler(login1[0] + "@" + login1[1], login1[2]);
                TransferFile tf = new TransferFile(login1[0] + "@" + login1[1], login1[2], login2[0] + "@" + login2[1], login2[2]);

                ISSHHandler han2 = new SSHHandler(login1[0] + "@" + login1[1], login1[2]);
                IServer server1 = new Server(han1, tf, new ContainerBackup(han1));
                IServer server2 = new Server(han2, new ContainerRestore(han2));
                IMoveContainer mover = new MoveContainer(server1, server2);
                String result = mover.transferContainer(request.params(":name"));
                return result;
            } catch (Exception e) {
                //log exception
                response.status(404);
                return response;
            }
        });
    }

}
