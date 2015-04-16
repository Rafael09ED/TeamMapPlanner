package version4.mapDrawer.rendering.tools;


import version4.mapDrawer.itemTracker.canvasItems.informationStorage.Point2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Rafael on 4/3/2015.
 */
public interface RenderingInterface {
    // To be expanded when new object types have to be rendered.

    void drawLine(Point2D startPoint, Point2D endPoint, double lineStroke, Color color, int EndType);
    void drawImage(Point2D position, BufferedImage image, double scale, int alpha);
}
