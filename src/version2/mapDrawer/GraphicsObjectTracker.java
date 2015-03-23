package version2.mapDrawer;

import version2.mapDrawer.graphicsObjects.GraphicsObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 3/22/2015.
 */
public class GraphicsObjectTracker {

    private List<GraphicsObject> graphicsObjects;

    public GraphicsObjectTracker() {

        graphicsObjects = new ArrayList<GraphicsObject>();

    }

    public void addGraphicsObject(GraphicsObject graphicsObjectIn){
        graphicsObjects.add(graphicsObjectIn);
    }

    public boolean removeGraphicsObject(GraphicsObject graphicsObjectIn){
        return graphicsObjects.remove(graphicsObjectIn);
    }
    public void renderGraphicsObjects(Graphics g){
        for (GraphicsObject graphicsObject : graphicsObjects) {
            graphicsObject.paint(g);
        }
    }
}
