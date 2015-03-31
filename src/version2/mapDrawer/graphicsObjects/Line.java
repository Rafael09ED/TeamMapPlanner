package version2.mapDrawer.graphicsObjects;

import java.awt.*;

/**
 * Created by Rafael on 3/23/2015.
 */
public class Line extends GraphicsObject {
    private final double EPSILON = .00001;


    private Color lineColor;
    private double lineStroke;
    private Point lineStartPoint, lineEndPoint;
    private double lineSlope;
    private double slopeDifference = 0;
    private BasicStroke jStroke;
    private double length = 0;
    private Point maxPoint;

    public Line(Point startPoint, Point endPoint) {
        this.lineStartPoint =   startPoint;
        this.lineEndPoint   =   endPoint;
        lineColor           =   Color.BLACK;
        lineStroke          =   1;

        doEveryInit();
    }
    public Line(Point startPoint, Point endPoint, Color lineColor, double lineStroke){
        this.lineStartPoint =   startPoint;
        this.lineEndPoint   =   endPoint;
        this.lineColor      =   lineColor;
        this.lineStroke     =   lineStroke;

        doEveryInit();
    }
    private void doEveryInit(){

        length = distanceFormula(lineStartPoint,lineEndPoint);
        jStroke = new BasicStroke((float)lineStroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND );
        solveMaxPoint();

    }
    private void solveMaxPoint(){
        maxPoint = new Point((int) Math.max(lineStartPoint.getX(), lineEndPoint.getX()) + 1 , (int) Math.max(lineStartPoint.getY(), lineEndPoint.getY()) + 1);
    }

    public static double solveSlope(Point startPointIn, Point endPointIn){
        double slope;
        // if the slope is zero, undefined, or a point
        if (startPointIn.x == endPointIn.x || startPointIn.y == endPointIn.y ){
            // the slope is 0;
            slope = 0;
        } else {
            slope = ((((double) (endPointIn.y - startPointIn.y))/((double) (endPointIn.x - startPointIn.x))));
        }
        //System.out.println(slope);
        return slope;
    }

    public boolean isPointAnExtension(Point pointIn) {

        // if it makes the line shorter:
        if (length > distanceFormula(lineStartPoint, pointIn)) {
            return false;
        }

        //if the point is a dot
        if (lineStartPoint.x == lineEndPoint.x && lineStartPoint.y == lineEndPoint.y) {
            return true;
        }

        // if the slope is zero or undefined.
        if (lineStartPoint.y == lineEndPoint.y && lineEndPoint.y == pointIn.y){
            return true;
        }
        if (lineStartPoint.x == lineEndPoint.x && lineEndPoint.x == pointIn.x){
            return true;
        }

        //System.out.println(( Math.abs(solveSlope(pointIn, lineEndPoint)) - lineSlope ) * (distanceFormula(lineStartPoint, pointIn)));
        // if the slope is close to each other, and the distance does not effect the slope difference,
        if (slopeDifference>EPSILON){
            return false;
        }
        if ( ( Math.abs(solveSlope(pointIn, lineEndPoint) - lineSlope )) * (distanceFormula(lineStartPoint, pointIn) + 1) < EPSILON){
            return true;
        }
        return false;
    }
    public void changeEndPoint(Point endPoint){
        this.lineEndPoint = endPoint;
        slopeDifference += Math.abs(solveSlope(lineStartPoint, lineEndPoint)-lineSlope);
        lineSlope = solveSlope(lineStartPoint, lineEndPoint);
        length = distanceFormula(lineStartPoint,endPoint);
        solveMaxPoint();
    }

    public static double distanceFormula(Point pointOne, Point pointTwo){
        if (pointOne.x == pointTwo.x)
            return Math.abs(pointOne.y - pointTwo.y);
        if (pointOne.y == pointTwo.y)
            return Math.abs(pointOne.x - pointTwo.x);

        double distance = Math.sqrt(Math.pow((double)(pointOne.x - pointTwo.x), 2)
                + Math.pow((double)(pointOne.y - pointTwo.y), 2) );

        //System.out.println(distance);
        return distance;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(lineColor);
        ((Graphics2D) g).setStroke(jStroke);
        g.drawLine(lineStartPoint.x, lineStartPoint.y, lineEndPoint.x, lineEndPoint.y);
    }

    @Override
    public Point getMaxPoint() {
        return maxPoint;
    }

    public Point getLastPoint() {
        return lineEndPoint;
    }
}
