package version5.mapDrawer.gui.panels;

import version5.mapDrawer.gui.GuiFrame;
import version5.mapDrawer.interfacing.CanvasGroupTypeActionable;
import version5.mapDrawer.interfacing.taskManagment.tasks.Task_DeleteCanvasGroup;
import version5.mapDrawer.interfacing.taskManagment.tasks.Task_NewCanvasFolder;
import version5.mapDrawer.interfacing.taskManagment.tasks.Task_NewCanvasLayer;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupFolderWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupWrapper;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by Rafael on 5/1/2015.
 */
public class LayersTree extends JPanel {
    private final GuiFrame guiFrame;
    private final JTree canvasGroupsTree;
    private final CanvasGroupTreeNode rootNode;
    private final TreeNodeRightClickMenu rightClickMenu;

    //Todo
    public LayersTree(GuiFrame guiFrame) {
        this.guiFrame = guiFrame;
        setLayout(new BorderLayout());
        rightClickMenu = new TreeNodeRightClickMenu();
        canvasGroupsTree = new JTree();
        canvasGroupsTree.setCellRenderer(new ExtendedJTreeNodeRenderer());
        rootNode = new CanvasGroupTreeNode(guiFrame.dataGrabber.getRootWrapper());
        canvasGroupsTree.addMouseListener(treeMouseAdapter);
        remakeTree();
        {
            JButton jButton = new JButton("Refresh");
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remakeTree();
                }
            });
            add(jButton, BorderLayout.NORTH);
        }
        add(canvasGroupsTree, BorderLayout.CENTER);
        revalidate();
    }

    public void remakeTree() {
        //TODO: update tree node
        //TODO: auto expand tree
        canvasGroupsTree.removeAll();
        CanvasGroupTreeNode rootNode = new CanvasGroupTreeNode(guiFrame.dataGrabber.getRootWrapper());
        new TreeNodeAction(rootNode);
        canvasGroupsTree.setModel(new DefaultTreeModel(rootNode));
        ((DefaultTreeModel) canvasGroupsTree.getModel()).nodeChanged(rootNode);
        canvasGroupsTree.setVisible(true);
        revalidate();
        canvasGroupsTree.setBackground(Color.LIGHT_GRAY);
    }

    private MouseAdapter treeMouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            doMaybe(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            doMaybe(e);
        }

        private void doMaybe(MouseEvent e) {
            if (e.isPopupTrigger()) {
                TreePath rightClickedOnPath = canvasGroupsTree.getPathForLocation(e.getX(), e.getY());
                if (rightClickedOnPath != null) {
                    Object object = rightClickedOnPath.getLastPathComponent();
                    CanvasGroupTreeNode canvasGroupNode = ((CanvasGroupTreeNode) object);
                    rightClickMenu.show(new NodeData(canvasGroupNode,rightClickedOnPath), canvasGroupsTree, e.getX(), e.getY());
                }
            }
        }
    };

    public CanvasGroupWrapper getSelectedGroup() {
        //TODO: check for null BECAUSE THIS DOES HAPPEN
        return ((CanvasGroupTreeNode)canvasGroupsTree.getSelectionPath().getLastPathComponent()).getCanvasGroupWrapper();
    }

    private class NodeData {
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

    private class CanvasGroupTreeNode extends IconNode {
        private final CanvasGroupWrapper canvasGroupWrapper;

        public CanvasGroupTreeNode(CanvasGroupWrapper canvasGroupWrapper) {
            super(canvasGroupWrapper.getDisplayName());
            this.setIcon(new IconGetter(canvasGroupWrapper).getIcon());
            this.canvasGroupWrapper = canvasGroupWrapper;
        }

        public CanvasGroupWrapper getCanvasGroupWrapper() {
            return canvasGroupWrapper;
        }
    }

    private class TreeNodeAction implements CanvasGroupTypeActionable {
        private final CanvasGroupTreeNode parentNode;
        private final CanvasGroupTreeNode childNode;

        public TreeNodeAction(CanvasGroupTreeNode parentNode, CanvasGroupTreeNode childNode) {
            this.parentNode = parentNode;
            this.childNode = childNode;
            createTreeRecursion();
        }

        private void createTreeRecursion() {
            parentNode.add(childNode);
            childNode.getCanvasGroupWrapper().callTypeActionable(this);
        }

        public TreeNodeAction(CanvasGroupTreeNode childNode) {
            parentNode = null;
            this.childNode = childNode;
            childNode.getCanvasGroupWrapper().callTypeActionable(this);
        }

        @Override
        public void doIfCanvasLayer(CanvasGroupLayerWrapper canvasGroupLayerWrapper) {
            //If is a canvasLayer do nothing since it is already added
        }

        @Override
        public void doIfCanvasFolder(CanvasGroupFolderWrapper canvasGroupFolderWrapper) {
            // If it is a canvasFolder, get children and do a tree node action on each one.

            List<CanvasGroupWrapper> list =
                    guiFrame.dataGrabber.getChildrenOfFolderWrapped(canvasGroupFolderWrapper);
            for (int i = 0; i < list.size(); i++) {
                CanvasGroupWrapper canvasGroupWrapper = list.get(i);
                CanvasGroupTreeNode treeNode = new CanvasGroupTreeNode(canvasGroupWrapper);
                new TreeNodeAction(childNode, treeNode);
            }
        }
    }

    private class TreeNodeRightClickMenu implements CanvasGroupTypeActionable {
        private JPopupMenu menuToShow;
        private NodeData selectedNodeData;

        public TreeNodeRightClickMenu() {
           setDefaultJPopup();
        }

        private void setDefaultJPopup() {
            menuToShow = new JPopupMenu("Default");
            menuToShow.add("Error: menu not initialized");
        }

        @Override
        public void doIfCanvasLayer(CanvasGroupLayerWrapper canvasGroupLayerWrapper) {
            menuToShow = new CanvasGroupLayerMenu(canvasGroupLayerWrapper);
        }

        @Override
        public void doIfCanvasFolder(CanvasGroupFolderWrapper canvasGroupFolderWrapper) {
            menuToShow = new CanvasGroupFolderMenu(canvasGroupFolderWrapper);
        }

        public void show(NodeData nodeData, Component componentInvoker, int x, int y) {
            setDefaultJPopup();
            selectedNodeData = nodeData;
            selectedNodeData.getNodeGroupWrapper().callTypeActionable(this);
            menuToShow.show(componentInvoker, x, y);
        }

        private class CanvasGroupFolderMenu extends JPopupMenu {
            private final CanvasGroupFolderWrapper canvasGroupFolderWrapper;

            public CanvasGroupFolderMenu(CanvasGroupFolderWrapper canvasGroupFolderWrapper) {
                super("FolderPanelOperationsMenu");
                this.canvasGroupFolderWrapper = canvasGroupFolderWrapper;
                createButtons();
            }

            private void createButtons() {
                //todo
                JMenuItem itemToAdd;

                itemToAdd = createMenuItem_SelectGroup();
                itemToAdd.setToolTipText("Selects the folder");
                add(itemToAdd);

                addSeparator();

                itemToAdd = createMenuItem_DeleteGroup();
                itemToAdd.setToolTipText("Deletes the folder and it's contents");
                add(itemToAdd);

                itemToAdd = createMenuItem_ShowRenderForGroup();
                itemToAdd.setToolTipText("Opens a new window showing the render of the layer at the time of action");
                add(itemToAdd);

                addSeparator();

                itemToAdd = new JMenuItem("Create Folder");
                itemToAdd.setToolTipText("Creates a new Folder");
                itemToAdd.addActionListener(addFolderAction);
                add(itemToAdd);

                itemToAdd = new JMenuItem("Create Layer");
                itemToAdd.setToolTipText("Creates a new Layer");
                itemToAdd.addActionListener(addLayerAction);
                add(itemToAdd);
            }
            private ActionListener addFolderAction =  new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guiFrame.taskManager.doNewTask(new Task_NewCanvasFolder(canvasGroupFolderWrapper));
                    remakeTree();
                }
            };
            private ActionListener addLayerAction =  new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guiFrame.taskManager.doNewTask(new Task_NewCanvasLayer(canvasGroupFolderWrapper));
                    remakeTree();
                }
            };
        }
        private class CanvasGroupLayerMenu extends JPopupMenu {
            private final CanvasGroupLayerWrapper rightClickedOnLayerWrapped;

            public CanvasGroupLayerMenu(CanvasGroupLayerWrapper rightClickedOnLayerWrapped) {
                super("LayerPanelOperationsMenu");
                this.rightClickedOnLayerWrapped = rightClickedOnLayerWrapped;
                createButtons();
            }

            private void createButtons() {
                //TODO
                JMenuItem itemToAdd;

                itemToAdd = createMenuItem_SelectGroup();
                itemToAdd.setToolTipText("Selects the layer to use in the canvas");
                add(itemToAdd);

                addSeparator();

                itemToAdd = createMenuItem_DeleteGroup();
                itemToAdd.setToolTipText("Deletes the layer and it's contents");
                add(itemToAdd);

                itemToAdd = createMenuItem_ShowRenderForGroup();
                itemToAdd.setToolTipText("Opens a new window showing the render of the layer at the time of action");
                add(itemToAdd);

            }

        }

        private JMenuItem createMenuItem_ShowRenderForGroup() {
            JMenuItem menuItem = new JMenuItem("Show Group");
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CanvasGroupWrapper canvasGroupWrapper = selectedNodeData.getNodeGroupWrapper();
                    JFrame jFrame = new JFrame("Canvas Group: " + canvasGroupWrapper.getDisplayName() + " Render");
                    BufferedImage render = canvasGroupWrapper.getCanvasGroupRender().getRender();
                    jFrame.add(new JLabel(new ImageIcon(render)));
                    jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    jFrame.setVisible(true);
                }
            });

            return menuItem;
        }

        private JMenuItem createMenuItem_DeleteGroup() {
            JMenuItem menuItem = new JMenuItem("Delete Group");
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guiFrame.taskManager.doNewTask(new Task_DeleteCanvasGroup(selectedNodeData.getNodeGroupWrapper()));
                    remakeTree();
                }
            });
            return menuItem;
        }

        private JMenuItem createMenuItem_SelectGroup() {
            JMenuItem menuItem = new JMenuItem("Select Group");
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvasGroupsTree.setSelectionPath(selectedNodeData.getRightClickedOnPath());
                }
            });
            return menuItem;
        }
    }

}
