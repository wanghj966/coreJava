package stream.ObjectStream;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: wanghj
 * Date: 12-12-26
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
public class SerialClone implements Cloneable,Serializable {
    public Object clone(){
        try{
            ByteArrayOutputStream bout=new ByteArrayOutputStream();
            ObjectOutputStream out=new ObjectOutputStream(bout);
            out.writeObject(this);
            out.close();
            ByteArrayInputStream bin=new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream in=new ObjectInputStream(bin);
            Object ret=in.readObject();
            in.close();
            return ret;
        }catch (Exception e){
            return null;
        }
    }
}
