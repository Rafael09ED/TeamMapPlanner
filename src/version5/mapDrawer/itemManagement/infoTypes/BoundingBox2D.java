package version5.mapDrawer.itemManagement.infoTypes;


/**
 * Created by Rafael on 4/3/2015.
 */
public class BoundingBox2D {
    private Point2D topLeft, bottomRight;

    public BoundingBox2D(Point2D pointOne, Point2D pointTwo) {
        // finds and sets the topLeft and bottomRight points from the two points passed
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

    /**
     * Changes the bounding box to incorporate the passed bounding box.
     * @param boundingBox   The bounding box that will be incorporated into the bounding box
     */
    public void  addBoundingBox(BoundingBox2D boundingBox) {
        // If the point currently stored is a place holder, ignore it's data
        // Else find the new point that represents what it is (Max or Min)

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

    /**
     * Gets the offset point (top left) of the bounding box as an int
     * @return  top left of bounding box as a truncated int
     */
    public Point2D getOffsetAsInt() {
        return new Point2D((int) topLeft.getX(),(int) topLeft.getY());
    }
}
