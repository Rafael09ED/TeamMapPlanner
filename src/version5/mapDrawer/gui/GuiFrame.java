package version5.mapDrawer.gui;

import oldCode.version4.mapDrawer.itemTracker.canvasItems.informationStorage.BoundingBox2D;
import oldCode.version4.mapDrawer.itemTracker.canvasItems.informationStorage.Point2D;
import version5.mapDrawer.gui.actionManagement.GuiActionManager;
import version5.mapDrawer.gui.panels.LayersTree;
import version5.mapDrawer.gui.tools.ToolsManager;
import version5.mapDrawer.interfacing.DataGrabber;
import version5.mapDrawer.interfacing.taskManagment.TaskManager;
import version5.mapDrawer.rendering.optimization.RenderData;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Rafael on 4/30/2015.
 */
public class GuiFrame extends JFrame {
    private final MapDrawerCanvas renderCanvas;
    public final DataGrabber dataGrabber;
    public final TaskManager taskManager;
    private final ToolsManager toolsManager;
    private BufferStrategy bufferStrategy;
    private ToolsManager.ToolButtonBar toolButtonBar;
    private LayersTree layerTreePanel;
    private GuiActionManager guiActionManager;

    public GuiFrame(String title, DataGrabber dataGrabber, TaskManager taskManager) {
        super(title);
        this.dataGrabber = dataGrabber;
        this.taskManager = taskManager;
        try {
            // set the system's Look and Feel
            UIManager.setLookAndFeel(UIManager
                    .getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e){
        } catch (Exception e) {
        }
        setSize(1000, 500);
        setLocation(100, 100);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        renderCanvas = new MapDrawerCanvas();
        add(renderCanvas, BorderLayout.CENTER);
        setVisible(true);
        renderCanvas.createBufferStrategy(2);
        bufferStrategy = renderCanvas.getBufferStrategy();

        createLayerTreePanel();
        renderCanvas();

        toolsManager = new ToolsManager(this);
        toolButtonBar = toolsManager.getToolButtonBar();
        this.add(toolButtonBar, BorderLayout.WEST);

        guiActionManager = new GuiActionManager(this,toolsManager);
        renderCanvas.addMouseListener(guiActionManager.canvasClickActionListener);
        revalidate();
    }


    private void createLayerTreePanel() {
        layerTreePanel = new LayersTree(this);
        add(layerTreePanel, BorderLayout.EAST);
    }
    public void renderCanvas(){
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        renderCanvas.paint(g);
        g.dispose();
        bufferStrategy.show();
    }

    public BoundingBox2D getFrameBoundingBox() {
        return new BoundingBox2D(new Point2D(0), new Point2D(renderCanvas.getWidth(), renderCanvas.getHeight()));
    }


    private class MapDrawerCanvas extends Canvas{
        public MapDrawerCanvas() {
        }

        @Override
        public void paint(Graphics graphics) {
            super.paint(graphics);
            Graphics2D g = (Graphics2D)graphics;
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            RenderData renderData = dataGrabber.renderCanvas();
            g.drawImage(renderData.getCurrentRender(), (int) renderData.getBoundingBoxOnCanvas().getTopLeft().getX(), (int) renderData.getBoundingBoxOnCanvas().getTopLeft().getY(), null);
        }
    }

}
