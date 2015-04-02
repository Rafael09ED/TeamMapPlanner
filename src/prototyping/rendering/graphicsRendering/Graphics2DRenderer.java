package prototyping.rendering.graphicsRendering;

import prototyping.rendering.graphicsRendering.GraphicsRenderer;

import java.awt.*;

/**
 * Created by ADMIN on 4/1/2015.
 */
public class Graphics2DRenderer implements GraphicsRenderer {
    private Graphics2D g;

    public Graphics2DRenderer() {
    }

    public void setGraphics2D(Graphics2D graphics2D){
        g = graphics2D;
    }

    @Override
    public void drawLine(Point startPoint, Point endPoint) {
        g.drawLine((int) startPoint.getX(), (int) startPoint.getY(), (int) endPoint.getX(), (int) endPoint.getY());
    }
}
