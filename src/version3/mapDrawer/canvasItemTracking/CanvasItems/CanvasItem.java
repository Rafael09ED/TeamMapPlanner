package version3.mapDrawer.canvasItemTracking.canvasItems;

import version3.mapDrawer.canvasItemTracking.informationStorage.BoundingBox2D;
import version3.mapDrawer.rendering.RenderingInterface;

/**
 * Created by Rafael on 4/3/2015.
 */
public interface CanvasItem{

    BoundingBox2D getBoundingBox();
    void render(RenderingInterface renderTo);

}
