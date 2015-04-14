package version3.mapDrawer.canvasItemTracking;

import version3.mapDrawer.canvasItemTracking.canvasGroups.CG_Folder;
import version3.mapDrawer.canvasItemTracking.canvasGroups.CG_Layer;
import version3.mapDrawer.rendering.DataTracking.HierarchyTracker;

/**
 * Created by Rafael on 4/3/2015.
 */
public class CanvasItemTracker extends CG_Folder {

    public CanvasItemTracker() {
        canvasGroups.add(new CG_Layer());
    }

    public void update() {
        //TODO: Update
    }

    @Override
    public boolean findHierarchy(HierarchyTracker hierarchyTracker) {
        boolean found = false;
        if (hierarchyTracker.hierarchyOf() == this){
            hierarchyTracker.addParent(this);
            found = true;
        } else {
           found = super.findHierarchy(hierarchyTracker);
        }

        return found;
    }
}
