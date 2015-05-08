package version5.mapDrawer.gui.panels.tree.data;

import version5.mapDrawer.gui.panels.tree.CanvasGroupTreeNode;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupWrapper;

import javax.swing.tree.TreePath;

/**
 * Created by Rafael on 5/6/2015.
 */
public class NodeData {
    private final CanvasGroupTreeNode canvasGroupNode;
    private final TreePath rightClickedOnPath;

    public NodeData(CanvasGroupTreeNode canvasGroupNode, TreePath rightClickedOnPath) {
        this.canvasGroupNode = canvasGroupNode;
        this.rightClickedOnPath = rightClickedOnPath;
    }

    public CanvasGroupTreeNode getCanvasGroupNode() {
        return canvasGroupNode;
    }

    public TreePath getRightClickedOnPath() {
        return rightClickedOnPath;
    }
    public CanvasGroupWrapper getNodeGroupWrapper(){
        return canvasGroupNode.getCanvasGroupWrapper();
    }
}