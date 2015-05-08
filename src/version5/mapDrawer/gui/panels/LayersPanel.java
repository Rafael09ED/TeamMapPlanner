package version5.mapDrawer.gui.panels;

import version5.mapDrawer.gui.GuiFrame;
import version5.mapDrawer.gui.panels.tree.CanvasGroupTree;
import version5.mapDrawer.gui.panels.tree.CanvasGroupTreeNode;
import version5.mapDrawer.gui.panels.tree.CanvasGroupTreeWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rafael on 5/1/2015.
 */
public class LayersPanel extends JPanel {
    private final GuiFrame guiFrame;
    private final CanvasGroupTreeWrapper canvasGroupsTreeWrapper;
    private final CanvasGroupTreeNode rootNode;

    //Todo
    public LayersPanel(GuiFrame guiFrame) {
        this.guiFrame = guiFrame;
        setLayout(new BorderLayout());

        rootNode = new CanvasGroupTreeNode(guiFrame.groupDataInterface.getRootWrapper());
        canvasGroupsTreeWrapper = new CanvasGroupTreeWrapper(new CanvasGroupTree(rootNode),guiFrame.taskManager,guiFrame.groupDataInterface);

        { // Temp Tester Button
            JButton jButton = new JButton("Refresh");
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvasGroupsTreeWrapper.remakeTree();
                }
            });
            add(jButton, BorderLayout.NORTH);
        }

        add(canvasGroupsTreeWrapper.getTree(), BorderLayout.CENTER);
    }

    public CanvasGroupWrapper getSelectedGroup() {
        return canvasGroupsTreeWrapper.getSelectedGroup();
    }
}
