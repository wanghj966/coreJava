package xml.parseXML;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;

import javax.swing.tree.DefaultTreeCellRenderer;

import java.awt.*;
import javax.swing.*;
public class DOMTreeCellRender extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);    //To change body of overridden methods use File | Settings | File Templates.
    }
    public static JPanel elementPanel(Element e) {return null;}
    public static String characterString(CharacterData node){return null;}
}
