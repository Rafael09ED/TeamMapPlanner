package version5.mapDrawer.gui;

import version5.mapDrawer.interfacing.DataGrabber;
import version5.mapDrawer.interfacing.taskManagment.TaskManager;
import version5.mapDrawer.rendering.optimization.RenderData;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Rafael on 4/30/2015.
 */
public class CanvasFrame extends JFrame {
    private final Canvas renderCanvas;
    private final DataGrabber dataGrabber;
    private final TaskManager taskManager;
    private BufferStrategy bufferStrategy;
    private JPanel toolButtonBar;
    private JPanel layerTreePanel;

    public CanvasFrame(String title, DataGrabber dataGrabber, TaskManager taskManager) {
        super(title);
        this.dataGrabber = dataGrabber;
        this.taskManager = taskManager;

        setLayout(new BorderLayout());

        renderCanvas = new Canvas();
        add(renderCanvas, BorderLayout.CENTER);
        setVisible(true);
        renderCanvas.createBufferStrategy(2);
        bufferStrategy = renderCanvas.getBufferStrategy();

        createToolButtonBar();
        createLayerTreePanel();
    }

    private void createToolButtonBar() {
        toolButtonBar = new JPanel();
        add(toolButtonBar, BorderLayout.WEST);
    }
    private void createLayerTreePanel() {
        layerTreePanel = new JPanel();
        add(layerTreePanel, BorderLayout.EAST);
    }
    public void renderCanvas(){
        Graphics2D g = (Graphics2D) renderCanvas.getGraphics();
        g.fillRect(0, 0, getWidth(), getHeight());
        RenderData renderData = dataGrabber.renderCanvas();
        g.drawImage(renderData.getCurrentRender(),(int) renderData.getBoundingBoxOnCanvas().getTopLeft().getX(), (int) renderData.getBoundingBoxOnCanvas().getTopLeft().getY(), null);
        g.dispose();
        bufferStrategy.show();
    }
}
