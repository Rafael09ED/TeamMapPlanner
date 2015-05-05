package version5.mapDrawer.itemManagement.itemTracker.canvasGroups;

import version5.mapDrawer.itemManagement.infoTypes.BoundingBox2D;
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
    private BoundingBox2D boundingBox2D;

    public CanvasGroupLayer() {
        canvasItems = new ArrayList<>();
        layerNumber = layerCounter++;
        boundingBox2D = new BoundingBox2D();
    }
    public boolean removeCanvasItem(CanvasItem canvasItem){
        return canvasItems.remove(canvasItem);
    }
    public void addCanvasItem(CanvasItem canvasItem){
        canvasItems.add(canvasItem);
        boundingBox2D.addBoundingBox(canvasItem.getBoundingBox());
    }
    public List<CanvasItem> getCanvasItems(){
        return canvasItems;
    }

    @Override
    public void getAllSubLayersOrdered(List<CanvasGroupLayer> allSubLayers) {
        allSubLayers.add(this);
    }

    @Override
    public void getChildren(List<CanvasGroup> immediateChildren) {
        return;
    }

    @Override
    public CanvasGroupFolder findParent(CanvasGroup parent) {
        return null;
    }

    public int getLayerNumber() {
        return layerNumber;
    }

    public BoundingBox2D getBoundingBox() {
        return boundingBox2D;
    }
}
