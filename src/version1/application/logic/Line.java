package version1.application.logic;

import version1.networking.interfaces.NetworkSyncable;


import java.awt.*;
import java.util.UUID;

/**
 * Created by Rafael on 3/3/2015.
 */
public class Line implements NetworkSyncable{
    private Color color;
    private Point startPoint, endPoint;
    private double slope;
    private final double EPSILON = .0000001;

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        if (endPoint.x == startPoint.x) {
            slope = 0;
        } else {
            slope = (((endPoint.y - startPoint.y)/(endPoint.x - startPoint.x)));
        }

    }

    public void draw(Graphics g){
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor(){
        return this.color;
    }

    public boolean equalsSlope(Point pointIn) {
        if (pointIn.x == startPoint.x && endPoint.x == startPoint.x){
            return true;
        } else if (pointIn.x == startPoint.x || endPoint.x == startPoint.x) {
            return false;
        }
        if (pointIn.y == startPoint.y){
            return true;
        }
        if ( Math.abs((((double) (pointIn.y - startPoint.y))/((double) (pointIn.x - startPoint.x))) - slope ) < EPSILON){
            return true;
        }
        return false;
    }
    public Line Clone(){
        Line tempLine = new Line(startPoint, endPoint);
        return tempLine;
    }
    @Override
    public String getAuthor() {
        return "Unknown";
    }

    @Override
    public String getType() {
        return "LINE";
    }

    @Override
    public UUID getUniqueID() {
        return null;
    }
}
