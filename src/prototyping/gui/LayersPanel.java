package prototyping.gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 * Created by Rafael on 4/17/2015.
 */
public class LayersPanel extends JFrame {

    public static void main(String[] args){
        LayersPanel layersPanel = new LayersPanel();
        layersPanel.setVisible(true);
    }

    public static final String name = "GUI Layers Prototype";
    public LayersPanel(){
        super(name);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTree tree = new JTree();
        tree.setModel(getModel());
        JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane);

    }

    private TreeModel getModel() {
        // http://java.dzone.com/news/secret-life-defaulttreemodel

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("JTree");
        DefaultMutableTreeNode      parent;

        parent = new DefaultMutableTreeNode("Folder One");
        root.add(parent);
        parent.add(new DefaultMutableTreeNode("Layer One"));
        parent.add(new DefaultMutableTreeNode("Layer Two"));
        DefaultMutableTreeNode child = new DefaultMutableTreeNode("Folder Two");
        child.add(new DefaultMutableTreeNode("Layer Three"));
        parent.add(child);
        parent.add(new DefaultMutableTreeNode("Layer 4"));

        parent = new DefaultMutableTreeNode("Folder Three");
        root.add(parent);
        return new DefaultTreeModel(root);
    }
}
