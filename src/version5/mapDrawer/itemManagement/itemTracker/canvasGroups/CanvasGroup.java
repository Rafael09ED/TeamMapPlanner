package version5.mapDrawer.itemManagement.itemTracker.canvasGroups;

import java.util.List;

/**
 * Created by Rafael on 4/25/2015.
 */
public interface CanvasGroup {
    void getAllSubLayersOrdered(List<CanvasGroupLayer> allSubLayers);
    void getChildren(List<CanvasGroup> immediateChildren);
}
