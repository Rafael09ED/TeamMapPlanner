package version5.mapDrawer.itemManagement.taskManagment;

import version5.mapDrawer.itemManagement.ItemManager;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupFolderWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupFolder;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;

/**
 * Created by Rafael on 4/27/2015.
 */
public class TaskData {
    private CanvasGroupLayer canvasGroupLayer;
    private CanvasGroupFolder canvasGroupFolder;
    private CanvasGroupLayerWrapper canvasGroupLayerWrapper;
    private CanvasGroupFolderWrapper canvasGroupFolderWrapper;

    public TaskData() {
    }

    public void setCanvasGroupLayer(CanvasGroupLayer canvasGroupLayer) {
        this.canvasGroupLayer = canvasGroupLayer;
    }

    public void setCanvasGroupFolder(CanvasGroupFolder canvasGroupFolder) {
        this.canvasGroupFolder = canvasGroupFolder;
    }

    public void setCanvasGroupLayerWrapper(CanvasGroupLayerWrapper canvasGroupLayerWrapper) {
        this.canvasGroupLayerWrapper = canvasGroupLayerWrapper;
    }

    public void setCanvasGroupFolderWrapper(CanvasGroupFolderWrapper canvasGroupFolderWrapper) {
        this.canvasGroupFolderWrapper = canvasGroupFolderWrapper;
    }
    public void grabData(ItemManager itemManager){
        if (canvasGroupFolderWrapper != null){
            canvasGroupFolder = itemManager.folderWrapperTranslator.get(canvasGroupFolderWrapper);
        }
        if (canvasGroupLayerWrapper != null) {
            canvasGroupLayer = itemManager.layerWrapperTranslator.get(canvasGroupLayerWrapper);
        }

    }

    public CanvasGroupLayer getCanvasGroupLayer() {
        return canvasGroupLayer;
    }

    public CanvasGroupFolder getCanvasGroupFolder() {
        return canvasGroupFolder;
    }

    public CanvasGroupLayerWrapper getCanvasGroupLayerWrapper() {
        return canvasGroupLayerWrapper;
    }

    public CanvasGroupFolderWrapper getCanvasGroupFolderWrapper() {
        return canvasGroupFolderWrapper;
    }
}
