package version5.mapDrawer.interfacing;

import version5.mapDrawer.itemManagement.ItemManager;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupFolderWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupWrapper;
import version5.mapDrawer.rendering.optimization.RenderData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 4/28/2015.
 */
public class DataGrabber {
    private final ItemManager itemManager;

    public DataGrabber(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    public CanvasGroupFolderWrapper getRootWrapper(){
        return itemManager.getRootWrapper();
    }
    public RenderData renderCanvas(){
        return itemManager.getRootWrapper().getCanvasGroupRender();
    }
    public List<CanvasGroupWrapper> getChildrenWrapped(CanvasGroupFolderWrapper canvasGroupFolderWrapper){
        List childrenUnwrapped = new ArrayList<>();
        itemManager.folderWrapperTranslator.get(canvasGroupFolderWrapper).getChildren(childrenUnwrapped);
        return itemManager.convertToWrapper(childrenUnwrapped);
    }

}
