package version2.mapDrawer.graphicsObjects;

import java.awt.*;

/**
 * Created by Rafael on 3/23/2015.
 */
public class Line extends GraphicsObject {
    private final double EPSILON = .0000001;


    private Color lineColor;
    private double lineStroke;
    private Point lineStartPoint, lineEndPoint;
    private double lineSlope;

    public Line(Point startPoint, Point endPoint) {
        this.lineStartPoint = startPoint;
        this.lineEndPoint = endPoint;
        lineColor       =   Color.BLACK;
        lineStroke      =   1;
    }
    public Line(Point startPoint, Point endPoint, Color lineColor, double lineStroke){
        this.lineStartPoint = startPoint;
        this.lineEndPoint = endPoint;

        this.lineColor      =   lineColor;
        this.lineStroke     =   lineStroke;
    }
    public static double solveSlope(Point startPointIn, Point endPointIn){
        double slope;

        if (startPointIn.x == endPointIn.x || startPointIn.y == endPointIn.y ){
            slope = 0;
        } else {
            slope = ((((double) (endPointIn.y - startPointIn.y))/((double) (endPointIn.x - startPointIn.x))));
        }

        return slope;
    }

    public boolean isPointCollinear(Point pointIn) {

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

        // if the slope is close to each other, and the distance does not effect the slope difference,
        if ( ( Math.abs(solveSlope(pointIn, lineEndPoint)) - lineSlope ) * distanceFormula(lineStartPoint, pointIn) < EPSILON){
            return true;
        }
        return false;
    }
    public void changeEndPoint(Point endPoint){
        this.lineEndPoint = endPoint;
        lineSlope = solveSlope(lineStartPoint, lineEndPoint);
    }

    public static double distanceFormula(Point pointOne, Point pointTwo){
        if (pointOne.x == pointTwo.x)
            return pointOne.y - pointTwo.y;
        if (pointOne.y == pointTwo.y)
            return pointOne.x - pointTwo.x;

        return Math.sqrt(Math.pow(pointOne.x - pointTwo.x, 2) + Math.pow(pointOne.y - pointTwo.y, 2) );
    }

    @Override
    public void paint(Graphics g) {
        g.drawLine(lineStartPoint.x, lineStartPoint.y, lineEndPoint.x, lineEndPoint.y);
    }
}
