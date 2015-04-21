package oldCode.version4.mapDrawer.gui;

import oldCode.version4.mapDrawer.gui.panels.LayersPanel;
import oldCode.version4.mapDrawer.rendering.CanvasItemRenderer;

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
    private LayersPanel layersPanel;

    public MapDrawerGUI(String title, ItemTrackerInterface itemTrackerInterface, CanvasItemRenderer canvasItemRenderer) {
        super(title);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Unsupported Look and Feel, Using Java's Default, Hopefully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // setting fields
        this.itemTrackerInterface = itemTrackerInterface;
        this.canvasItemRenderer = canvasItemRenderer;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 500);

        // create Canvas to render to
        canvas = new Canvas();
        add(canvas);

        setVisible(true);

        // Set Canvas Buffer Strategy
        setStrategy();

        showGroupsPanel();
    }
    private void showGroupsPanel(){
        layersPanel = new LayersPanel(canvasItemRenderer, itemTrackerInterface);
        JFrame layersFrame = new JFrame("Layers");
        layersFrame.setLocationRelativeTo(null);
        layersFrame.setSize(300,300);
        layersFrame.add(layersPanel);
        layersFrame.setVisible(true);
    }

    private void setStrategy() {
        canvas.createBufferStrategy(2);
        strategy = canvas.getBufferStrategy();
    }

    public void render() {
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        //Graphics2D g  = (Graphics2D) canvas.getGraphics();

        // sets the background to white, prevents render stacking
        g.setColor(Color.WHITE);
        // TODO: Find out if this can be optimized
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        canvasItemRenderer.setGraphics2d(g);
        canvasItemRenderer.renderAll();

        //Renders "lolWorld" for testing the buffer
        g.setColor(Color.BLUE);
        g.drawString("lolWorld", 10, 10);

        // disposes the graphics and shows the buffer
        g.dispose();
        strategy.show();
    }
    public void update(){
        layersPanel.update();
    }
}
