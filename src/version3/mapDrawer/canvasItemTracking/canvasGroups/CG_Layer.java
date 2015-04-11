package version3.mapDrawer.canvasItemTracking.canvasGroups;

import version3.mapDrawer.canvasItemTracking.canvasItems.CanvasItem;
import version3.mapDrawer.canvasItemTracking.informationStorage.BoundingBox2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 4/7/2015.
 */
public class CG_Layer extends CanvasGroup {
    private List<CanvasItem> canvasItems;

    public CG_Layer() {
        canvasItems = new ArrayList<>();
    }

    @Override
    public BoundingBox2D getBoundingBox() {
        //TODO: get Bounding Box
        return null;
    }

    @Override
    public List<CanvasItem> getAllSubCanvasItems() {
        return canvasItems;
    }

    @Override
    public List<CanvasGroup> getAllSubCanvasGroups() {
        return new ArrayList<CanvasGroup>();
    }

    @Override
    public List<CG_Folder> getAllSubFolders() {
        return new ArrayList<>();
    }

    @Override
    public List<CanvasGroup> getCanvasGroups() {
        //TODO
        return null;
    }

    @Override
    public List<CG_Folder> getFolders() {
        //TODO
        return null;
    }

    @Override
    public List<CG_Layer> getLayers() {
        //TODO
        return null;
    }

}
