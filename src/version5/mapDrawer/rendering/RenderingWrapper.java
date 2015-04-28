package version5.mapDrawer.rendering;

import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupFolderWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupFolder;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;
import version5.mapDrawer.rendering.optimization.CanvasGroupRenderOptimizer;

import java.awt.image.BufferedImage;
import java.util.Set;

/**
 * Created by Rafael on 4/24/2015.
 */
public class RenderingWrapper {

    private final CanvasGroupRenderOptimizer canvasGroupRenderOptimizer;
    private Set<CanvasGroupLayer> changedLayers;

    public RenderingWrapper() {
         canvasGroupRenderOptimizer = new CanvasGroupRenderOptimizer(this);
    }

    public BufferedImage getRender(CanvasGroupFolder canvasGroupFolder) {
        return canvasGroupRenderOptimizer.getRender(canvasGroupFolder);
    }
    public BufferedImage getRender(CanvasGroupLayer canvasGroupLayer) {
        return canvasGroupRenderOptimizer.getRender(canvasGroupLayer);
    }

    public BufferedImage getRender(CanvasGroupFolderWrapper canvasGroupFolderWrapper) {
        return null;
    }

    public void setChangedLayers(Set<CanvasGroupLayer> changedLayers) {
        this.changedLayers = changedLayers;
        canvasGroupRenderOptimizer.setChangedLayers(changedLayers);
    }
}
