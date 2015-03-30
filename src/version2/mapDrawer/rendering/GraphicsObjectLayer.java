package version2.mapDrawer.rendering;

import version2.mapDrawer.graphicsObjects.GraphicsObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by ADMIN on 3/30/2015.
 */
public class GraphicsObjectLayer {
    private boolean requiresRender = false;
    private List<GraphicsObject> graphicsObjects;
    private BufferedImage layerPrecomposed;
    private Point maxPoint;

    private boolean objectAdded = false;
    private Point maxPointAdded;
    private List<GraphicsObject> objectsAdded;

    public GraphicsObjectLayer() {
        graphicsObjects = Collections.synchronizedList(new ArrayList<GraphicsObject>());
        layerPrecomposed = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

        objectsAdded = new LinkedList<GraphicsObject>();
        maxPoint = new Point(1, 1);
        maxPointAdded = new Point(1,1);
    }

    public void addGraphicsObject(GraphicsObject graphicsObjectIn) {
        graphicsObjects.add(graphicsObjectIn);
        
        objectsAdded.add(graphicsObjectIn);
        maxPointAdded = solveSize(maxPointAdded, graphicsObjectIn.getMaxPoint());
        objectAdded = true;
    }

    private void setRenderQuality(Graphics g){
        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint
                (RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        g1.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);

    }

    private Point solveSize(Point p1, Point p2) {
        return new Point((int) Math.max(p1.getX(), p2.getX()) + 1, (int) Math.max(p1.getX(), p2.getX()) + 1);
    }

    public boolean removeGraphicsObjects(GraphicsObject graphicsObjectIn) {
        boolean removed = graphicsObjects.remove(graphicsObjectIn);
        if (removed) {
            requiresRender = true;
        }
        return removed;
    }

    public void render(Graphics g) {

        if (requiresRender) {
            layerPrecomposed = new BufferedImage((int) maxPoint.getX(), (int) maxPoint.getY(), BufferedImage.TYPE_INT_ARGB);
            Graphics g2 = layerPrecomposed.getGraphics();
            setRenderQuality(g2);

            for (GraphicsObject graphicsObject : graphicsObjects) {
                graphicsObject.paint(g2);
            }
        } else if (objectAdded){
            Point tempMaxPoint = solveSize(maxPoint, maxPointAdded);

            if ( maxPoint.equals(tempMaxPoint) ) {
                Graphics g1 = layerPrecomposed.getGraphics();
                setRenderQuality(g1);

                for (Iterator<GraphicsObject> iterator = objectsAdded.iterator(); iterator.hasNext(); ) {
                    iterator.next().paint(g1);
                    iterator.remove();
                }

            } else {
                maxPoint = tempMaxPoint;

                BufferedImage tempLayer = new BufferedImage((int) maxPoint.getX(), (int) maxPoint.getY(), BufferedImage.TYPE_INT_ARGB);
                Graphics g1 = tempLayer.getGraphics();
                g1.drawImage(layerPrecomposed, 0, 0, null);

                for (Iterator<GraphicsObject> iterator = objectsAdded.iterator(); iterator.hasNext(); ) {
                    iterator.next().paint(g1);
                    iterator.remove();
                }

                g1.dispose();
                layerPrecomposed = tempLayer;
            }
        }
        // sets render tracker
        requiresRender = false;
        maxPointAdded = new Point(1,1);
        objectAdded = false;

        g.drawImage(layerPrecomposed, 0, 0, null);
    }
}
