package version4.mapDrawer.gui;

import version4.mapDrawer.rendering.CanvasItemRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rafael on 4/14/2015.
 */
public class MapDrawerGUI extends JFrame {
    private final Canvas canvas;
    private CanvasItemRenderer canvasItemRenderer;
    private ItemTrackerInterface itemTrackerInterface;

    public MapDrawerGUI(String title, ItemTrackerInterface itemTrackerInterface, CanvasItemRenderer canvasItemRenderer) {
        super(title);

        // setting fields
        this.itemTrackerInterface = itemTrackerInterface;
        this.canvasItemRenderer = canvasItemRenderer;

        // initialization
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 500);

        // create Canvas to render to
        canvas = new Canvas();
        add(canvas);

        // show GUI
        setVisible(true);
    }

}
