package version3.mapDrawer.GUI;

import version3.mapDrawer.rendering.MapDrawerRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ADMIN on 4/3/2015.
 */
public class mapDrawerGUI extends JFrame{
    private MapDrawerRenderer drawerRenderer;
    private GUICanvasItemInterface itemInterface;

    public mapDrawerGUI(String title, MapDrawerRenderer drawerRenderer, GUICanvasItemInterface itemInterface) throws HeadlessException {
        super(title);

        this.drawerRenderer = drawerRenderer;
        this.itemInterface = itemInterface;


    }
}
