package xml.sax;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: wanghj
 * Date: 12-12-28
 * Time: 上午10:30
 * To change this template use File | Settings | File Templates.
 */
public class StAXTest {
    public static void main(String[] args)throws Exception
    {
        String urlString;
        if(args.length==0){
            urlString="http://www.w3.org/" ;
            System.out.println("Using "+urlString);
        } else urlString=args[0];
        URL url=new URL(urlString);
        InputStream in=url.openStream();
        XMLInputFactory factory=XMLInputFactory.newInstance();
        XMLStreamReader parser=factory.createXMLStreamReader(in);
        while(parser.hasNext()){
            int event=parser.next();
            if(event== XMLStreamConstants.START_ELEMENT){
                if(parser.getLocalName().equals("a")){
                    String href=parser.getAttributeValue(null,"href");
                    if(href!=null)
                        System.out.println(href);
                }
            }
        }
    }

}
