package version5.mapDrawer.gui.panels.tree;

import javax.swing.*;

/**
 * Created by Rafael on 5/6/2015.
 */
public class CanvasGroupTree extends JTree {

    public CanvasGroupTree(CanvasGroupTreeNode rootNode) {
        super(rootNode);
    }
    public void expandAllNodes() {

        int j = getRowCount();
        int i = 0;
        while(i < j) {
            expandRow(i);
            i += 1;
            j = getRowCount();
        }
    }
}
