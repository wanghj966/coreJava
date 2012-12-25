package stream.RandomFile;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wanghj
 * Date: 12-12-25
 * Time: 下午2:59
 * To change this template use File | Settings | File Templates.
 */
public class DataIO {
    public static String readFixedString(int size,DataInput in) throws IOException
    {
        StringBuilder sb=new StringBuilder(size);
        int i=0;
        boolean more=true;
        while (more && i<size){
            char ch=in.readChar();
            i++;
            if(ch==0)more=false;
            else sb.append(ch);
        }
        in.skipBytes(2*(size-i));
        return sb.toString();
    }
    public static void writeFixedString(String s,int size,DataOutput out)throws IOException{
        for(int i=0;i<size;i++)
        {
         char ch=0;
         if(i<s.length()) ch=s.charAt(i);
            out.writeChar(ch);
        }
    }
}
