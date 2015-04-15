package version4.mapDrawer.gui;

import version4.mapDrawer.itemTracker.CanvasItemTracker;
import version4.mapDrawer.itemTracker.canvasGroups.CanvasGroupFolder;
import version4.mapDrawer.itemTracker.canvasGroups.CanvasGroupLayer;
import version4.mapDrawer.itemTracker.canvasItems.CanvasItem;

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
    public boolean hasChanged(boolean resetChanged){

        boolean hasChangedToReturn = layersChanged;
        if (resetChanged){
            layersChanged = false;
        }

        return hasChangedToReturn;
    }
    public Set<CanvasGroupLayer> changedSet(boolean resetChanged){
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
}
