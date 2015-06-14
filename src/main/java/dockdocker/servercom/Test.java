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
import com.jcraft.jsch.UserInfo;
import java.io.InputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Bert
 */
public class Test {
    
    public Test(){
        
    }
    
    public static void main(String[] arg){
    try{
      JSch jsch=new JSch();  
 
      String host=null;
      if(arg.length>0){
        host=arg[0];
      }
      else{
        host= "ubuntu-0861465@145.24.222.169";
      }
      String user=host.substring(0, host.indexOf('@'));
      host=host.substring(host.indexOf('@')+1);
 
      Session session=jsch.getSession(user, host, 22);
      
      /*
      String xhost="127.0.0.1";
      int xport=0;
      String display=JOptionPane.showInputDialog("Enter display name", 
                                                 xhost+":"+xport);
      xhost=display.substring(0, display.indexOf(':'));
      xport=Integer.parseInt(display.substring(display.indexOf(':')+1));
      session.setX11Host(xhost);
      session.setX11Port(xport+6000);
      */
      
      session.setPassword("HdC883");
      session.setConfig("StrictHostKeyChecking", "no");
      session.connect();
 
      String command= "echo \"test is een succes\"";
      command = "sudo -S -p HdC883 docker ps"; 
 
      Channel channel=session.openChannel("exec");
      ((ChannelExec)channel).setCommand(command);
 
      // X Forwarding
      // channel.setXForwarding(true);
 
      //channel.setInputStream(System.in);
      channel.setInputStream(null);
 
      //channel.setOutputStream(System.out);
 
      //FileOutputStream fos=new FileOutputStream("/tmp/stderr");
      //((ChannelExec)channel).setErrStream(fos);
      ((ChannelExec)channel).setErrStream(System.err);
 
      InputStream in=channel.getInputStream();
 
      channel.connect();
 
      byte[] tmp=new byte[1024];
      while(true){
        while(in.available()>0){
          int i=in.read(tmp, 0, 1024);
          if(i<0)break;
          System.out.print(new String(tmp, 0, i));
        }
        if(channel.isClosed()){
          if(in.available()>0) continue; 
          System.out.println("exit-status: "+channel.getExitStatus());
          break;
        }
        try{Thread.sleep(1000);}catch(Exception ee){}
      }
      channel.disconnect();
      session.disconnect();
    }
    catch(Exception e){
      System.out.println(e);
    }
  }
}
