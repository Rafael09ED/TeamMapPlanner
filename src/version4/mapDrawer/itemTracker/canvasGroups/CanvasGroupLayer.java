package version4.mapDrawer.itemTracker.canvasGroups;


import version4.mapDrawer.itemTracker.canvasItems.CanvasItem;
import version4.mapDrawer.itemTracker.canvasItems.informationStorage.BoundingBox2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 4/14/2015.
 */
public class CanvasGroupLayer implements CanvasGroup{

    protected final List<CanvasItem> canvasItems;
    private CanvasGroupFolder parent;
    private BoundingBox2D boundingBox;

    public CanvasGroupLayer(CanvasGroupFolder parent) {
        canvasItems = new ArrayList<>();
        this.parent = parent;
        boundingBox = new BoundingBox2D();
    }
    public List<CanvasItem> getCanvasItems(){
        return canvasItems;
    }

    /**
     * Adds a canvasItem to the canvasLayer canvasItems list
     * Makes it a part of the layer
     * @param canvasItem    The canvasItem that will be added to the Layer
     */
    public void addCanvasItem(CanvasItem canvasItem){
        canvasItems.add(canvasItem);
        boundingBox.addBoundingBox(canvasItem.getBoundingBox());
    }

    public boolean removeCanvasItem(CanvasItem canvasItem){
        boolean removed = canvasItems.remove(canvasItem);

        // if the canvasItem was in the list and hence removed
        if (removed) {
            recalculateBoundingBox();
        }
        return removed;
    }

    private void recalculateBoundingBox(){
        boundingBox = new BoundingBox2D();

        for (int i = 0; i < canvasItems.size(); i++) {
            CanvasItem item = canvasItems.get(i);
            boundingBox.addBoundingBox(item.getBoundingBox());
        }
    }

    @Override
    public void getAllSubLayersOrdered(List<CanvasGroupLayer> allSubLayers) {
        allSubLayers.add(this);
    }
    @Override
    public CanvasGroupFolder getParent() {
        return parent;
    }

    /**
     * TODO: Implement externally
     * Sets the parent of the graphics object
     * @param parent
     */
    public void setParent(CanvasGroupFolder parent){
        this.parent = parent;
    }

    public BoundingBox2D getBoundingBox() {
        return boundingBox;
    }

}
