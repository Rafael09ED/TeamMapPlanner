package version3.mapDrawer.GUI;

import version3.mapDrawer.canvasItemTracking.CanvasItemTracker;
import version3.mapDrawer.canvasItemTracking.canvasGroups.CanvasGroup;
import version3.mapDrawer.rendering.Graphics2D.CGGraphicsData;
import version3.mapDrawer.rendering.MapDrawerRenderer;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Rafael on 4/3/2015.
 */
public class GUICanvasItemInterface {
    private CanvasItemTracker itemTracker;
    private MapDrawerRenderer drawerRenderer;
    private boolean totalRenderRequired = true;
    private HashMap<CanvasGroup, CGGraphicsData> layerToBufferedImageHM;
    //TODO: use CGGraphicsData to store bufferedImage and other info for rendering
    // Also rename

    public GUICanvasItemInterface(CanvasItemTracker itemTracker, MapDrawerRenderer drawerRenderer) {
        this.itemTracker = itemTracker;
        this.drawerRenderer = drawerRenderer;
        layerToBufferedImageHM = new HashMap<>();
    }

    public void setG2D(Graphics2D graphics) {
        drawerRenderer.setG2D(graphics);
    }

}
