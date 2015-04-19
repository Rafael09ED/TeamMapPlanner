package version4.mapDrawer.itemTracker.canvasGroups;

import java.util.List;

/**
 * Created by Rafael on 4/14/2015.
 */

public interface CanvasGroup {
    void getAllSubLayersOrdered(List<CanvasGroupLayer> allSubLayers);
    void getChildren(List<CanvasGroup> immediateChildren);

    CanvasGroupFolder getParent();
    String getName();

    void setName(String newName);
}
