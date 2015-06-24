/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

import dockdocker.servercom.interfaces.ISSHHandler;
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
public class ContainerRestoreTest {

    public ContainerRestoreTest() {
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
     * Test of restoreContainer method, of class ContainerRestore.
     */
    @Test
    public void testRestoreContainer() {
        System.out.println("restoreContainer");
        String containerName = "";
        ContainerRestore instance = new ContainerRestore(new FakeSSHHandler());
        String expResult = "{message: \"success\"}";
        String result = instance.restoreContainer(containerName);
        System.out.println(expResult + " == " + result);
        assertEquals(expResult, result);
        expResult = "{message: \"failure\"}";
        result = instance.restoreContainer(containerName);
        System.out.println(expResult + " == " + result);
        assertEquals(expResult, result);
    }

    private class FakeSSHHandler implements ISSHHandler {

        private String defaultLoc;
        private int counter = 0;

        public void setDefaultLoc(String defaultLoc) {
            this.defaultLoc = defaultLoc;
        }

        @Override
        public String runCommand(String command) {
            if (command.equals("docker ps -l")) {
                if (counter == 0) {
                    System.out.println("first container is different");
                    counter++;
                    return "123456";
                }
                System.out.println("standard container");
                return "654321";
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
