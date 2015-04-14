package oldCode.version3.mapDrawer.rendering;

import oldCode.version3.mapDrawer.canvasItemTracking.informationStorage.Point2D;

/**
 * Created by Rafael on 4/3/2015.
 */
public interface RenderingInterface {
    // To be expanded when new object types have to be rendered.
    public void drawLine(Point2D startPoint, Point2D endPoint, double lineStroke, int EndType);
}
