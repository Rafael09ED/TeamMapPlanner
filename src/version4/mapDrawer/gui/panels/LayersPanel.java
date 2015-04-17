package version4.mapDrawer.gui.panels;

import version4.mapDrawer.gui.ItemTrackerInterface;
import version4.mapDrawer.rendering.CanvasItemRenderer;

import javax.swing.*;

/**
 * Created by Rafael on 4/16/2015.
 */
public class LayersPanel extends JPanel{
    private final CanvasItemRenderer canvasItemRenderer;
    private final ItemTrackerInterface itemTrackerInterface;

    public LayersPanel(CanvasItemRenderer canvasItemRenderer, ItemTrackerInterface itemTrackerInterface) {
        this.canvasItemRenderer = canvasItemRenderer;
        this.itemTrackerInterface = itemTrackerInterface;
    }
}
