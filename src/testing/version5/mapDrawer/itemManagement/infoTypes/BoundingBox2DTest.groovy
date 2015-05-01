package version5.mapDrawer.itemManagement.infoTypes

/**
 * Created by Rafael on 5/1/2015.
 */
class BoundingBox2DTest extends groovy.util.GroovyTestCase {
    BoundingBox2D boundingBox2D;
    BoundingBox2D boundingBox2D1
    @Override
    void setUp() {
        super.setUp();
        boundingBox2D = new BoundingBox2D(new Point2D(), new Point2D());
        boundingBox2D1 = new BoundingBox2D(new Point2D(10,10), new Point2D(20,20));
    }

    void testGetTopLeft() {
        println("TopLeft");
        println(boundingBox2D.getTopLeft())
        println(boundingBox2D1.getTopLeft())
    }

    void testGetBottomRight() {
        println("BottomRight");
        println(boundingBox2D.getBottomRight())
        println(boundingBox2D1.getBottomRight())
    }

    void testGetWidth() {
        println("getWidth");
        println(boundingBox2D.getWidth())
        println(boundingBox2D1.getWidth())
    }

    void testGetHeight() {
        println("getHeight");
        println(boundingBox2D.getHeight())
        println(boundingBox2D1.getHeight())
    }

    void testGetIntBoxWidth() {
        println("intWidth");
        println(boundingBox2D.getIntBoxWidth())
        println(boundingBox2D1.getIntBoxWidth())
    }

    void testGetIntBoxHeight() {
        println("intHeight");
        println(boundingBox2D.getIntBoxHeight())
        println(boundingBox2D1.getIntBoxHeight())
    }

    void testAddBoundingBox() {
        println("testAdd");
        println(boundingBox2D)
        println(boundingBox2D1)
        println("adding");
        println(boundingBox2D.addBoundingBox(boundingBox2D1))
        println("checking");
        println(boundingBox2D)
        println(boundingBox2D1)
    }

    void testToString() {
        println("testString");
        println(boundingBox2D)
        println(boundingBox2D1)
    }

    void testGetOffsetAsInt() {
        println("getOffsetASInt");
        println(boundingBox2D.getOffsetAsInt())
        println(boundingBox2D1.getOffsetAsInt())
    }
}
