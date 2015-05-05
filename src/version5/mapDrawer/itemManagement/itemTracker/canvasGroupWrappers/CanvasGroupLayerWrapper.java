package version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers;

import version5.mapDrawer.interfacing.CanvasGroupTypeActionable;
import version5.mapDrawer.itemManagement.ItemManager;
import version5.mapDrawer.itemManagement.infoTypes.BoundingBox2D;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;
import version5.mapDrawer.rendering.RenderingWrapper;
import version5.mapDrawer.rendering.optimization.RenderData;

/**
 * Created by Rafael on 4/25/2015.
 */
public class CanvasGroupLayerWrapper implements CanvasGroupWrapper {
    private final CanvasGroupLayer canvasGroupLayer;
    private RenderingWrapper renderingWrapper;
    private long timeLastChanged;
    private String displayName;

    public CanvasGroupLayerWrapper(CanvasGroupLayer canvasGroupLayer, RenderingWrapper renderingWrapper, ItemManager itemManager) {
        this.canvasGroupLayer = canvasGroupLayer;
        this.renderingWrapper = renderingWrapper;
        setDisplayName("Layer " + canvasGroupLayer.getLayerNumber());
        notifyOfChange();
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String newDisplayName) {
        displayName = newDisplayName;
    }

    @Override
    public RenderData getCanvasGroupRender() {
        return renderingWrapper.getRender(canvasGroupLayer);
    }

    @Override
    public long getLastChangedTime() {
        return timeLastChanged;
    }

    @Override
    public void notifyOfChange() {
        timeLastChanged = System.currentTimeMillis();
    }

    @Override
    public void callTypeActionable(CanvasGroupTypeActionable canvasGroupTypeActionable) {
        canvasGroupTypeActionable.doIfCanvasLayer(this);
    }

    public BoundingBox2D getBoundingBox2D() {
        return canvasGroupLayer.getBoundingBox();
    }
}
