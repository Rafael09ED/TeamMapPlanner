package version4.mapDrawer.itemTracker.canvasGroups;

import java.util.List;

/**
 * Created by Rafael on 4/14/2015.
 */

public interface CanvasGroup {
    void getAllSubLayers(List<CanvasGroupLayer> allSubLayers);
    CanvasGroupFolder getParent();
}
