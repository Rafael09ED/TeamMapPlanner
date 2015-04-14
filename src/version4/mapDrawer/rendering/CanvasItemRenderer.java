package version4.mapDrawer.rendering;

import version4.mapDrawer.gui.ItemTrackerInterface;
import version4.mapDrawer.itemTracker.CanvasItemTracker;

/**
 * Created by Rafael on 4/14/2015.
 */
public class CanvasItemRenderer {
    private CanvasItemTracker canvasItemTracker;
    private ItemTrackerInterface itemTrackerInterface;

    public CanvasItemRenderer(CanvasItemTracker canvasItemTracker, ItemTrackerInterface itemTrackerInterface) {

        this.canvasItemTracker = canvasItemTracker;
        this.itemTrackerInterface = itemTrackerInterface;
    }
}
