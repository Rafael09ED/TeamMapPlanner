package version5.mapDrawer.gui;


import version5.mapDrawer.gui.actionManagement.GuiActionManager;
import version5.mapDrawer.gui.panels.LayersPanel;
import version5.mapDrawer.gui.tools.ToolsManager;
import version5.mapDrawer.interfacing.GroupDataInterface;
import version5.mapDrawer.interfacing.taskManagment.TaskManager;
import version5.mapDrawer.itemManagement.infoTypes.BoundingBox2D;
import version5.mapDrawer.itemManagement.infoTypes.Point2D;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupWrapper;
import version5.mapDrawer.rendering.optimization.RenderData;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Rafael on 4/30/2015.
 */
public class GuiFrame extends JFrame {
    private final MapDrawerCanvas renderCanvas;
    public final GroupDataInterface groupDataInterface;
    public final TaskManager taskManager;
    private final ToolsManager toolsManager;
    private BufferStrategy bufferStrategy;
    private ToolsManager.ToolButtonBar toolButtonBar;
    private final LayersPanel layerTreePanel;
    private GuiActionManager guiActionManager;

    public GuiFrame(String title, GroupDataInterface groupDataInterface, TaskManager taskManager) {
        super(title);
        this.groupDataInterface = groupDataInterface;
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

        layerTreePanel = new LayersPanel(this);
        add(layerTreePanel, BorderLayout.EAST);
        renderCanvas();

        toolsManager = new ToolsManager(this);
        toolButtonBar = toolsManager.getToolButtonBar();
        this.add(toolButtonBar, BorderLayout.WEST);

        guiActionManager = new GuiActionManager(this,toolsManager);
        renderCanvas.addMouseListener(guiActionManager.canvasClickActionListener);
        revalidate();
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

    public CanvasGroupWrapper getSelectedGroup() {
        return layerTreePanel.getSelectedGroup();
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
            RenderData renderData = groupDataInterface.renderCanvas();
            g.drawImage(renderData.getRender(), (int) renderData.getBoundingBoxOnCanvas().getTopLeft().getX(), (int) renderData.getBoundingBoxOnCanvas().getTopLeft().getY(), null);
        }
    }

}
