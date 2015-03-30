package version2.mapDrawer.tools;

import version2.mapDrawer.DrawingCanvas;
import version2.mapDrawer.rendering.GraphicsObjectTracker;
import version2.mapDrawer.graphicsObjects.Image;
import version2.mapDrawer.tools.toolBars.PictureToolBar;
import version2.mapDrawer.tools.toolBars.ToolToolBar;

import java.awt.event.MouseEvent;

/**
 * Created by ADMIN on 3/29/2015.
 */
public class PictureTool extends MapDrawerTool {

    private final static String TOOL_STRING_NAME = "PictureTool";
    private PictureToolBar toolBar;
    private double scale = 1;
    private boolean isImageLoaded = false;
    private boolean mouseDown = false;
    private float alpha = 1f;


    public PictureTool(GraphicsObjectTracker graphicsObjectTracker, DrawingCanvas drawingCanvas) {
        super(graphicsObjectTracker, drawingCanvas);

        toolBar = new PictureToolBar(this);
    }

    @Override
    public String getToolString() {
        return TOOL_STRING_NAME;
    }

    @Override
    public String getToolDisplayName() {
        return "Image";
    }

    @Override
    public void toolSelected() {

    }

    @Override
    public void toolDeSelected() {
        drawingCanvas.SuperGUI().remove(toolBar);
        drawingCanvas.SuperGUI().revalidate();
    }

    @Override
    public void update() {
        if (isImageLoaded) {
            graphicsObjectTracker.addCurrentFrameObject(new Image(toolBar.getSelectedImage(), getMousePoint(), scale, (.5f * alpha) ));
        }
    }

    @Override
    public ToolToolBar getToolbar() {
        return toolBar;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (toolBar.getSelectedImage() != null) {
            graphicsObjectTracker.addGraphicsObject(new Image(toolBar.getSelectedImage(), getMousePoint(), scale, alpha));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void setImageLoaded(boolean isImageLoaded) {
        this.isImageLoaded = isImageLoaded;
    }
}
