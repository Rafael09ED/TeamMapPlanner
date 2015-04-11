package version3.mapDrawer.canvasItemTracking.canvasGroups;

import version3.mapDrawer.canvasItemTracking.canvasItems.CanvasItem;
import version3.mapDrawer.canvasItemTracking.informationStorage.BoundingBox2D;

import java.util.List;

/**
 * Created by Rafael on 4/7/2015.
 */
public abstract class CanvasGroup {

    public CanvasGroup() {}
    public abstract BoundingBox2D getBoundingBox();

    // Obviously I want to use as much memory and garbage collection as possible
    // if you look at the following's implementation.

    public abstract List<CanvasItem> getAllSubCanvasItems();
    public abstract List<CanvasGroup> getAllSubCanvasGroups();
    public abstract List<CG_Folder> getAllSubFolders();

    // methods that only relate to the current class's storage.
    public abstract List<CanvasGroup> getCanvasGroups();
    public abstract List<CG_Folder> getFolders();
    public abstract List<CG_Layer> getLayers();

}
