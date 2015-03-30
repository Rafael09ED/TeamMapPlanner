package version2.mapDrawer;

import version2.mapDrawer.graphicsObjects.GraphicsObject;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Rafael on 3/22/2015.
 */
public class GraphicsObjectTracker {

    private List<List<GraphicsObject>> graphicsObjects;
    private List<GraphicsObject> currentFrameGraphicsObjects;
    private int activeLayer = 0;

    public GraphicsObjectTracker() {

        graphicsObjects = new ArrayList<List<GraphicsObject>>();
        currentFrameGraphicsObjects = new LinkedList<GraphicsObject>();
        createNewLayer(0);
    }

    private void createNewLayer(int index) {
        while (graphicsObjects.size() < index + 1) {
            graphicsObjects.add(Collections.synchronizedList(new ArrayList<GraphicsObject>()));
        }
    }

    public void addGraphicsObject(GraphicsObject graphicsObjectIn) {
        graphicsObjects.get(activeLayer).add(graphicsObjectIn);
    }

    public void setActiveLayer(int layer) {
        activeLayer = layer;
    }

    public void addCurrentFrameObject(GraphicsObject graphicsObjectIn) {
        currentFrameGraphicsObjects.add(graphicsObjectIn);
    }

    public boolean removeGraphicsObject(GraphicsObject graphicsObjectIn) {
        return graphicsObjects.get(activeLayer).remove(graphicsObjectIn);
    }

    public void renderGraphicsObjects(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint
                (RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        int currentLayerToRender = 0;
        for (List<GraphicsObject> graphicsList : graphicsObjects) {
            synchronized (graphicsList) {

                for (GraphicsObject graphicsObject : graphicsList) {
                    graphicsObject.paint(g2);
                }
            }
            //System.out.println("Printing current Frame");
            if (currentLayerToRender == activeLayer) {
                for (Iterator<GraphicsObject> iterator = currentFrameGraphicsObjects.iterator(); iterator.hasNext(); ) {
                    iterator.next().paint(g);
                    iterator.remove();
                }
            }
        }
    }
}
