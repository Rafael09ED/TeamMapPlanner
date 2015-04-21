package oldCode.version4.mapDrawer.itemTracker.canvasItems;

import oldCode.version4.mapDrawer.itemTracker.canvasItems.informationStorage.BoundingBox2D;
import oldCode.version4.mapDrawer.rendering.tools.IRenderable;

/**
 * Created by Rafael on 4/14/2015.
 */
public interface CanvasItem extends IRenderable{
    BoundingBox2D getBoundingBox();
}
