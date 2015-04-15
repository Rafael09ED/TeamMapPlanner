package version4.mapDrawer.itemTracker.canvasGroups;


import version4.mapDrawer.itemTracker.canvasItems.CanvasItem;
import version4.mapDrawer.itemTracker.canvasItems.informationStorage.BoundingBox2D;
import version4.mapDrawer.itemTracker.canvasItems.informationStorage.Point2D;

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
        boundingBox = new BoundingBox2D(new Point2D(0), new Point2D(1));
    }
    public List<CanvasItem> getCanvasItems(){
        return canvasItems;
    }

    public void addCanvasItem(CanvasItem canvasItem){
        canvasItems.add(canvasItem);
        boundingBox.addBoundingBox(canvasItem.getBoundingBox());
    }
    public boolean removeCanvasItem(CanvasItem canvasItem){
        boolean removed = canvasItems.remove(canvasItem);
        if (removed) {
            boundingBox = new BoundingBox2D(new Point2D(), new Point2D());

            for (int i = 0; i < canvasItems.size(); i++) {
                CanvasItem item = canvasItems.get(i);

                boundingBox.addBoundingBox(item.getBoundingBox());
            }
        }
        return removed;
    }

    @Override
    public void getAllSubLayers(List<CanvasGroupLayer> allSubLayers) {
        allSubLayers.add(this);
    }
    @Override
    public CanvasGroupFolder getParent() {
        return parent;
    }

    public void setParent(CanvasGroupFolder parent){
        this.parent = parent;
    }

    public BoundingBox2D getBoundingBox() {
        return boundingBox;
    }

}
