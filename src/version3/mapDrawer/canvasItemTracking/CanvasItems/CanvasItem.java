package version3.mapDrawer.canvasItemTracking.CanvasItems;

import version3.mapDrawer.canvasItemTracking.informationStorage.BoundingBox2D;
import version3.mapDrawer.rendering.IRenderable;

/**
 * Created by ADMIN on 4/3/2015.
 */
public abstract class CanvasItem implements IRenderable{
    public CanvasItem() {
    }
    abstract BoundingBox2D getBoundingBox();
}
