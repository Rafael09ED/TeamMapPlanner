package version4.mapDrawer.gui;

import version4.mapDrawer.rendering.CanvasItemRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Rafael on 4/14/2015.
 */
public class MapDrawerGUI extends JFrame {
    private final Canvas canvas;
    private BufferStrategy strategy;
    private CanvasItemRenderer canvasItemRenderer;
    private ItemTrackerInterface itemTrackerInterface; // Will be used for tools and such

    public MapDrawerGUI(String title, ItemTrackerInterface itemTrackerInterface, CanvasItemRenderer canvasItemRenderer) {
        super(title);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Unsupported Look and Feel, Using Default Java");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        canvas.setVisible(true);

        // Set Canvas Buffer Strategy
        setStrategy();
    }

    private void setStrategy() {
        canvas.createBufferStrategy(2);
        strategy = canvas.getBufferStrategy();
    }

    public void render() {

        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        //Graphics2D g  = (Graphics2D) canvas.getGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        canvasItemRenderer.setGraphics2d(g);
        canvasItemRenderer.renderAll();

        g.setColor(Color.BLUE);
        g.drawString("lolWorld", 10, 10);

        g.dispose();
        strategy.show();
    }
}
