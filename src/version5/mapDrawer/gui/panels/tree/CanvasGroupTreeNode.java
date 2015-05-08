package version5.mapDrawer.gui.panels.tree;

import version5.mapDrawer.gui.panels.tree.jtreeRendering.IconNode;
import version5.mapDrawer.gui.panels.tree.data.IconGetter;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupWrapper;

/**
 * Created by Rafael on 5/6/2015.
 */
public class CanvasGroupTreeNode extends IconNode {
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