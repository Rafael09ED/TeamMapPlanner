package version2.mapDrawer.rendering;

import version2.mapDrawer.graphicsObjects.GraphicsObject;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Rafael on 3/22/2015.
 */
public class GraphicsObjectTracker {

    private List<GraphicsObjectLayer> graphicsObjects;
    private List<GraphicsObject> currentFrameGraphicsObjects;
    private int activeLayer = 0;

    public GraphicsObjectTracker() {

        graphicsObjects = new ArrayList<GraphicsObjectLayer>();
        currentFrameGraphicsObjects = new LinkedList<GraphicsObject>();
        createNewLayer(0);
    }

    private void createNewLayer(int index) {
        while (graphicsObjects.size() < index + 1) {
            graphicsObjects.add(new GraphicsObjectLayer());
        }
    }

    public void addGraphicsObject(GraphicsObject graphicsObjectIn) {
        graphicsObjects.get(activeLayer).addGraphicsObject(graphicsObjectIn);
    }

    public void setActiveLayer(int layer) {
        activeLayer = layer;
    }

    public void addCurrentFrameObject(GraphicsObject graphicsObjectIn) {
        currentFrameGraphicsObjects.add(graphicsObjectIn);
    }

    public boolean removeGraphicsObject(GraphicsObject graphicsObjectIn) {
        return graphicsObjects.get(activeLayer).removeGraphicsObjects(graphicsObjectIn);
    }

    public void renderGraphicsObjects(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint
                (RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        int currentLayerToRender = 0;

        for (GraphicsObjectLayer graphicsLayer : graphicsObjects) {
            synchronized (graphicsLayer) {
                graphicsLayer.render(g);
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
