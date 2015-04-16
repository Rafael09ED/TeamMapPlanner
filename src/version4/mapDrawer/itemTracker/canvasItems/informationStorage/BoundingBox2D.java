package version4.mapDrawer.itemTracker.canvasItems.informationStorage;


/**
 * Created by Rafael on 4/3/2015.
 */
public class BoundingBox2D {
    private Point2D topLeft, bottomRight;

    public BoundingBox2D(Point2D pointOne, Point2D pointTwo) {
        topLeft = new Point2D(Math.min(pointOne.getX(), pointTwo.getX()), Math.min(pointOne.getY(), pointTwo.getY()));
        bottomRight = new Point2D(Math.max(pointOne.getX(), pointTwo.getX()), Math.max(pointOne.getY(), pointTwo.getY()));
    }

    public BoundingBox2D() {
        topLeft = new Point2D();
        bottomRight = new Point2D();
    }

    public Point2D getTopLeft() {
        return topLeft;
    }

    public Point2D getBottomRight() {
        return bottomRight;
    }

    public double getWidth(){
        return bottomRight.getX() - topLeft.getX();
    }
    public double getHeight(){
        return bottomRight.getY() - topLeft.getY();
    }
    public int getIntBoxWidth(){
        return (int) (bottomRight.getX() - topLeft.getX() ) + 1;
    }
    public int getIntBoxHeight(){
        return (int) (bottomRight.getY() - topLeft.getY() ) + 1;
    }

    public void  addBoundingBox(BoundingBox2D boundingBox) {
        if (topLeft.isPlaceholder()) {
            topLeft = boundingBox.getTopLeft();
        } else {
            topLeft = new Point2D(Math.min(topLeft.getX(), boundingBox.getTopLeft().getX()),
                    Math.min(topLeft.getY(), boundingBox.getTopLeft().getY()));
        }
        if (bottomRight.isPlaceholder()) {
            bottomRight = boundingBox.getBottomRight();
        } else {
            bottomRight = new Point2D(Math.max(bottomRight.getX(), boundingBox.getBottomRight().getX()),
                    Math.max(bottomRight.getY(), boundingBox.getBottomRight().getY()));
        }
    }

    @Override
    public String toString() {
        return "BoundingBox2D:( TopLeft: ( " + topLeft.toString() + " ) BottomRight: ( " + bottomRight.toString() + " ))";
    }

    public Point2D getOffsetAsInt() {
        return new Point2D((int) topLeft.getX(),(int) topLeft.getY());
    }
}
