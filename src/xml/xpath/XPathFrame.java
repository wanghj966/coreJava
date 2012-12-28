package xml.xpath;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileFilter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class XPathFrame extends JFrame {
    public  XPathFrame(){
        setTitle("XpathTest");
        JMenu fileMenu=new JMenu("File");
        JMenuItem openItem=new JMenuItem("open");
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        fileMenu.add(openItem);
        JMenuItem exitMenu=new JMenuItem("Exit");
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenu);
        JMenuBar menuBar=new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        ActionListener listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evaluate();
            }
        };
        expression=new JTextField(20);
        expression.addActionListener(listener);
        JButton evaluateButton=new JButton("Evaluate");
        evaluateButton.addActionListener(listener);

        typeCombo=new JComboBox(new Object[]{"STRING","NODE","NODESET","NUMBER","BOOLEAN"});
        typeCombo.setSelectedItem("STRING");

        JPanel panel=new JPanel();
        panel.add(expression);
        panel.add(typeCombo);
        panel.add(evaluateButton);
        docText=new JTextArea(10,40);
        result=new JTextField();
        result.setBorder(new TitledBorder("Result"));
        add(panel,BorderLayout.NORTH) ;
        add(new JScrollPane(docText),BorderLayout.CENTER);
        add(result,BorderLayout.SOUTH);
        try{
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            builder=factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            JOptionPane.showMessageDialog(this,e);
        }
        XPathFactory xpfactory=XPathFactory.newInstance();
        path=xpfactory.newXPath();
        pack();

    }


    private void openFile() {
        JFileChooser chooser=new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileFilter(new FileFilter(){

            @Override
            public boolean accept(File f) {
                return f.isDirectory()||f.getName().toLowerCase().endsWith(".xml");
            }

            @Override
            public String getDescription() {
                return "XML files";  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        int r=chooser.showOpenDialog(this);
        if(r!=JFileChooser.APPROVE_OPTION) return;
        File f=chooser.getSelectedFile();
        try{
            byte[] bytes=new byte[(int)f.length()];
            new FileInputStream(f).read(bytes);
            docText.setText(new String(bytes));
            doc=builder.parse(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SAXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    private void evaluate() {
        try{
            String typeName=(String)typeCombo.getSelectedItem();
            QName returnType=(QName) XPathConstants.class.getField(typeName).get(null);
            Object evalResult=path.evaluate(expression.getText(),doc,returnType);
            if(typeName.equals("NODESET")){
                NodeList list=(NodeList)evalResult;
                StringBuilder builder=new StringBuilder();
                builder.append("{");
                for (int i=0;i<list.getLength();i++){
                    if(i>0)builder.append(",");
                    builder.append(""+list.item(i));
                }
                builder.append("}");
                result.setText(""+builder);
            }else  result.setText(""+evalResult);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (XPathExpressionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private DocumentBuilder builder;
    private Document doc;
    private XPath path;
    private JTextField expression;
    private JTextField result;
    private JTextArea docText;
    private JComboBox typeCombo;
}
