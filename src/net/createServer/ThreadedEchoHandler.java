package net.createServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: wanghj
 * Date: 12-12-28
 * Time: 上午11:04
 * To change this template use File | Settings | File Templates.
 */
public class ThreadedEchoHandler implements Runnable {
    public   ThreadedEchoHandler(Socket i){
        incoming=i;
    }
    public void run(){
        try{
            try{
                InputStream inStream=incoming.getInputStream();
                OutputStream outStream=incoming.getOutputStream();
                Scanner in=new Scanner(inStream);
                PrintWriter out=new PrintWriter(outStream,true);

                out.println("hello! enter BYBY to exit.");

                //echo client input
                boolean done=false;
                while(!done&&in.hasNextLine()){
                    String line=in.nextLine();
                    out.println("Echo:"+line);
                    if(line.trim().equals("BYBY")) done=true;
                }
            } finally {
                incoming.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private Socket incoming;
}
