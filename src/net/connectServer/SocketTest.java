package net.connectServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: wanghj
 * Date: 12-12-28
 * Time: 上午10:40
 * To change this template use File | Settings | File Templates.
 */
public class SocketTest {
    public static void main(String[] args){
        try{
            Socket s=new Socket("time-A.timefreq.bldrdoc.gov",13);
            try{
                InputStream inStream=s.getInputStream();
                Scanner in=new Scanner(inStream);
                while (in.hasNextLine()){
                    String line=in.nextLine();
                    System.out.println(line);
                }
            } finally {
                s.close();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
