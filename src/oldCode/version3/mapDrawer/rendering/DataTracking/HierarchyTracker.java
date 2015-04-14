package oldCode.version3.mapDrawer.rendering.DataTracking;

import oldCode.version3.mapDrawer.canvasItemTracking.canvasGroups.CG_Folder;
import oldCode.version3.mapDrawer.canvasItemTracking.canvasGroups.CanvasGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 4/13/2015.
 */
public class HierarchyTracker {
    private List<CG_Folder> cgHierarchy;
    private CanvasGroup childGroup;
    public HierarchyTracker(CanvasGroup childGroup) {
        this.childGroup = childGroup;
        cgHierarchy = new ArrayList<CG_Folder>();
    }
    public void addParent(CG_Folder immediateParent){
        cgHierarchy.add(immediateParent);
    }
    public List<CG_Folder> getHierarchy(){
        return cgHierarchy;
    }
    public CanvasGroup hierarchyOf(){
        return childGroup;
    }

    public CG_Folder getParent() {
        if (cgHierarchy.size() >= 1){
            return cgHierarchy.get(0);
        }
        return null;
    }
}