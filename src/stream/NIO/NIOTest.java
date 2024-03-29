package stream.NIO;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.RandomAccess;
import java.util.zip.CRC32;

/**
 * Created with IntelliJ IDEA.
 * User: wanghj
 * Date: 12-12-26
 * Time: 上午11:02
 * To change this template use File | Settings | File Templates.
 */
public class NIOTest {
    public static long checksumInputStream(String filename) throws IOException
    {
        InputStream in=new FileInputStream(filename);
        CRC32 crc=new CRC32();
        int c;
        while((c=in.read())!=-1) crc.update(c);
        return crc.getValue();
    }
    public static long checksumBufferedInputStream(String filename) throws IOException
    {
        InputStream in=new BufferedInputStream(new FileInputStream(filename));
        CRC32 crc=new CRC32();
        int c;
        while((c=in.read())!=-1) crc.update(c);
        return crc.getValue();
    }
    public static long checksumRandomAccessFile(String filename) throws IOException
    {
        RandomAccessFile in= new RandomAccessFile(filename,"r");
        long length=in.length();
        CRC32 crc=new CRC32();
        for(long p=0;p<length;p++){
            in.seek(p);
            int c=in.readByte();
            crc.update(c);
        }
        return crc.getValue();
    }
    public static long checksumMappedFile(String filename) throws IOException
    {
        FileInputStream in=new FileInputStream(filename);
        FileChannel channel=in.getChannel();
        CRC32 crc=new CRC32();
        int length=(int)channel.size();
        MappedByteBuffer buffer=channel.map(FileChannel.MapMode.READ_ONLY,0,length);
        for(int p=0;p<length;p++){
            int c=buffer.get(p);
            crc.update(c);
        }
        return crc.getValue();
    }
    public static void main(String[] args) throws IOException {
        args=new String[]{"D:\\contacts.csv"};
        System.out.println("Input Stream:");
        long start=System.currentTimeMillis();
        long crcValue=checksumInputStream(args[0]);
        long end=System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end-start)+"milliseconds");

        System.out.println("Buffered Input Stream:");
        start=System.currentTimeMillis();
        crcValue=checksumBufferedInputStream(args[0]);
        end=System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end-start)+"milliseconds");

        System.out.println("Random Access File:");
        start=System.currentTimeMillis();
        crcValue=checksumRandomAccessFile(args[0]);
        end=System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end-start)+"milliseconds");

        System.out.println("Mapped File:");
        start=System.currentTimeMillis();
        crcValue=checksumMappedFile(args[0]);
        end=System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end-start)+"milliseconds");
    }
}
