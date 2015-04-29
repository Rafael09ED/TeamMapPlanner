package version5.mapDrawer.interfacing;

import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupFolderWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;

/**
 * Created by Rafael on 4/28/2015.
 */
public interface CanvasGroupTypeActionable {
    void doIfCanvasLayer(CanvasGroupLayerWrapper canvasGroupLayerWrapper);
    void doIfCanvasFolder(CanvasGroupFolderWrapper canvasGroupFolderWrapper);
}
