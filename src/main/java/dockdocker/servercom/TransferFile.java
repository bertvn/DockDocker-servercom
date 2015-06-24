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
import dockdocker.servercom.interfaces.ITransferFile;
import dockdocker.servercom.resources.Configuration;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Bert
 */
public class TransferFile implements ITransferFile {

    private String password;
    private String password2;
    private String remote;
    private JSch ssh = new JSch();

    private final String user;
    private final String host;

    /**
     * constructor for TransferFile
     * @param login username@ip for the server that holds the file
     * @param password password for the server that holds the file
     * @param login2 username@ip for the server that the file will be transferred to
     * @param password2 password for the server that the file will be transferred to
     */
    public TransferFile(String login, String password, String login2, String password2) {
        user = login.substring(0, login.indexOf('@'));
        host = login.substring(login.indexOf('@') + 1);
        remote = login2;
        this.password = password;
        this.password2 = password2;
    }

    /**
     * transfers file over shh
     *
     * @param file file you want to transfer
     * @return server output
     */
    public String transfer(String file) {
        StringBuilder result = new StringBuilder();
        try {
            Session session = ssh.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            Channel channel = session.openChannel("exec");

            System.out.println(file);
            String completeCommand = "tar cz " + Configuration.defaultLoc + "/" + file + " | ssh " + remote + " \"cat > outfile.tar.gz\"";

            ((ChannelExec) channel).setCommand(completeCommand);

            OutputStream out = channel.getOutputStream();

            channel.connect();

            out.write((password2 + "\n").getBytes());
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
                    System.out.println(new String(tmp, 0, i));
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
        return result.toString();
    }

    /**
     * returns login
     * @return username@ip
     */
    public String getLogin() {
        return user + "@" + host;
    }

    /**
     * returns password
     * @return password
     */
    public String getPassword() {
        return password;
    }

}
