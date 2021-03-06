package oldCode.version4.mapDrawer.gui;

import oldCode.version4.mapDrawer.itemTracker.CanvasItemTracker;
import oldCode.version4.mapDrawer.itemTracker.canvasGroups.CanvasGroup;
import oldCode.version4.mapDrawer.itemTracker.canvasGroups.CanvasGroupFolder;
import oldCode.version4.mapDrawer.itemTracker.canvasGroups.CanvasGroupLayer;
import oldCode.version4.mapDrawer.itemTracker.canvasItems.CanvasItem;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rafael on 4/14/2015.
 */
public class ItemTrackerInterface {
    private CanvasItemTracker canvasItemTracker;
    private boolean layersChanged;
    private Set<CanvasGroupLayer> changedLayers;

    public ItemTrackerInterface(CanvasItemTracker canvasItemTracker) {
        this.canvasItemTracker = canvasItemTracker;
        changedLayers = new HashSet<>();
    }

    public void newLayer(CanvasGroupFolder currentCanvasFolder, boolean inParent) {
        if (currentCanvasFolder == null){
            currentCanvasFolder = canvasItemTracker;
        } else if (inParent) {
            currentCanvasFolder = currentCanvasFolder.getParent();
        }

        CanvasGroupLayer layerToAdd = new CanvasGroupLayer(currentCanvasFolder);
        currentCanvasFolder.addCanvasGroup(layerToAdd);
        changedLayers.add(layerToAdd);

        layersChanged = true;
    }
    public boolean hasLayerOrderChanged(boolean resetChanged){

        boolean hasChangedToReturn = layersChanged;
        if (resetChanged){
            layersChanged = false;
        }

        return hasChangedToReturn;
    }
    public Set<CanvasGroupLayer> getSetOfChangedLayers(boolean resetChanged){
        Set<CanvasGroupLayer> setToSend = changedLayers;
        if (resetChanged) {
            changedLayers = new HashSet<>();
        }
        return setToSend;
    }


    public void addCanvasItemTo(CanvasItem canvasItem, CanvasGroupLayer canvasGroupLayer) {
        canvasGroupLayer.addCanvasItem(canvasItem);
        changedLayers.add(canvasGroupLayer);
    }

    public CanvasGroupFolder getRoot() {
        return canvasItemTracker;
    }

    public boolean deleteCanvasGroup(CanvasGroup groupToDelete) {
        if (groupToDelete == getRoot()){
            return false;
        }
        CanvasGroupFolder parent = groupToDelete.getParent();
        boolean hasDeleted = parent.removeChild(groupToDelete);

        if (hasDeleted){
            hasLayerOrderChanged(true);
        }

        return hasDeleted;
    }

    public void addLayer(CanvasGroupFolder canvasGroupFolder) {

    }
}
