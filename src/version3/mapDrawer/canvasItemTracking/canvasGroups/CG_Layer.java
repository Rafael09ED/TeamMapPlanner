package version3.mapDrawer.canvasItemTracking.canvasGroups;

import version3.mapDrawer.canvasItemTracking.canvasItems.CanvasItem;
import version3.mapDrawer.canvasItemTracking.informationStorage.BoundingBox2D;
import version3.mapDrawer.rendering.DataTracking.HierarchyTracker;
import version3.mapDrawer.rendering.RenderingInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rafael on 4/7/2015.
 */
public class CG_Layer implements CanvasGroup {
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
    public List<CG_Layer> getAllSubLayers() {
        List<CG_Layer> list = new LinkedList<CG_Layer>();
        list.add(this);
        return list;
    }

    @Override
    public List<CanvasGroup> getCanvasGroups() {
        //TODO
        return null;
    }

    @Override
    public List<CG_Folder> getFolders() {
        return new ArrayList<CG_Folder>();
    }

    @Override
    public List<CG_Layer> getLayers() {
        return new ArrayList<>();
    }

    @Override
    public boolean findHierarchy(HierarchyTracker hierarchyTracker) {
        return false;
    }

    @Override
    public void render(RenderingInterface renderTo){
        for (int i = 0; i < canvasItems.size(); i++) {
            canvasItems.get(i).render(renderTo);
        }
    }
    public void addCanvasItem(CanvasItem canvasItem){
        canvasItems.add(canvasItem);
    }
    public void removeCanvasItem(CanvasItem canvasItem){
        canvasItems.remove(canvasItem);
    }
}
