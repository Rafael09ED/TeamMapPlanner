package version5.mapDrawer.itemManagement.itemTracker.canvasGroups;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 4/25/2015.
 */
public class CanvasGroupFolder implements CanvasGroup {
    private static int folderCounter = 0;

    protected final List<CanvasGroup> canvasGroups;
    private final int folderNumber;

    public CanvasGroupFolder() {
        canvasGroups = new ArrayList<>();
        folderNumber = folderCounter++;
    }

    @Override
    public void getAllSubLayersOrdered(List<CanvasGroupLayer> allSubLayers) {

    }

    @Override
    public void getChildren(List<CanvasGroup> immediateChildren) {
        immediateChildren.addAll(canvasGroups);
    }

    public int getFolderNumber() {
        return folderNumber;
    }
    public void addCanvasGroup(CanvasGroup canvasGroupLayer) {
        canvasGroups.add(canvasGroupLayer);
    }

    public void addCanvasGroup(CanvasGroup canvasGroupLayer, int index) {
        canvasGroups.add(index, canvasGroupLayer);
    }

    public boolean removeChild(CanvasGroup groupToDelete) {
        boolean removed = canvasGroups.remove(groupToDelete);
        return removed;
    }
}
