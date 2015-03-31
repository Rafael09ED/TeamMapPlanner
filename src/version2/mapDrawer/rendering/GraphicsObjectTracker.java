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
    private int activeLayer = 0;
    private static final String LAYER_NAME = "Layer ";
    public GraphicsObjectTracker() {

        graphicsObjects = new ArrayList<GraphicsObjectLayer>();
        createNewLayer(0);
    }

    public void createNewLayer(int index) {
        while (graphicsObjects.size() < index + 1) {
            graphicsObjects.add(new GraphicsObjectLayer(this, LAYER_NAME + (graphicsObjects.size() + 1)));
        }
    }

    public void addGraphicsObject(GraphicsObject graphicsObjectIn) {
        graphicsObjects.get(activeLayer).addGraphicsObject(graphicsObjectIn);
    }

    public GraphicsObjectLayer setActiveLayer(int layer) {
        if (graphicsObjects.size() < layer + 1){
            return null;
        }

        activeLayer = layer;
        return graphicsObjects.get(layer);
    }

    public void addCurrentFrameObject(GraphicsObject graphicsObjectIn) {
        graphicsObjects.get(activeLayer).addCurrentFrameGraphicsObject(graphicsObjectIn);
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
        }
    }

    public GraphicsObjectLayer createNewLayer() {
        GraphicsObjectLayer newLayer = new GraphicsObjectLayer(this, LAYER_NAME + (graphicsObjects.size() + 1));
        graphicsObjects.add(newLayer);

        return newLayer;
    }
}
