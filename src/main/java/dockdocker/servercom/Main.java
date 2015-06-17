/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

import dockdocker.servercom.interfaces.IContainerBackup;
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
        ISSHHandler han1 = new SSHHandler("ubuntu-0861465@145.24.222.169", "HdC883");
        ISSHHandler han2 = new SSHHandler("ubuntu-0861465@145.24.222.146", "64Bxx8");
        
        get("/ssh/:command", (request, response) -> {
            return han1.runCommand(request.params(":command"));
        });
        
        get("/backup/:name", (request, response) -> {
            IContainerBackup cb = new ContainerBackup(han1);
            return cb.backupContainer(request.params(":name"));
        });
        
        get("/move/:name/:server1/:server2", (request, response) -> {
            IServer server1 = new Server(han1, new SCPHandler(han1), new ContainerBackup(han1), new VolumeBackup(han1));
            IServer server2 = new Server(han2,new ContainerRestore(han2), new VolumeRestore(han2));
            IMoveContainer mover = new MoveContainer(server1, server2);
            return "dope shit man!";
        });
    }
    
}
