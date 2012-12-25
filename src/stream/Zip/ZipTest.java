package stream.Zip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List.*;
import java.util.zip.*;
import java.util.*;
import java.io.*;
public class ZipTest {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ZipTestFrame frame=new ZipTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
