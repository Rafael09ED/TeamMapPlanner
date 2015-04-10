package version3.mapDrawer.rendering;

import version3.mapDrawer.canvasItemTracking.CanvasItemTracker;
import version3.mapDrawer.canvasItemTracking.canvasItems.CanvasItem;
import version3.mapDrawer.rendering.Graphics2D.Graphics2DRenderer;

import java.awt.*;
import java.util.List;


/**
 * Created by Rafael on 4/3/2015.
 */
public class MapDrawerRenderer {
    private Graphics2DRenderer graphics2dRenderer;
    private CanvasItemTracker itemTracker;

    public MapDrawerRenderer(CanvasItemTracker itemTracker) {
        this.itemTracker = itemTracker;
        graphics2dRenderer = new Graphics2DRenderer();
    }

    public void RenderAll(){
        List<CanvasItem> listToRender = itemTracker.getAllCanvasItems();
    }

    public void setG2D(Graphics2D g){
        graphics2dRenderer.setGraphicsToRenderTo(g);
    }

}
