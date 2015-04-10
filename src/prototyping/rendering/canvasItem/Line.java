package prototyping.rendering.canvasItem;

import prototyping.rendering.graphicsRendering.GraphicsRenderer;
import prototyping.rendering.graphicsRendering.IRenderable;

import java.awt.*;

/**
 * Created by Rafael on 4/1/2015.
 */
public class Line implements IRenderable {
    private Point startPoint, endPoint;

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
    @Override
    public void render(GraphicsRenderer graphicsRenderer) {
        graphicsRenderer.drawLine(startPoint, endPoint);
    }
}
