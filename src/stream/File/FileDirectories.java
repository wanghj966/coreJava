package stream.File;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wanghj
 * Date: 12-12-26
 * Time: 上午10:29
 * To change this template use File | Settings | File Templates.
 */
public class FileDirectories {
    public static void main(String[] args){
        if(args.length==0) args=new String[]{".."};
        try{
            File pathName=new File(args[0]);
            String[] fileNames=pathName.list();
            //enumerate
            for(int i=0;i<fileNames.length;i++){
                File f=new File(pathName,fileNames[i]);
                if(f.isDirectory()){
                    System.out.println(f.getCanonicalPath());
                    main(new String[]{f.getPath()});
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
