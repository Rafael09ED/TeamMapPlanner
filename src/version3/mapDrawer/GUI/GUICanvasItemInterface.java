package version3.mapDrawer.GUI;

import version3.mapDrawer.canvasItemTracking.CanvasItemTracker;
import version3.mapDrawer.canvasItemTracking.canvasGroups.CG_Folder;
import version3.mapDrawer.canvasItemTracking.canvasGroups.CG_Layer;
import version3.mapDrawer.canvasItemTracking.canvasGroups.CanvasGroup;
import version3.mapDrawer.rendering.Graphics2D.CGGraphicsData;
import version3.mapDrawer.rendering.MapDrawerRenderer;
import version3.mapDrawer.rendering.RenderOptimizer;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Rafael on 4/3/2015.
 */
public class GUICanvasItemInterface {
    private CanvasItemTracker itemTracker;
    private MapDrawerRenderer drawerRenderer;
    private boolean totalRenderRequired = true;
    private HashMap<CanvasGroup, CGGraphicsData> layerToDataHM;
    private RenderOptimizer optimizedRendering;
    //TODO: use CGGraphicsData to store bufferedImage and other info for rendering

    public GUICanvasItemInterface(CanvasItemTracker itemTracker, MapDrawerRenderer drawerRenderer) {
        this.itemTracker = itemTracker;
        this.drawerRenderer = drawerRenderer;
        optimizedRendering = new RenderOptimizer(drawerRenderer);
        layerToDataHM = new HashMap<>();
    }

    public void setG2D(Graphics2D graphics) {
        drawerRenderer.setG2D(graphics);
    }

    public void moveCanvasGroup(CanvasGroup group, CG_Folder destination){
        //TODO: Move CanvasGroup Code
    }

    public void removeCanvasGroup(CanvasGroup group){
        //TODO: Remove CanvasGroups Code
    }
    public void newFolder(){
        //TODO: New Folder Code
    }
    public void newLayer(){
        //TODO: New Layer Code
    }
    public static void addLayerToFolder(CG_Layer layer, CG_Folder folder){
        folder.addCanvasGroup(layer);

    }
}
