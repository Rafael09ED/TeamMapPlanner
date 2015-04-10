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

    // Obliviously I want to use as much memory and garbage collection as possible
    // if you look at the following's implementation.

    public abstract List<CanvasItem> getAllCanvasItems();
    public abstract List<CanvasGroup> getAllSubCanvasGroups();
    public abstract List<CG_Folder> getAllSubFolders();
    public abstract List<CG_Layer> getAllLayers();

}
