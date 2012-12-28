package xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: wanghj
 * Date: 12-12-28
 * Time: 上午10:21
 * To change this template use File | Settings | File Templates.
 */
public class SAXTest {
    public static void main(String[] args) throws Exception
    {
        String url;
        if(args.length==0){
            url="http://www.w3.org/";
            System.out.println("Using "+url);
        }else url=args[0];
        DefaultHandler handler=new DefaultHandler(){
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if(localName.equals("a")&& attributes!=null){
                    for(int i=0;i<attributes.getLength();i++){
                        String name=attributes.getLocalName(i);
                        if(name.equals("href")) System.out.println(attributes.getValue(i));
                    }
                }
            }
        } ;
        SAXParserFactory factory=SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        SAXParser parser=factory.newSAXParser();
        InputStream in=new URL(url).openStream();
        parser.parse(in,handler);

    }
}
