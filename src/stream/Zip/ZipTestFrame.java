package stream.Zip;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipTestFrame extends JFrame {
    public ZipTestFrame(){
        setTitle("ZipTest");
        setSize(DEFAULT_WIDTH,DEFAULT_HIGHT);
        // add the menu and the open and exit menu items
        JMenuBar menuBar=new JMenuBar();
        JMenu menu=new JMenu("File");
        JMenuItem openItem=new JMenuItem("open");
        menu.add(openItem);
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser=new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                int r=chooser.showOpenDialog(ZipTestFrame.this);
                if(r==JFileChooser.APPROVE_OPTION){
                    zipname=chooser.getSelectedFile().getPath();
                    fileCombo.removeAllItems();
                    scanZipFile();
                }
            }
        });
        JMenuItem exitItem=new JMenuItem("exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuBar.add(menu);
        setJMenuBar(menuBar);

        //add the text area and combo box
        fileText=new JTextArea();
        fileCombo=new JComboBox();
        fileCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadZipFile((String) fileCombo.getSelectedItem());
            }
        });
        add(fileCombo,BorderLayout.SOUTH) ;
        add(new JScrollPane(fileText),BorderLayout.CENTER);
    }
    public void scanZipFile(){
        new SwingWorker<Void,String>(){
                protected Void doInBackground() throws Exception
                {
                    System.out.println(zipname);
                    ZipInputStream zin=new ZipInputStream(new FileInputStream(zipname));
                    ZipEntry entry;
                    while((entry=zin.getNextEntry())!=null){
                        publish(entry.getName());
                        System.out.println(entry.getName());
                        zin.closeEntry();
                    }
                    zin.close();
                    return null;
                }
                protected void  process(List<String> names){
                    System.out.println("---------");
                    for (String name:names){
                        fileCombo.addItem(name);
                    }
                 }
        }.execute();
    }
    public void loadZipFile(final String name){
        fileCombo.setEnabled(false);
        fileText.setText("");
        new SwingWorker<Void,Void>(){
            protected Void doInBackground() throws Exception{
               try{
                   ZipInputStream zin=new ZipInputStream(new FileInputStream(zipname));
                   ZipEntry entry;
                   //find entry with matching name in archive
                   while((entry=zin.getNextEntry())!=null){
                           if(entry.getName().equals(name)){
                               Scanner in=new Scanner(zin);
                               while(in.hasNextLine()){
                                   fileText.append(in.nextLine());
                                   fileText.append("\n");
                               }
                           }
                       zin.closeEntry();
                   }   zin.close();

               }catch (IOException e){
                   e.printStackTrace();
               }
                return null;
            }
            protected void done(){
                fileCombo.setEnabled(true);
            }
        }.execute();
    }

    public static final int DEFAULT_WIDTH=400;
    public static final int DEFAULT_HIGHT=300;
    private JComboBox fileCombo;
    private JTextArea fileText;
    private String zipname;
}
