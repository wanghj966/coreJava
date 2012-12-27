package xml.parseXML;

import org.w3c.dom.Document;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Created with IntelliJ IDEA.
 * User: wanghj
 * Date: 12-12-27
 * Time: 下午3:51
 * To change this template use File | Settings | File Templates.
 */
public class DOMTreeModel implements TreeModel {
    public DOMTreeModel(Document doc){
        this.doc=doc;
    }
    @Override
    public Object getRoot() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getChild(Object parent, int index) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getChildCount(Object parent) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isLeaf(Object node) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    private Document doc;
}
