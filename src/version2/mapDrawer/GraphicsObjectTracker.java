package version2.mapDrawer;

import version2.mapDrawer.graphicsObjects.GraphicsObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rafael on 3/22/2015.
 */
public class GraphicsObjectTracker {

    private List<GraphicsObject> graphicsObjects;
    private List<GraphicsObject> currentFrameGraphicsObjects;

    public GraphicsObjectTracker() {

        graphicsObjects = new ArrayList<GraphicsObject>();
        currentFrameGraphicsObjects = new LinkedList<>();

    }

    public void addGraphicsObject(GraphicsObject graphicsObjectIn){
        graphicsObjects.add(graphicsObjectIn);
    }

    public void addCurrentFrameObject(GraphicsObject graphicsObjectIn){
        currentFrameGraphicsObjects.add(graphicsObjectIn);
    }

    public boolean removeGraphicsObject(GraphicsObject graphicsObjectIn){
        return graphicsObjects.remove(graphicsObjectIn);
    }
    public void renderGraphicsObjects(Graphics g){
        for (GraphicsObject graphicsObject : graphicsObjects) {
            graphicsObject.paint(g);
        }
        System.out.println("Printing current Frame");
        for (Iterator<GraphicsObject> iterator = currentFrameGraphicsObjects.iterator(); iterator.hasNext(); ) {

            iterator.next().paint(g);
            iterator.remove();

        }
    }
}
