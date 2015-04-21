package oldCode.version4.mapDrawer.itemTracker;

import oldCode.version4.SETTINGS.NAMING_SETTINGS;
import oldCode.version4.mapDrawer.itemTracker.canvasGroups.CanvasGroupFolder;

/**
 * Created by Rafael on 4/14/2015.
 */
public class CanvasItemTracker extends CanvasGroupFolder{
    public CanvasItemTracker() {
        super(null);
        setName(NAMING_SETTINGS.rootFolderName);
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
