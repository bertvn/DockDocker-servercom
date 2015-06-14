/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

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
        ISSHHandler han = new SSHHandler("ubuntu-0861465@145.24.222.169", "HdC883");
        
        get("/ssh/:command", (request, response) -> {
            return han.runCommand(request.params(":command"));
        });
        
        get("/backup/:name", (request, response) -> {
            IContainerBackup cb = new ContainerBackup(han);
            return cb.backupContainer(request.params(":name"));
        });
    }
    
}
