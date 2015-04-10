package version3.mapDrawer.canvasItemTracking.canvasItems;

import version3.mapDrawer.canvasItemTracking.informationStorage.BoundingBox2D;
import version3.mapDrawer.rendering.RenderingInterface;

/**
 * Created by Rafael on 4/3/2015.
 */
public abstract class CanvasItem{
    public CanvasItem() {
    }
    abstract BoundingBox2D getBoundingBox();
    abstract void render(RenderingInterface renderTo);
   //abstract CanvasItem getItems(); // wtf does this do?
}
