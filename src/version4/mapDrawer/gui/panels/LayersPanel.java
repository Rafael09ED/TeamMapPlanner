package version4.mapDrawer.gui.panels;

import version4.mapDrawer.gui.ItemTrackerInterface;
import version4.mapDrawer.gui.dataManagement.canvasGroupTreeNode;
import version4.mapDrawer.itemTracker.canvasGroups.CanvasGroup;
import version4.mapDrawer.rendering.CanvasItemRenderer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 4/16/2015.
 */
public class LayersPanel extends JPanel{
    private final CanvasItemRenderer canvasItemRenderer;
    private final ItemTrackerInterface itemTrackerInterface;
    private final LayersMenu layersMenu;

    private final JTree canvasGroupsTree;
    private final canvasGroupTreeNode rootNode;

    public LayersPanel(CanvasItemRenderer canvasItemRenderer, ItemTrackerInterface itemTrackerInterface) {
        this.canvasItemRenderer = canvasItemRenderer;
        this.itemTrackerInterface = itemTrackerInterface;

        layersMenu = new LayersMenu();
        rootNode = new canvasGroupTreeNode(itemTrackerInterface.getRoot());
        canvasGroupsTree = new JTree(rootNode);

        setLayout(new BorderLayout());
        add(new JScrollPane(canvasGroupsTree));
    }

    public void update(){
        if (itemTrackerInterface.hasLayerOrderChanged(false)){
            rebuildTree();
            ( (DefaultTreeModel) canvasGroupsTree.getModel() ).nodeChanged(rootNode);
        }
    }
    private void rebuildTree(){
        rootNode.removeAllChildren();
        buildTreeFromNode(rootNode);
        revalidate();

    }
    private void buildTreeFromNode(canvasGroupTreeNode parentCanvasNode){
        List<CanvasGroup> list = new ArrayList<>();
        parentCanvasNode.getCanvasGroup().getChildren(list);
        for (int i = 0; i < list.size(); i++) {
            CanvasGroup canvasGroup = list.get(i);
            parentCanvasNode.add(new canvasGroupTreeNode(canvasGroup));

        }
    }
    /**
     * Menu that pops up when you right click on a node.
     */
    private class LayersMenu extends JPopupMenu{
        //TODO: Finish Class Next, This is where layers can be added until a real GUI is born
        public LayersMenu() {
            super();
        }

        private void setLayerMenu(){
            removeAll();
            JMenuItem itemToAdd;

            itemToAdd = new JMenuItem("Select Layer");
            itemToAdd.setToolTipText("Selects the layer to use in the canvas");
            itemToAdd.addActionListener(selectLayer);
            add(itemToAdd);

            addSeparator();

            itemToAdd = new JMenuItem("Delete Layer");
            itemToAdd.setToolTipText("Deletes the layer and it's contents");
            itemToAdd.addActionListener(deleteLayer);
            add(itemToAdd);

            itemToAdd = new JMenuItem("Show Layer Render");
            itemToAdd.setToolTipText("Opens a dialog showing the render of the layer");
            itemToAdd.addActionListener(showLayerRender);
            itemToAdd.setEnabled(false);
            add(itemToAdd);
        }
        private MouseAdapter treeMenuClicked = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                maybeShowPopup(e);
            }

            public void mouseReleased(MouseEvent e) {
                maybeShowPopup(e);
            }

            private void maybeShowPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    //TODO: Implement
                }
            }
        };
        private ActionListener showLayerRender = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Action Performed
            }
        };
        private ActionListener deleteLayer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Action Performed
            }
        };
        private ActionListener selectLayer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Action Performed
            }
        };


        private void setFolderMenu(){
            removeAll();

            JMenuItem itemToAdd;

            itemToAdd = new JMenuItem("Create New Layer");
            itemToAdd.setToolTipText("Creates a new layer");
            itemToAdd.addActionListener(createNewLayer);
            add(itemToAdd);

            itemToAdd = new JMenuItem("Create New Folder");
            itemToAdd.setToolTipText("Creates a new folder");
            itemToAdd.addActionListener(createNewFolder);
            add(itemToAdd);

            addSeparator();

            itemToAdd = new JMenuItem("Delete Folder");
            itemToAdd.setToolTipText("Deletes the folder and the contents in it");
            itemToAdd.addActionListener(deleteFolder);
            add(itemToAdd);
        }
        private ActionListener createNewFolder = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Action Performed
            }
        };
        private ActionListener createNewLayer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Action Performed
            }
        };
        private ActionListener deleteFolder = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Action Performed
            }
        };
    }
}
