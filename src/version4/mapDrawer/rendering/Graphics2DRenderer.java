package version4.mapDrawer.rendering;

import version4.SETTINGS.RENDER_SETTINGS;
import version4.mapDrawer.itemTracker.canvasItems.informationStorage.Point2D;
import version4.mapDrawer.rendering.tools.RenderingInterface;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Rafael on 4/3/2015.
 */
public class Graphics2DRenderer implements RenderingInterface {
    private Graphics2D g;
    private Point2D offset;

    public Graphics2DRenderer() {
        offset = new Point2D(0);
    }

    public void setGraphicsToRenderTo(Graphics2D g) {
        this.g = g;
        RENDER_SETTINGS.SetRenderAA(g);


    }

    @Override
    public void drawLine(Point2D startPoint, Point2D endPoint, double lineStroke, Color color, int EndType) {
        startPoint = Point2D.pointWithOffset(startPoint, offset);
        endPoint = Point2D.pointWithOffset(endPoint, offset);

        Stroke jStroke = new BasicStroke((float) lineStroke, EndType, BasicStroke.JOIN_ROUND);
        g.setStroke(jStroke);
        g.setColor(color);
        g.drawLine((int) startPoint.getX(), (int) startPoint.getY(), (int) endPoint.getX(), (int) endPoint.getY());
    }

    @Override
    public void drawImage(Point2D position, BufferedImage image, double scale, int alpha) {
        g.drawImage(image, (int) position.getX() ,(int) position.getY(), (int) (image.getWidth()*scale), (int) (image.getHeight()*scale), null);
    }

    public void setOffset(Point2D offset) {
        this.offset = offset;
    }
}
