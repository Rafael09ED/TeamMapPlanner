package oldCode.version4.mapDrawer.rendering.tools;

import oldCode.version4.mapDrawer.itemTracker.canvasItems.informationStorage.Point2D;

import java.awt.image.BufferedImage;

/**
 * Created by Rafael on 4/14/2015.
 */
public class CanvasGroupGraphicsData {
    private BufferedImage render;
    private Point2D canvasOffset;

    public CanvasGroupGraphicsData() {
        render = new BufferedImage(10,10,BufferedImage.TYPE_INT_ARGB);
        canvasOffset = new Point2D();
    }

    public void setRender(BufferedImage render){
        this.render = render;
    }

    public void setCanvasOffset(Point2D canvasOffset) {
        this.canvasOffset = canvasOffset;
    }

    public Point2D getCanvasOffset() {
        return canvasOffset;
    }

    public BufferedImage getRender(){
        return render;
    }
}
