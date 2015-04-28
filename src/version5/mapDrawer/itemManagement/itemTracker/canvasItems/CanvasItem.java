package version5.mapDrawer.itemManagement.itemTracker.canvasItems;

import version5.mapDrawer.itemManagement.infoTypes.BoundingBox2D;
import version5.mapDrawer.rendering.RenderingCommands;

/**
 * Created by Rafael on 4/25/2015.
 */
public interface CanvasItem {
    BoundingBox2D getBoundingBox();
    void render(RenderingCommands renderingCommands);
}
