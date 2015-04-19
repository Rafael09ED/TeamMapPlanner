package version4.mapDrawer.itemTracker.canvasGroups;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 4/14/2015.
 */
public class CanvasGroupFolder implements CanvasGroup{
    private static int folderCounter = 0;

    protected final List<CanvasGroup> canvasGroups;
    private CanvasGroupFolder parent;
    private String canvasGroupName;

    public CanvasGroupFolder(CanvasGroupFolder parent) {
        this.parent = parent;
        canvasGroups = new ArrayList<>();
        canvasGroupName = "Folder " + folderCounter;
        folderCounter++;
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

    public CanvasGroupFolder getParent() {
        return parent;
    }

    @Override
    public String getName() {
        return canvasGroupName;
    }
    @Override
    public void setName(String newName){
        canvasGroupName = newName;
    }

    public void setParent(CanvasGroupFolder parent){
        this.parent = parent;
    }
    public void addCanvasGroup(CanvasGroup canvasGroupLayer) {
        canvasGroups.add(canvasGroupLayer);
    }
}
