package version4.mapDrawer;

import version4.SETTINGS.GENERAL_SETTINGS;
import version4.mapDrawer.gui.ItemTrackerInterface;
import version4.mapDrawer.gui.MapDrawerGUI;
import version4.mapDrawer.itemTracker.CanvasItemTracker;
import version4.mapDrawer.rendering.CanvasItemRenderer;

/**
 * Created by Rafael on 4/14/2015.
 */
public class MapDrawerCore {
    public static void main(String[] args){
        new MapDrawerCore();
    }

    private MapDrawerGUI mapDrawerGUI;
    private CanvasItemTracker canvasItemTracker;
    private CanvasItemRenderer canvasItemRenderer;
    private ItemTrackerInterface itemTrackerInterface;

    public MapDrawerCore() {
        // declare fields and pass construct classes
        declarations();
        
    }


    private void declarations() {
        canvasItemTracker = new CanvasItemTracker();
        itemTrackerInterface = new ItemTrackerInterface(canvasItemTracker);
        canvasItemRenderer = new CanvasItemRenderer(canvasItemTracker, itemTrackerInterface);
        mapDrawerGUI = new MapDrawerGUI(GENERAL_SETTINGS.PROGRAM_TITLE, itemTrackerInterface, canvasItemRenderer);
    }
}
