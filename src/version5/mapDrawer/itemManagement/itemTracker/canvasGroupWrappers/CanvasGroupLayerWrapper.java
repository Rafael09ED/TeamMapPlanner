package version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers;

import version5.mapDrawer.itemManagement.infoTypes.BoundingBox2D;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;

import java.awt.image.BufferedImage;

/**
 * Created by Rafael on 4/25/2015.
 */
public class CanvasGroupLayerWrapper implements CanvasGroupWrapper {
    private long timeLastChanged;
    private String displayName;
    private BoundingBox2D boundingBox2D;

    public CanvasGroupLayerWrapper(CanvasGroupLayer canvasGroupLayer) {
        setDisplayName("Folder " + canvasGroupLayer.getLayerNumber());
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
    public BufferedImage getCanvasGroupRender() {
        return null;
    }

    @Override
    public long getLastChangedTime() {
        return timeLastChanged;
    }

    @Override
    public void notifyOfChange() {
        timeLastChanged = System.currentTimeMillis();
    }

    public BoundingBox2D getBoundingBox2D() {
        return boundingBox2D;
    }
}
