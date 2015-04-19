package version4.mapDrawer.gui.dataManagement;

import version4.SETTINGS.NAMING_SETTINGS;
import version4.mapDrawer.itemTracker.canvasGroups.CanvasGroup;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by Rafael on 4/18/2015.
 */
public class canvasGroupTreeNode extends DefaultMutableTreeNode{
    private final CanvasGroup canvasGroup;
    public canvasGroupTreeNode(CanvasGroup canvasGroup) {
        super(canvasGroup, true);
        this.canvasGroup = canvasGroup;
    }

    @Override
    public String toString() {
        if (canvasGroup == null || canvasGroup.getName() == null){
            System.err.println("Node object or node name was null");
            return "Error: " + super.toString();
        } else if (canvasGroup.getName().equals("")) {
            return NAMING_SETTINGS.untitledName;
        }
        return canvasGroup.getName();
    }

    public CanvasGroup getCanvasGroup() {
        return canvasGroup;
    }
}
