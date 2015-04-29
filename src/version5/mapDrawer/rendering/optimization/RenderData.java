package version5.mapDrawer.rendering.optimization;

import version5.mapDrawer.itemManagement.infoTypes.BoundingBox2D;
import version5.mapDrawer.itemManagement.infoTypes.Point2D;

import java.awt.image.BufferedImage;

/**
 * Created by Rafael on 4/27/2015.
 */
public class RenderData {
    private BoundingBox2D boundingBoxOnCanvas;
    private BufferedImage currentRender;

    public RenderData(BufferedImage currentRender, BoundingBox2D boundingBoxOnCanvas) {
        this.currentRender = currentRender;
        this.boundingBoxOnCanvas = boundingBoxOnCanvas;
    }

    public BufferedImage getCurrentRender() {
        return currentRender;
    }


    public BoundingBox2D getBoundingBoxOnCanvas() {
        return boundingBoxOnCanvas;
    }
    public Point2D getOffsetFromCanvas(){
        // NOTE: IDK if get Offset As Int is what I want.
        return boundingBoxOnCanvas.getOffsetAsInt();
    }
    public int getXCanvasOffset(){
        return (int) boundingBoxOnCanvas.getTopLeft().getX();
    }
    public int getYCanvasOffset(){
        return (int) boundingBoxOnCanvas.getTopLeft().getY();
    }
}
