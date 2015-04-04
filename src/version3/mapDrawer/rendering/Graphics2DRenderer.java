package version3.mapDrawer.rendering;

import version3.mapDrawer.canvasItemTracking.CanvasItems.CanvasItemTypes;
import version3.mapDrawer.canvasItemTracking.informationStorage.Point2D;

import java.awt.*;

/**
 * Created by ADMIN on 4/3/2015.
 */
public class Graphics2DRenderer implements RenderingInterface {
    private Graphics2D g;

    public Graphics2DRenderer() {
    }

    public void setGraphicsToRenderTo(Graphics2D g) {
        this.g = g;
    }

    public void drawLine(Point2D startPoint, Point2D endPoint, double lineStroke, int EndType) {

        Stroke jStroke = new BasicStroke((float)lineStroke, EndType, BasicStroke.JOIN_ROUND );
        g.setStroke(jStroke);
        g.drawLine((int) startPoint.getX(), (int) startPoint.getY(), (int) endPoint.getX(), (int) endPoint.getY());
    }

}
