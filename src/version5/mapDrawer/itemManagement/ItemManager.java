package version5.mapDrawer.itemManagement;

import version5.mapDrawer.interfacing.taskManagment.TaskData;
import version5.mapDrawer.itemManagement.itemTracker.CanvasRoot;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupFolderWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroup;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupFolder;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;
import version5.mapDrawer.rendering.RenderingWrapper;

import java.util.*;

/**
 * Created by Rafael on 4/24/2015.
 */
public class ItemManager {
    private final CanvasRoot canvasRoot;
    private final RenderingWrapper renderingWrapper;
    private final CanvasGroupFolderWrapper canvasRootWrapper;

    private Set<CanvasGroupLayer> changedCanvasGroupLayers;

    public HashMap<CanvasGroupFolderWrapper, CanvasGroupFolder> folderWrapperTranslator;
    public HashMap<CanvasGroupLayerWrapper, CanvasGroupLayer> layerWrapperTranslator;

    public ItemManager(CanvasRoot canvasRoot, RenderingWrapper renderingWrapper) {
        this.canvasRoot = canvasRoot;
        canvasRootWrapper = new CanvasGroupFolderWrapper(canvasRoot, renderingWrapper, this);
        this.renderingWrapper = renderingWrapper;
        folderWrapperTranslator = new HashMap<>();
        layerWrapperTranslator = new HashMap<>();

        changedCanvasGroupLayers = new HashSet<>();
        renderingWrapper.setChangedLayers(changedCanvasGroupLayers);

        folderWrapperTranslator.put(canvasRootWrapper, canvasRoot);
    }

    public CanvasGroupLayerWrapper createNewCanvasGroupLayer(CanvasGroupFolderWrapper parentWrapper) {
        CanvasGroupLayer canvasGroupLayer = new CanvasGroupLayer();
        CanvasGroupFolder parent = folderWrapperTranslator.get(parentWrapper);
        parent.addCanvasGroup(canvasGroupLayer);
        CanvasGroupLayerWrapper canvasGroupLayerWrapper
                = new CanvasGroupLayerWrapper(canvasGroupLayer, renderingWrapper, this);
        layerWrapperTranslator.put(canvasGroupLayerWrapper, canvasGroupLayer);
        return canvasGroupLayerWrapper;
    }

    public CanvasGroupFolderWrapper createNewCanvasGroupFolder(CanvasGroupFolderWrapper parentWrapper) {
        CanvasGroupFolder canvasGroupFolder = new CanvasGroupFolder();
        CanvasGroupFolder parent = folderWrapperTranslator.get(parentWrapper);
        parent.addCanvasGroup(canvasGroupFolder);
        CanvasGroupFolderWrapper canvasGroupFolderWrapper
                = new CanvasGroupFolderWrapper(canvasGroupFolder, renderingWrapper, this);
        folderWrapperTranslator.put(canvasGroupFolderWrapper, canvasGroupFolder);
        return canvasGroupFolderWrapper;
    }

    public void fillData(TaskData canvasTaskData) {
        canvasTaskData.grabData(this);
    }

    public void addToChangedSet(List<CanvasGroupLayer> layersChangedByTask) {
        changedCanvasGroupLayers.addAll(layersChangedByTask);

    }

    public CanvasRoot getCanvasRoot() {
        return canvasRoot;
    }

    public List<CanvasGroupWrapper> convertToWrapper(List<CanvasGroup> canvasGroups) {
        List<CanvasGroupWrapper> canvasGroupWrappers = new ArrayList<>();

        HashMap<CanvasGroup, CanvasGroupWrapper> groupToWrapper = new HashMap<>();

        for (Map.Entry<CanvasGroupLayerWrapper, CanvasGroupLayer> entry : layerWrapperTranslator.entrySet()) {
            groupToWrapper.put(entry.getValue(), entry.getKey());
        }
        for (Map.Entry<CanvasGroupFolderWrapper, CanvasGroupFolder> entry : folderWrapperTranslator.entrySet()) {
            groupToWrapper.put(entry.getValue(), entry.getKey());
        }

        for (CanvasGroup canvasGroup : canvasGroups) {
            canvasGroupWrappers.add(groupToWrapper.get(canvasGroup));
        }
        return canvasGroupWrappers;
    }

    public CanvasGroupFolderWrapper getRootWrapper() {
        return canvasRootWrapper;
    }
}
