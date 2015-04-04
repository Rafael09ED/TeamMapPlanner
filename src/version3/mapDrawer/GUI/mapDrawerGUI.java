package version3.mapDrawer.GUI;

import version3.mapDrawer.rendering.MapDrawerRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ADMIN on 4/3/2015.
 */
public class MapDrawerGUI extends JFrame{
    private MapDrawerRenderer drawerRenderer;
    private GUICanvasItemInterface itemInterface;
    private Canvas canvas;

    public MapDrawerGUI(String title, MapDrawerRenderer drawerRenderer, GUICanvasItemInterface itemInterface) throws HeadlessException {
        super(title);

        this.drawerRenderer = drawerRenderer;
        this.itemInterface = itemInterface;

        canvas = new Canvas();
        setSize(1000,500);
        setVisible(true);
    }

    public void render() {
        //TODO: set up rendering code
    }
}
