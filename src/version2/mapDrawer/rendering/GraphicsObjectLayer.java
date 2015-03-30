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
    }
    public void addGraphicsObject(GraphicsObject graphicsObjectIn){
        graphicsObjects.add(graphicsObjectIn);
    }

    public boolean removeGraphicsObjects(GraphicsObject graphicsObjectIn) {
        return graphicsObjects.remove(graphicsObjectIn);
    }

    public void render(Graphics g) {

        if (hasLayerChanged){

            hasLayerChanged = false;
        }


    }
}
