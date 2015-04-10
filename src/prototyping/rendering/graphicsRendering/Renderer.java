package prototyping.rendering.graphicsRendering;

import prototyping.rendering.MainClass;
import prototyping.rendering.canvasItem.ObjectTracker;

import java.awt.*;
import java.util.*;

/**
 * Created by Rafael on 4/1/2015.
 */
public class Renderer {
    private ObjectTracker objectTracker;
    private Graphics2DRenderer g2Drenderer;

    public Renderer(ObjectTracker objectTracker) {
        this.objectTracker = objectTracker;
        g2Drenderer = new Graphics2DRenderer();
    }
    public void renderAll(Graphics2D g){
        g2Drenderer.setGraphics2D(g);
        java.util.List<IRenderable> list = new LinkedList<>(objectTracker.getObjectsToRender());

        for (Iterator<IRenderable> listIterator = list.iterator(); listIterator.hasNext(); ) {
            listIterator.next().render(g2Drenderer);
            listIterator.remove();
        }
    }
}
