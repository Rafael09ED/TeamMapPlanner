package version5.mapDrawer.interfacing.taskManagment;

import version5.mapDrawer.itemManagement.ItemManager;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupFolderWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupFolder;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;

import java.util.Map;

/**
 * Created by Rafael on 4/27/2015.
 */
public class TaskData {
    private final ItemManager itemManager;

    // TODO : Implement Better
    public TaskData(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public CanvasGroupFolderWrapper getRootWrapped() {
        return itemManager.getRootWrapper();
    }

    public CanvasGroupLayer getCanvasGroupLayer(CanvasGroupLayerWrapper canvasGroupLayerWrapper) {
        return itemManager.layerWrapperTranslator.get(canvasGroupLayerWrapper);
    }

    public CanvasGroupFolder getCanvasGroupFolder(CanvasGroupFolderWrapper canvasGroupFolderWrapper) {
        return itemManager.folderWrapperTranslator.get(canvasGroupFolderWrapper);
    }

    public CanvasGroupLayerWrapper getCanvasGroupLayerWrapper(CanvasGroupLayer canvasGroupLayer) {
        for (Map.Entry<CanvasGroupLayerWrapper, CanvasGroupLayer> entry : itemManager.layerWrapperTranslator.entrySet()) {
            if (entry.getValue() == canvasGroupLayer){
                return entry.getKey();
            }
        }
        return null;
    }

    public CanvasGroupFolderWrapper getCanvasGroupFolderWrapper(CanvasGroupFolder canvasGroupFolder) {
        for (Map.Entry<CanvasGroupFolderWrapper, CanvasGroupFolder> entry : itemManager.folderWrapperTranslator.entrySet()) {
            if (entry.getValue() == canvasGroupFolder){
                return entry.getKey();
            }
        }
        return null;
    }
}
