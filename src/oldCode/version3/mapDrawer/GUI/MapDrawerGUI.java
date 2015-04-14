package oldCode.version3.mapDrawer.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rafael on 4/3/2015.
 */
public class MapDrawerGUI extends JFrame{
    private GUICanvasItemInterface itemInterface;
    private Canvas canvas;

    public MapDrawerGUI(String title, GUICanvasItemInterface itemInterface) {
        super(title);

        this.itemInterface = itemInterface;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        canvas = new Canvas();
        setSize(1000,500);
        add(canvas);
        setVisible(true);

    }

    public void render() {
        //TODO: set up rendering code
        itemInterface.setG2D((Graphics2D) canvas.getGraphics());

        itemInterface.TESTINGMETHOD_GENERATELINES();

        itemInterface.renderAll();
    }
}
