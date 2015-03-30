package version2.mapDrawer.rendering;

import version2.mapDrawer.graphicsObjects.GraphicsObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ADMIN on 3/30/2015.
 */
public class GraphicsObjectLayer {
    private boolean hasLayerChanged = false;
    private List<GraphicsObject> graphicsObjects;
    private BufferedImage layerPrecomposed;
    private Point maxPoint;

    public GraphicsObjectLayer() {
        graphicsObjects = Collections.synchronizedList(new ArrayList<GraphicsObject>());
        layerPrecomposed = new BufferedImage(1,1, BufferedImage.TYPE_INT_ARGB);
    }
    public void addGraphicsObject(GraphicsObject graphicsObjectIn){
        graphicsObjects.add(graphicsObjectIn);
        maxPoint = solveSize(maxPoint, graphicsObjectIn.getMaxPoint());
        hasLayerChanged = true;

    }
    private Point solveSize(Point p1, Point p2){
        return new Point((int) Math.max(p1.getX(), p2.getX()),(int) Math.max(p1.getX(),p2.getX()) );
    }

    public boolean removeGraphicsObjects(GraphicsObject graphicsObjectIn) {
        return graphicsObjects.remove(graphicsObjectIn);
    }

    public void render(Graphics g) {

        if (hasLayerChanged){
            layerPrecomposed = new BufferedImage((int) maxPoint.getX(),(int) maxPoint.getY(), BufferedImage.TYPE_INT_ARGB);
            Graphics g2 = layerPrecomposed.getGraphics();
            for (GraphicsObject graphicsObject : graphicsObjects) {

            }

            hasLayerChanged = false;
        }
        g.drawImage(layerPrecomposed, 0, 0, null);
    }
}
