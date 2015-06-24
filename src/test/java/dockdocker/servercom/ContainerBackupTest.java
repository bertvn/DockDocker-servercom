/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

import dockdocker.servercom.interfaces.ISSHHandler;
import dockdocker.servercom.resources.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bert
 */
public class ContainerBackupTest {

    private ISSHHandler fakeHandler;
    
    public ContainerBackupTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of backupContainer method, of class ContainerBackup.
     */
    @Test
    public void testBackupContainer() {
        System.out.println("backupContainer");
        String containerName = "";
        fakeHandler = new FakeSSHHandler();
        ContainerBackup instance = new ContainerBackup(this.fakeHandler);
        String expResult = "{message: \"success\"}";
        String result = instance.backupContainer(containerName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    private class FakeSSHHandler implements ISSHHandler {

        @Override
        public String runCommand(String command) {
            String[] args = command.split(" ");
            if (args[1].equals(Configuration.defaultLoc)) {
                switch (args[0]) {
                    case "ls":
                        System.out.println(Configuration.defaultLoc + " not found");
                        return "ls: cannot access " + Configuration.defaultLoc + ": No such file or directory";
                    case "mk":
                        System.out.println("folder made");
                        return "";
                }
            }
            switch (args[1]) {
                case "save":
                    System.out.println("saved");
                    return "";
                case "-f":
                    System.out.println("removed");
                    return "";
            }
            
            return "";
        }

        @Override
        public String getLogin() {
            return "fake@login";
        }

        @Override
        public String getPassword() {
            return "123456seven";
        }

    }

}
