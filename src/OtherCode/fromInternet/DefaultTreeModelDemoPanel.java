package OtherCode.fromInternet;
/**
 * The MIT License
 * Copyright (c) 2007 Collin Fagan
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * DefaultTreeModelDemoPanel
 * This class is intended to demonstrate the right and wrong ways to manipulate a DefaultTreeModel.
 *
 * @author Collin Fagan
 */
public class DefaultTreeModelDemoPanel extends JPanel {
    private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
    private DefaultMutableTreeNode child1Node = new DefaultMutableTreeNode("Child 1");
    private DefaultMutableTreeNode child2Node = new DefaultMutableTreeNode("Child 2");
    private DefaultMutableTreeNode child3Node = new DefaultMutableTreeNode("Child 3");
    private JTree tree = new JTree(rootNode);
    private JPopupMenu menu = new JPopupMenu("Operations");

    /**
     *
     */
    public DefaultTreeModelDemoPanel() {
        setLayout(new BorderLayout());
        add(new JScrollPane(tree));

        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
        tree.addMouseListener(treeMenuClicked);

        rootNode.add(child1Node);
        rootNode.add(child2Node);
        rootNode.add(child3Node);

        JMenuItem removeNode = new JMenuItem("Remove from node - [Will NOT update correctly]");
        removeNode.setToolTipText("Will NOT update correctly");
        removeNode.addActionListener(removeNodeFromNode);

        JMenuItem removeModel = new JMenuItem("Remove from model");
        removeModel.setToolTipText("Will update correctly");
        removeModel.addActionListener(removeNodeFromModel);

        JMenuItem addNumbers = new JMenuItem("Add odd/even numbered children");
        addNumbers.setToolTipText("Example of a bulk update");
        addNumbers.addActionListener(addEvenOddNumbers);

        JMenuItem longTextNoUpdate = new JMenuItem("Set long text - without update - [Will NOT update correctly]");
        longTextNoUpdate.setToolTipText("Will NOT update correctly");
        longTextNoUpdate.addActionListener(modifyUserObjectWithoutUpdate);

        JMenuItem longTextWithUpdate = new JMenuItem("Set long text - with update");
        longTextWithUpdate.setToolTipText("Will update correctly");
        longTextWithUpdate.addActionListener(modifyUserObjectWithUpdate);

        menu.add(removeNode);
        menu.add(removeModel);
        menu.addSeparator();
        menu.add(addNumbers);
        menu.addSeparator();
        menu.add(longTextNoUpdate);
        menu.add(longTextWithUpdate);
    }

    /**
     * MouseAdapter to popup the menu the tree menu
     */
    private MouseAdapter treeMenuClicked = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                int row = tree.getClosestRowForLocation(e.getX(), e.getY());
                tree.setSelectionPath(tree.getPathForRow(row));
                menu.show(tree, e.getX(), e.getY());
            }
        }
    };
    /**
     * Example of the WRONG way to remove a single node from a tree. This will not update the tree correctly.
     */
    private ActionListener removeNodeFromNode = new ActionListener() {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent e) {
            TreePath currentSelection = tree.getSelectionPath();
            if (currentSelection != null) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) currentSelection.getLastPathComponent();
                node.removeFromParent();
            }
        }
    };
    /**
     * Example of the CORRECT way to remove a single node from a tree.
     */
    private ActionListener removeNodeFromModel = new ActionListener() {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent e) {
            TreePath currentSelection = tree.getSelectionPath();
            if (currentSelection != null) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) currentSelection.getLastPathComponent();
                DefaultTreeModel model = ((DefaultTreeModel) tree.getModel());
                model.removeNodeFromParent(node);
            }
        }
    };
    /**
     * Example of adding children in bulk, then updating the tree.
     */
    private ActionListener addEvenOddNumbers = new ActionListener() {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent e) {
            TreePath currentSelection = tree.getSelectionPath();
            if (currentSelection != null) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) currentSelection.getLastPathComponent();
                DefaultTreeModel model = ((DefaultTreeModel) tree.getModel());
                DefaultMutableTreeNode odd = new DefaultMutableTreeNode("odd");
                node.add(odd);
                DefaultMutableTreeNode even = new DefaultMutableTreeNode("even");
                node.add(even);
                for (int i = 0; i < 50; i++) {
                    if (i % 2 == 0) {
                        even.add(new DefaultMutableTreeNode(i));
                    } else {
                        odd.add(new DefaultMutableTreeNode(i));
                    }
                }
                // The above changes may not seem to take effect until nodeStructureChanged is called
                model.nodeStructureChanged(node);
            }
        }
    };
    /**
     * Example of the setting the user object and NOT updating the model. This will NOT update the tree correctly.
     */
    private ActionListener modifyUserObjectWithoutUpdate = new ActionListener() {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent e) {
            TreePath currentSelection = tree.getSelectionPath();
            if (currentSelection != null) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) currentSelection.getLastPathComponent();
                node.setUserObject("THIS IS A VERY LOOOOOOOOOOOOOOOOOOOOONG STRING");
            }
        }
    };
    /**
     * Example of setting the user object and updating the model. This WILL update the tree correctly.
     */
    private ActionListener modifyUserObjectWithUpdate = new ActionListener() {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent e) {
            TreePath currentSelection = tree.getSelectionPath();
            if (currentSelection != null) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) currentSelection.getLastPathComponent();
                node.setUserObject("THIS IS A VERY LOOOOOOOOOOOOOOOOOOOOONG STRING");
                DefaultTreeModel model = ((DefaultTreeModel) tree.getModel());
                model.nodeChanged(node);
            }
        }
    };

    /**
     * Main program entry point, starts the demo.
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame demoFrame = new JFrame("DefaultTreeModel Demo");
                demoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                demoFrame.setContentPane(new DefaultTreeModelDemoPanel());
                demoFrame.setSize(300, 600);
                // centers the frame on the screen
                demoFrame.setLocationRelativeTo(null);
                demoFrame.setVisible(true);
            }
        });
    }
}