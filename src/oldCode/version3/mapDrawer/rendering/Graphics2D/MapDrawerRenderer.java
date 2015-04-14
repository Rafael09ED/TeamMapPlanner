package oldCode.version3.mapDrawer.rendering.Graphics2D;

import oldCode.version3.mapDrawer.canvasItemTracking.CanvasItemTracker;
import oldCode.version3.mapDrawer.canvasItemTracking.canvasGroups.CanvasGroup;
import oldCode.version3.mapDrawer.settings.RENDER_SETTINGS;

import java.awt.*;
import java.util.List;


/**
 * Created by Rafael on 4/3/2015.
 */
public class MapDrawerRenderer {
    private Graphics2DRenderer graphics2dRenderer;
    private CanvasItemTracker itemTracker;

    // NOTE: render only the first and last in the hierarchy

    public MapDrawerRenderer(CanvasItemTracker itemTracker) {
        this.itemTracker = itemTracker;
        graphics2dRenderer = new Graphics2DRenderer();
    }

    public void RenderAll(){
        List<CanvasGroup> groupsToRender = itemTracker.getAllSubCanvasGroups();
        for (int i = 0; i < groupsToRender.size(); i++) {
            CanvasGroup group = groupsToRender.get(i);
            group.render(graphics2dRenderer);
        }
    }

    public void setG2D(Graphics2D g){
        RENDER_SETTINGS.SetRenderAA(g);
        graphics2dRenderer.setGraphicsToRenderTo(g);
    }

}
