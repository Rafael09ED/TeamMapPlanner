package oldCode.version3.mapDrawer.GUI;

import oldCode.version3.mapDrawer.canvasItemTracking.CanvasItemTracker;
import oldCode.version3.mapDrawer.canvasItemTracking.canvasGroups.CG_Folder;
import oldCode.version3.mapDrawer.canvasItemTracking.canvasGroups.CG_Layer;
import oldCode.version3.mapDrawer.canvasItemTracking.canvasGroups.CanvasGroup;
import oldCode.version3.mapDrawer.canvasItemTracking.canvasItems.CI_Line;
import oldCode.version3.mapDrawer.canvasItemTracking.informationStorage.Point2D;
import oldCode.version3.mapDrawer.rendering.DataTracking.CGGraphicsData;
import oldCode.version3.mapDrawer.rendering.DataTracking.HierarchyTracker;
import oldCode.version3.mapDrawer.rendering.DataTracking.RenderOptimizer;
import oldCode.version3.mapDrawer.rendering.Graphics2D.MapDrawerRenderer;

import java.awt.*;


/**
 * Created by Rafael on 4/3/2015.
 */
public class GUICanvasItemInterface {
    private CanvasItemTracker itemTracker;
    private MapDrawerRenderer drawerRenderer;
    private boolean totalRenderRequired = true;
    private RenderOptimizer optimizedRendering;
    //TODO: use CGGraphicsData to store bufferedImage and other info for rendering

    public GUICanvasItemInterface(CanvasItemTracker itemTracker, MapDrawerRenderer drawerRenderer) {
        this.itemTracker = itemTracker;
        this.drawerRenderer = drawerRenderer;
        optimizedRendering = new RenderOptimizer(drawerRenderer);
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
    public CG_Folder newFolder(CanvasGroup currentGroup){
        HierarchyTracker currentGroupHierarchy = new HierarchyTracker(currentGroup);
        if (currentGroup.findHierarchy(currentGroupHierarchy)){
            CG_Folder parent = currentGroupHierarchy.getParent();
            if (parent != null){
                CG_Folder folder = new CG_Folder();
                parent.addCanvasGroup(folder);
                optimizedRendering.setHashMapData(folder, new CGGraphicsData(folder));
                return folder;
            }
        }
        return null;
    }
    public CG_Layer newLayer(CanvasGroup currentGroup){
        HierarchyTracker currentGroupHierarchy = new HierarchyTracker(currentGroup);
        if (currentGroup.findHierarchy(currentGroupHierarchy)){
            CG_Folder parent = currentGroupHierarchy.getParent();
            if (parent != null){
                CG_Layer layer = new CG_Layer();
                parent.addCanvasGroup(layer);
                optimizedRendering.setHashMapData(layer, new CGGraphicsData(layer));
                return layer;
            }
        }
        return null;
    }
    public static void addLayerToFolder(CG_Layer layer, CG_Folder folder){
        folder.addCanvasGroup(layer);

    }

    public void renderAll() {
        //drawerRenderer.RenderAll();
    }

    public void TESTINGMETHOD_GENERATELINES() {
        itemTracker.getAllSubLayers().get(0).addCanvasItem(new CI_Line(new Point2D(rndNum(),rndNum()), new Point2D(rndNum(), rndNum())));
    }

    private double rndNum() {
        return (int)(Math.random()*1000);
    }
}
