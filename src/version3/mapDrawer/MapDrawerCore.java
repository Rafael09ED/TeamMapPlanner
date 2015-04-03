package version3.mapDrawer;

import version3.mapDrawer.GUI.GUICanvasItemInterface;
import version3.mapDrawer.canvasItemTracking.CanvasItemTracker;
import version3.mapDrawer.rendering.MapDrawerRenderer;

/**
 * Created by ADMIN on 4/2/2015.
 */
public class MapDrawerCore {
    public static void main(String[] args) {
        new MapDrawerCore();
    }

    /*
     * The main layout:
     * http://i.imgur.com/dKA9b7Q.jpg
     */

    private CanvasItemTracker itemTracker;
    private GUICanvasItemInterface itemGUIInterface;
    private MapDrawerRenderer mapDrawerRenderer;

    public MapDrawerCore() {
        itemTracker = new CanvasItemTracker();

        itemGUIInterface = new GUICanvasItemInterface(itemTracker);
        mapDrawerRenderer = new MapDrawerRenderer(itemTracker);



    }

}
