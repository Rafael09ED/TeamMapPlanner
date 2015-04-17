package version4.mapDrawer.itemTracker.canvasGroups;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 4/14/2015.
 */
public class CanvasGroupFolder implements CanvasGroup{
    protected final List<CanvasGroup> canvasGroups;
    private CanvasGroupFolder parent;

    public CanvasGroupFolder(CanvasGroupFolder parent) {
        this.parent = parent;
        canvasGroups = new ArrayList<>();
    }

    @Override
    public void getAllSubLayersOrdered(List<CanvasGroupLayer> allSubLayers) {
        for (int i = 0; i < canvasGroups.size(); i++) {
            canvasGroups.get(i).getAllSubLayersOrdered(allSubLayers);
        }
    }

    public CanvasGroupFolder getParent() {
        return parent;
    }

    public void setParent(CanvasGroupFolder parent){
        this.parent = parent;
    }
    public void addCanvasGroup(CanvasGroup canvasGroupLayer) {
        canvasGroups.add(canvasGroupLayer);
    }
}
