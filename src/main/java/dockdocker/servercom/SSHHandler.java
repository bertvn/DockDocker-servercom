/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

import dockdocker.servercom.interfaces.ISSHHandler;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Bert
 */
public class SSHHandler implements ISSHHandler {

    private String password;
    private JSch ssh = new JSch();

    private final String user;
    private final String host;

    
    /**
     * constructor for SSHHandler
     * @param login username@ip for server
     * @param password password for server
     */
    public SSHHandler(String login, String password) {
        user = login.substring(0, login.indexOf('@'));
        host = login.substring(login.indexOf('@') + 1);
        this.password = password;
    }

    /**
     * run a command on the server through ssh
     *
     * @param command command you want to run
     * @return server output
     */
    @Override
    public String runCommand(String command) {
        StringBuilder result = new StringBuilder();
        try {
            Session session = ssh.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            Channel channel = session.openChannel("exec");

            System.out.println(command);
            String completeCommand = "sudo -S -p '' " + command;

            ((ChannelExec) channel).setCommand(completeCommand);

            OutputStream out = channel.getOutputStream();

            channel.connect();

            out.write((password + "\n").getBytes());
            out.flush();

            channel.setInputStream(null);

            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();

            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    result.append(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            System.out.print(e);
            return null;
        }
        if (result.length() > 1) {
            result.setLength(result.length() - 1);
        }
        return result.toString();
    }

    /**
     * returns login
     * @return username@ip
     */
    @Override
    public String getLogin() {
        return user + "@" + host;
    }

    /**
     * returns password
     * @return password
     */
    @Override
    public String getPassword() {
        return password;
    }
}
