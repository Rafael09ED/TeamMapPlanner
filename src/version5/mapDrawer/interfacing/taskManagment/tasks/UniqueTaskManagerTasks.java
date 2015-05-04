package version5.mapDrawer.interfacing.taskManagment.tasks;

import version5.mapDrawer.itemManagement.ItemManager;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupFolderWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;

/**
 * Created by Rafael on 4/28/2015.
 */
public class UniqueTaskManagerTasks {
    private final ItemManager itemManager;

    public UniqueTaskManagerTasks(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    public CanvasGroupFolderWrapper createNewCanvasFolder(CanvasGroupFolderWrapper parentCanvasFolder){
        CanvasGroupFolderWrapper folderWrapper = itemManager.createNewCanvasGroupFolder(parentCanvasFolder);
        return folderWrapper;
    }
    public CanvasGroupLayerWrapper createNewCanvasLayer(CanvasGroupFolderWrapper parentCanvasFolder){
        return itemManager.createNewCanvasGroupLayer(parentCanvasFolder);
    }
}
