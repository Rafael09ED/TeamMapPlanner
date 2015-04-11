package version3.mapDrawer.canvasItemTracking.canvasGroups;

import version3.mapDrawer.canvasItemTracking.canvasItems.CanvasItem;
import version3.mapDrawer.canvasItemTracking.informationStorage.BoundingBox2D;

import java.util.List;

/**
 * Created by Rafael on 4/7/2015.
 */
public interface CanvasGroup extends CanvasItem {


     BoundingBox2D getBoundingBox();

    // Obviously I want to use as much memory and garbage collection as possible
    // if you look at the following's implementation.

    List<CanvasItem> getAllSubCanvasItems();
    List<CanvasGroup> getAllSubCanvasGroups();
    List<CG_Folder> getAllSubFolders();

    // methods that only relate to the current class's storage.
    List<CanvasGroup> getCanvasGroups();
    List<CG_Folder> getFolders();
    List<CG_Layer> getLayers();

}
