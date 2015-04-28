package version5.mapDrawer.rendering.optimization;

import version5.mapDrawer.itemManagement.infoTypes.Point2D;

import java.awt.image.BufferedImage;

/**
 * Created by Rafael on 4/27/2015.
 */
public class RenderData {
    private Point2D canvasOffset;
    private BufferedImage currentRender;

    public RenderData(BufferedImage currentRender, Point2D canvasOffset) {
        this.currentRender = currentRender;
        this.canvasOffset = canvasOffset;
    }

    public BufferedImage getCurrentRender() {
        return currentRender;
    }

    public void setNewRender(BufferedImage currentRender, Point2D canvasOffset) {
        this.currentRender = currentRender;
        this.canvasOffset = canvasOffset;
    }

    public Point2D getCanvasOffset() {
        return canvasOffset;
    }
}
