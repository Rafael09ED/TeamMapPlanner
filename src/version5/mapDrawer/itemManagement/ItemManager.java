package version5.mapDrawer.itemManagement;

import version5.mapDrawer.itemManagement.itemTracker.CanvasTracker;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupFolderWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupFolder;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;
import version5.mapDrawer.itemManagement.taskManagment.TaskData;
import version5.mapDrawer.rendering.RenderingWrapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafael on 4/24/2015.
 */
public class ItemManager {
    private final CanvasTracker canvasTracker;
    private final RenderingWrapper renderingWrapper;
    private Set<CanvasGroupLayer> changedCanvasGroupLayers;

    public HashMap<CanvasGroupFolderWrapper, CanvasGroupFolder> folderWrapperTranslator;
    public HashMap<CanvasGroupLayerWrapper, CanvasGroupLayer> layerWrapperTranslator;

    public ItemManager(CanvasTracker canvasTracker, RenderingWrapper renderingWrapper) {
        this.canvasTracker = canvasTracker;
        this.renderingWrapper = renderingWrapper;
        folderWrapperTranslator = new HashMap<>();
        layerWrapperTranslator = new HashMap<>();
        changedCanvasGroupLayers = new HashSet<>();
        //ToDo
    }
    public CanvasGroupLayerWrapper createCanvasGroupLayer(CanvasGroupFolderWrapper parentWrapper){
        CanvasGroupLayer canvasGroupLayer = new CanvasGroupLayer();
        CanvasGroupFolder parent = folderWrapperTranslator.get(parentWrapper);
        CanvasGroupLayerWrapper canvasGroupLayerWrapper
                = new CanvasGroupLayerWrapper(canvasGroupLayer, renderingWrapper);
       layerWrapperTranslator.put(canvasGroupLayerWrapper, canvasGroupLayer);
        return canvasGroupLayerWrapper;
    }
    public CanvasGroupFolderWrapper createCanvasGroupFolder(CanvasGroupFolderWrapper parentWrapper){
        CanvasGroupFolder canvasGroupFolder = new CanvasGroupFolder();
        CanvasGroupFolder parent = folderWrapperTranslator.get(parentWrapper);
        CanvasGroupFolderWrapper canvasGroupFolderWrapper
                = new CanvasGroupFolderWrapper(canvasGroupFolder, renderingWrapper);
        folderWrapperTranslator.put(canvasGroupFolderWrapper, canvasGroupFolder);
        return canvasGroupFolderWrapper;
    }
    public void fillData(TaskData canvasTaskData) {
        canvasTaskData.grabData(this);
    }

    public void addToChangedSet(List<CanvasGroupLayer> layersChangedByTask) {
        for (CanvasGroupLayer canvasGroupLayer : layersChangedByTask) {
            changedCanvasGroupLayers.add(canvasGroupLayer);
        }
        renderingWrapper.setChangedLayers(changedCanvasGroupLayers);
    }
}
