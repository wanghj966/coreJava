package net.createServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: wanghj
 * Date: 12-12-28
 * Time: 上午11:04
 * To change this template use File | Settings | File Templates.
 */
public class ThreadedEchoServer {
    public static void main(String[] args){
        try{
            int i=1;
            ServerSocket s=new ServerSocket(8189);
            while (true){
                Socket incoming=s.accept();
                System.out.println("spawing "+i);
                Runnable r=new ThreadedEchoHandler(incoming);
                Thread t=new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
