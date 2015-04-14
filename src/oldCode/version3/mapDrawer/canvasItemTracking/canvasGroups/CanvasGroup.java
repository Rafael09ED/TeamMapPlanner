package oldCode.version3.mapDrawer.canvasItemTracking.canvasGroups;

import oldCode.version3.mapDrawer.canvasItemTracking.canvasItems.CanvasItem;
import oldCode.version3.mapDrawer.canvasItemTracking.informationStorage.BoundingBox2D;
import oldCode.version3.mapDrawer.rendering.DataTracking.HierarchyTracker;

import java.util.List;

/**
 * Created by Rafael on 4/7/2015.
 */
public interface CanvasGroup extends CanvasItem {


     BoundingBox2D getBoundingBox();

    // Obviously I want to use as much memory and garbage collection as possible
    // if you look at the following's implementation.

    //render immediate sub layers
    //void renderCurrentLayer(RenderingInterface renderTo); // NOT Needed anti-OO

    //Methods for finding all sub items
    List<CanvasItem> getAllSubCanvasItems();
    List<CanvasGroup> getAllSubCanvasGroups();
    List<CG_Folder> getAllSubFolders();
    List<CG_Layer> getAllSubLayers();

    // methods that only relate to the current class's storage.
    List<CanvasGroup> getCanvasGroups();
    List<CG_Folder> getFolders();
    List<CG_Layer> getLayers();

    // methods for finding stuff
    boolean findHierarchy(HierarchyTracker hierarchyTracker);

}
