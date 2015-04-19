package version4.mapDrawer.gui.panels.TreeRightClick;

import version4.mapDrawer.gui.ItemTrackerInterface;
import version4.mapDrawer.gui.dataManagement.CanvasGroupTreeNode;
import version4.mapDrawer.gui.panels.LayersPanel;
import version4.mapDrawer.itemTracker.canvasGroups.CanvasGroupFolder;
import version4.mapDrawer.itemTracker.canvasGroups.CanvasGroupLayer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CGRightClickMenu extends JPopupMenu {
    //TODO: Finish Class Next, This is where layers can be added until a real GUI is born
    private CanvasGroupTreeNode rightClickedOn;
    private TreePath rightClickedOnPath;
    private JTree canvasGroupsTree;
    private final ItemTrackerInterface itemTrackerInterface;

    public CGRightClickMenu(JTree canvasGroupsTree, ItemTrackerInterface itemTrackerInterface, LayersPanel layersPanel) {
        super();
        this.canvasGroupsTree = canvasGroupsTree;
        this.itemTrackerInterface = itemTrackerInterface;
        canvasGroupsTree.addMouseListener(treeMenuClicked);
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
        /*
        itemToAdd = new JMenuItem("Show Layer Render");
        itemToAdd.setToolTipText("Opens a dialog showing the render of the layer");
        itemToAdd.addActionListener(showLayerRender);
        itemToAdd.setEnabled(false);
        add(itemToAdd); // TODO: Implement */
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
                rightClickedOnPath = canvasGroupsTree.getPathForLocation(e.getX(), e.getY());
                rightClickedOn = (CanvasGroupTreeNode) rightClickedOnPath.getLastPathComponent();

                if (rightClickedOn.getCanvasGroup() instanceof CanvasGroupFolder){
                    setFolderMenu();
                } else if (rightClickedOn.getCanvasGroup() instanceof CanvasGroupLayer) {
                    setLayerMenu();
                }

                show(canvasGroupsTree, e.getX(), e.getY());
            }
        }
    };
    private ActionListener showLayerRender = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("Layer Render");
            //TODO: Eventually Clean and implement
        }
    };
    private ActionListener deleteLayer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (rightClickedOn != null) {
                itemTrackerInterface.deleteCanvasGroup(rightClickedOn.getCanvasGroup());
                ((DefaultTreeModel) canvasGroupsTree.getModel()).removeNodeFromParent(rightClickedOn);
                rightClickedOn = null;
            }
        }
    };
    private ActionListener selectLayer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            canvasGroupsTree.setSelectionPath(rightClickedOnPath);
            //setSelectedLayer
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
            itemTrackerInterface.addLayer(new CanvasGroupFolder((CanvasGroupFolder) rightClickedOn.getCanvasGroup()));
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