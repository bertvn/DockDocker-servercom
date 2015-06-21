/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dockdocker.servercom;

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
public class SCPSSH {

    private String password;
    private String password2;
    private JSch ssh = new JSch();

    private final String user;
    private final String host;

    public SCPSSH(String login, String password) {
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
    public String runCommand(String command) {
        StringBuilder result = new StringBuilder();
        try {
            Session session = ssh.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            Channel channel = session.openChannel("exec");
            //((ChannelExec) channel).setCommand("echo " + password + " | sudo -S " + command);

            System.out.println(command);
            String completeCommand = command;

            ((ChannelExec) channel).setCommand(completeCommand);

            OutputStream out = channel.getOutputStream();

            //((ChannelExec) channel).setPty(true);
            channel.connect();

            out.write((password2 + "\n").getBytes());
            out.flush();
            //out.write((password2 + "\n").getBytes());
            //out.flush();

            // X Forwarding
            // channel.setXForwarding(true);
            //channel.setInputStream(System.in);
            channel.setInputStream(null);

            //channel.setOutputStream(System.out);
            //FileOutputStream fos=new FileOutputStream("/tmp/stderr");
            //((ChannelExec)channel).setErrStream(fos);
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
                    System.out.println(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    //System.out.println("exit-status: " + channel.getExitStatus());
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
        return result.toString();
    }

    /**
     * runs command and allows for another password to be entered. used for SCP
     *
     * @param command command you want to run
     * @param extraPassword password for SCP
     * @return server output
     */
    public String runCommandExtraPassword(String command, String extraPassword) {
        //String temp = password;
        //password = password + "\n" + extraPassword;
        password2 = extraPassword;
        String result = runCommand(command);
        //password = temp;
        return result;
    }

    public String getLogin() {
        return user + "@" + host;
    }

    public String getPassword() {
        return password;
    }

}
