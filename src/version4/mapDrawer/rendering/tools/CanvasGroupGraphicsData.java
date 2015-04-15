package version4.mapDrawer.rendering.tools;

import version4.mapDrawer.itemTracker.canvasItems.informationStorage.Point2D;

import java.awt.image.BufferedImage;

/**
 * Created by Rafael on 4/14/2015.
 */
public class CanvasGroupGraphicsData {
    private BufferedImage render;
    private Point2D topLeftCorner;

    public CanvasGroupGraphicsData() {
        render = new BufferedImage(10,10,BufferedImage.TYPE_INT_ARGB);
        topLeftCorner = new Point2D();
    }

    public void setRender(BufferedImage render){
        this.render = render;
    }

    public void setTopLeftCorner(Point2D topLeftCorner) {
        this.topLeftCorner = topLeftCorner;
    }

    public Point2D getTopLeftCorner() {
        return topLeftCorner;
    }

    public BufferedImage getRender(){
        return render;
    }
}
