package version5.mapDrawer.itemManagement.itemTracker.canvasGroups;

import version5.mapDrawer.itemManagement.itemTracker.canvasItems.CanvasItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 4/25/2015.
 */
public class CanvasGroupLayer implements CanvasGroup{

    private static int layerCounter = 0;
    protected final List<CanvasItem> canvasItems;
    private final int layerNumber;

    public CanvasGroupLayer() {
        canvasItems = new ArrayList<>();
        layerNumber = layerCounter++;
    }
    public boolean removeCanvasItem(CanvasItem canvasItem){
        return canvasItems.remove(canvasItem);
    }
    public void addCanvasItem(CanvasItem canvasItem){
        canvasItems.add(canvasItem);
    }

    @Override
    public void getAllSubLayersOrdered(List<CanvasGroupLayer> allSubLayers) {
        allSubLayers.add(this);
    }

    @Override
    public void getChildren(List<CanvasGroup> immediateChildren) {
        return;
    }

    public int getLayerNumber() {
        return layerNumber;
    }
}
