package version5.mapDrawer.rendering;

import version5.mapDrawer.itemManagement.infoTypes.Point2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Rafael on 4/27/2015.
 */
public interface RenderingCommands {
    void drawLine(Point2D startPoint, Point2D endPoint, double lineStroke, Color color, int EndType);
    void drawImage(Point2D position, BufferedImage image, double scale, int alpha);

}
