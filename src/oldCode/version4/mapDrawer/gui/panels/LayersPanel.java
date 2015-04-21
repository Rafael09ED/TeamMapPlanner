package oldCode.version4.mapDrawer.gui.panels;

import oldCode.version4.mapDrawer.gui.ItemTrackerInterface;
import oldCode.version4.mapDrawer.gui.dataManagement.CanvasGroupTreeNode;
import oldCode.version4.mapDrawer.gui.panels.TreeRightClick.CGRightClickMenu;
import oldCode.version4.mapDrawer.itemTracker.canvasGroups.CanvasGroup;
import oldCode.version4.mapDrawer.rendering.CanvasItemRenderer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 4/16/2015.
 */
public class LayersPanel extends JPanel{
    private final CanvasItemRenderer canvasItemRenderer;
    private final ItemTrackerInterface itemTrackerInterface;
    private final CGRightClickMenu CGRightClickMenu;
    private final JTree canvasGroupsTree;
    private final CanvasGroupTreeNode rootNode;
    private CanvasGroup canvasGroupSelected;

    public LayersPanel(CanvasItemRenderer canvasItemRenderer, ItemTrackerInterface itemTrackerInterface) {
        this.canvasItemRenderer = canvasItemRenderer;
        this.itemTrackerInterface = itemTrackerInterface;

        rootNode = new CanvasGroupTreeNode(itemTrackerInterface.getRoot());
        canvasGroupsTree = new JTree(rootNode);
        CGRightClickMenu = new CGRightClickMenu(canvasGroupsTree, itemTrackerInterface, this);

        setLayout(new BorderLayout());
        add(new JScrollPane(canvasGroupsTree));
    }
    public void setCanvasGroupSelected(CanvasGroup canvasGroup){
        canvasGroupSelected = canvasGroup;
    }

    public void update(){
        if (itemTrackerInterface.hasLayerOrderChanged(false)){
            rebuildTree();
            ( (DefaultTreeModel) canvasGroupsTree.getModel() ).nodeChanged(rootNode);
            revalidate();
        }
    }
    private void rebuildTree(){
        rootNode.removeAllChildren();
        buildTreeFromNode(rootNode);
    }
    private void buildTreeFromNode(CanvasGroupTreeNode parentCanvasNode){
        List<CanvasGroup> list = new ArrayList<>();
        parentCanvasNode.getCanvasGroup().getChildren(list);
        for (int i = 0; i < list.size(); i++) {
            CanvasGroup canvasGroup = list.get(i);
            parentCanvasNode.add(new CanvasGroupTreeNode(canvasGroup));

        }
    }
}
