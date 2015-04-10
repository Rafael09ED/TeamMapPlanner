package version3.mapDrawer.canvasItemTracking;

import version3.mapDrawer.canvasItemTracking.canvasGroups.CG_Layer;
import version3.mapDrawer.canvasItemTracking.canvasGroups.CanvasGroup;
import version3.mapDrawer.canvasItemTracking.canvasItems.CanvasItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rafael on 4/3/2015.
 */
public class CanvasItemTracker {
    private List<CanvasGroup> canvasGroups;

    public CanvasItemTracker() {
        canvasGroups = new ArrayList<>();
        canvasGroups.add(new CG_Layer());
    }

    public void update() {
        //TODO: Update
    }

    public List<CanvasItem> getAllCanvasItems() {
        List<CanvasItem> allItems = new LinkedList<>();
        for (int i = 0; i < canvasGroups.size(); i++) {
            allItems.addAll(canvasGroups.get(i).getAllCanvasItems());
        }
        return allItems;
    }

    public List<CanvasGroup> getAllCanvasGroups(){

        List<CanvasGroup> allItems = new LinkedList<>();
        for (int i = 0; i < canvasGroups.size(); i++) {
            allItems.addAll(canvasGroups.get(i).getAllSubCanvasGroups());
        }
        return allItems;
    }
}
