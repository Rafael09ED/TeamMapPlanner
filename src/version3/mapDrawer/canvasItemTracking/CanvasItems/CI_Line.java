package version3.mapDrawer.canvasItemTracking.canvasItems;

import version3.mapDrawer.canvasItemTracking.informationStorage.BoundingBox2D;
import version3.mapDrawer.canvasItemTracking.informationStorage.Point2D;
import version3.mapDrawer.rendering.RenderingInterface;
import version3.mapDrawer.settings.GENERAL_SETTINGS;

import java.awt.*;

/**
 * Created by Rafael on 4/3/2015.
 */
public class CI_Line extends CanvasItem {
    private Point2D startPoint, endPoint;
    private BoundingBox2D boundingBox;
    private double lineLength, lineSlope;
    private double lineStroke;
    private int StrokeEnd = BasicStroke.CAP_ROUND;

    private double totalSlopeModification = 0;

    public CI_Line(Point2D startPoint, Point2D endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        boundingBox = new BoundingBox2D(startPoint, endPoint);
        lineStroke = 1;
    }


    @Override
    BoundingBox2D getBoundingBox() {
        return null;
    }

    @Override
    public void render(RenderingInterface renderTo) {
        renderTo.drawLine(startPoint, endPoint, lineStroke, StrokeEnd);
    }

    //TODO: add change end point

    public boolean isPointAnExtension(Point2D pointIn) {

        // if it makes the line shorter:
        if (lineLength > distanceFormula(startPoint, pointIn)) {
            return false;
        }

        //if the point is a dot
        if (startPoint.getX() == endPoint.getX() && startPoint.getY() == endPoint.getY()) {
            return true;
        }

        // if the slope is zero or undefined.
        if (startPoint.getY() == endPoint.getY() && endPoint.getY() == pointIn.getY()){
            return true;
        }
        if (startPoint.getX() == endPoint.getX() && endPoint.getX() == pointIn.getX()){
            return true;
        }

        //System.out.println(( Math.abs(solveSlope(pointIn, lineEndPoint)) - lineSlope ) * (distanceFormula(lineStartPoint, pointIn)));
        // if the slope is close to each other, and the distance does not effect the slope difference,
        if (totalSlopeModification > GENERAL_SETTINGS.EPSILON){
            return false;
        }
        if ( ( Math.abs(solveSlope(pointIn, endPoint) - lineSlope )) * (distanceFormula(startPoint, pointIn) + 1) < GENERAL_SETTINGS.EPSILON){
            return true;
        }
        return false;
    }

    public static double solveSlope(Point2D startPointIn, Point2D endPointIn){
        double slope;
        // if the slope is zero, undefined, or a point
        if (startPointIn.getX() == endPointIn.getX() || startPointIn.getY() == endPointIn.getY() ){
            // the slope is 0;
            slope = 0;
        } else {
            slope = ((( (endPointIn.getY() - startPointIn.getY()))/( (endPointIn.getX() - startPointIn.getX()))));
        }
        //System.out.println(slope);
        return slope;
    }

    public static double distanceFormula(Point2D pointOne, Point2D pointTwo){

        if (pointOne.getX() == pointTwo.getX())
            return Math.abs(pointOne.getY() - pointTwo.getY());

        if (pointOne.getY() == pointTwo.getY())
            return Math.abs(pointOne.getX() - pointTwo.getX());

        double distance = Math.sqrt(Math.pow((pointOne.getX() - pointTwo.getX()), 2)
                + Math.pow((pointOne.getY() - pointTwo.getY()), 2) );

        //System.out.println(distance);
        return distance;
    }
}
