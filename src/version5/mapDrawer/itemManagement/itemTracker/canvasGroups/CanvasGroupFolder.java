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
        for (int i = 0; i < canvasGroups.size(); i++) {
            canvasGroups.get(i).getAllSubLayersOrdered(allSubLayers);
        }
    }

    @Override
    public void getChildren(List<CanvasGroup> immediateChildren) {
        immediateChildren.addAll(canvasGroups);
    }

    @Override
    public CanvasGroupFolder findParent(CanvasGroup child) {
        for (CanvasGroup canvasGroup : canvasGroups) {
            if (canvasGroup == child){
                return this;
            }
        }
        for (CanvasGroup canvasGroup : canvasGroups) {
            CanvasGroupFolder parent = canvasGroup.findParent(child);
            if (parent != null) {
                return parent;
            }
        }
        return null;
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

    public int removeChild(CanvasGroup groupToDelete) {
        int index = canvasGroups.indexOf(groupToDelete);
        if (index < 0 ){
            return -1;
        }
        canvasGroups.remove(index);
        return index;
    }
}
