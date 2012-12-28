package net.connectServer;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: wanghj
 * Date: 12-12-28
 * Time: 上午10:46
 * To change this template use File | Settings | File Templates.
 */
public class InetAddressTest {
    public static void main(String[] args){
        try{
            if(args.length>0){
                String host=args[0];
                InetAddress[] addresses=InetAddress.getAllByName(host);
                for(InetAddress a:addresses){
                    System.out.println(a);
                }
            }
            else {
                InetAddress localhostAddress=InetAddress.getLocalHost();
                System.out.println(localhostAddress);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
