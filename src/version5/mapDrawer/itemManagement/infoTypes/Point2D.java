package version5.mapDrawer.itemManagement.infoTypes;

/**
 * Created by Rafael on 4/3/2015.
 */
public class Point2D {
    private double x,y;
    private boolean isPlaceholder = false;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a default point that is a placeholder
     * Being a placeholder means that it was created to not cause errors,
     * but the data does not mean anything
     */
    public Point2D() {
        x = 0;
        y = 0;
        isPlaceholder = true;
    }

    public Point2D(double number) {
        x = number;
        y = number;
    }

    public boolean isPlaceholder() {
        return isPlaceholder;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static Point2D pointWithOffset(Point2D pointIn, Point2D zeroPoint){
        return new Point2D(pointIn.getX()-zeroPoint.getX(), pointIn.getY()-zeroPoint.getY());
    }
    public static Point2D pointWithIntegerOffset(Point2D pointIn, Point2D zeroPoint){
        return new Point2D((int)pointIn.getX()-(int)zeroPoint.getX(), (int)pointIn.getY()-(int)zeroPoint.getY());
    }
    @Override
    public String toString() {
        return "Point2D: ( " + x + " , " + y +" )";
    }
}
