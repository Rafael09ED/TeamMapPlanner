package version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers;

import version5.mapDrawer.interfacing.CanvasGroupTypeActionable;
import version5.mapDrawer.rendering.optimization.RenderData;

/**
 * Created by Rafael on 4/25/2015.
 */
public interface CanvasGroupWrapper {
    String getDisplayName();
    void setDisplayName(String newDisplayName);
    RenderData getCanvasGroupRender();
    long getLastChangedTime();
    void notifyOfChange();
    void callTypeActionable(CanvasGroupTypeActionable canvasGroupTypeActionable);
}
