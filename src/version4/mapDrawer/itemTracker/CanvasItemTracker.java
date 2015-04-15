package version4.mapDrawer.itemTracker;

import version4.mapDrawer.itemTracker.canvasGroups.CanvasGroupFolder;

/**
 * Created by Rafael on 4/14/2015.
 */
public class CanvasItemTracker extends CanvasGroupFolder{
    public CanvasItemTracker() {
        super(null);
    }

    @Override
    public CanvasGroupFolder getParent() {
        return this;
    }

    @Override
    public void setParent(CanvasGroupFolder parent) {
        //Don't Set Parent
    }
}
