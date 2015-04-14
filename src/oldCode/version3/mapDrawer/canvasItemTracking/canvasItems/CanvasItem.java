package oldCode.version3.mapDrawer.canvasItemTracking.canvasItems;

import oldCode.version3.mapDrawer.canvasItemTracking.informationStorage.BoundingBox2D;
import oldCode.version3.mapDrawer.rendering.IRenderable;

/**
 * Created by Rafael on 4/3/2015.
 */
public interface CanvasItem extends IRenderable {

    BoundingBox2D getBoundingBox();

}
