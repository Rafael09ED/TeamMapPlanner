package version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers;

import java.awt.image.BufferedImage;

/**
 * Created by Rafael on 4/25/2015.
 */
public interface CanvasGroupWrapper {
    public String getDisplayName();
    public void setDisplayName(String newDisplayName);
    public BufferedImage getCanvasGroupRender();
    public long getLastChangedTime();
    void notifyOfChange();
}
