package oldCode.version4.mapDrawer.gui.dataManagement;

import oldCode.version4.SETTINGS.NAMING_SETTINGS;
import oldCode.version4.mapDrawer.itemTracker.canvasGroups.CanvasGroup;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by Rafael on 4/18/2015.
 */
public class CanvasGroupTreeNode extends DefaultMutableTreeNode{
    private final CanvasGroup canvasGroup;
    public CanvasGroupTreeNode(CanvasGroup canvasGroup) {
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
