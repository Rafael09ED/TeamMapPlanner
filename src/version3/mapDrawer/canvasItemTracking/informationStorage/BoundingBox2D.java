package version3.mapDrawer.canvasItemTracking.informationStorage;


/**
 * Created by Rafael on 4/3/2015.
 */
public class BoundingBox2D {
    private Point2D topLeft, bottomRight;

    public BoundingBox2D(Point2D pointOne, Point2D pointTwo) {
        topLeft = new Point2D(Math.min(pointOne.getX(), pointTwo.getX()), Math.min(pointOne.getY(), pointTwo.getY()));
        bottomRight = new Point2D(Math.max(pointOne.getX(), pointTwo.getX()), Math.max(pointOne.getY(), pointTwo.getY()));
    }

    public Point2D getTopLeft() {
        return topLeft;
    }

    public Point2D getBottomRight() {
        return bottomRight;
    }
}
