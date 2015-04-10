package prototyping.rendering.canvasItem;

import prototyping.rendering.graphicsRendering.IRenderable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 4/1/2015.
 */
public class ObjectTracker {
    private List<IRenderable> objectsToRender;

    public ObjectTracker() {
        objectsToRender = new ArrayList<>();
        objectsToRender.add(new Line(new Point(10,10), new Point(100,100)));
    }

    public List<IRenderable> getObjectsToRender() {
        return objectsToRender;
    }

    public void addRenderable(IRenderable iRenderable) {
        objectsToRender.add(iRenderable);
    }
}
