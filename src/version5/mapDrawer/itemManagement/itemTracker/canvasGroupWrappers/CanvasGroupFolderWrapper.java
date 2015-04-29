package version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers;

import version5.mapDrawer.interfacing.CanvasGroupTypeActionable;
import version5.mapDrawer.itemManagement.ItemManager;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupFolder;
import version5.mapDrawer.rendering.RenderingWrapper;
import version5.mapDrawer.rendering.optimization.RenderData;

/**
 * Created by Rafael on 4/25/2015.
 */
public class CanvasGroupFolderWrapper implements CanvasGroupWrapper {
    private final CanvasGroupFolder canvasGroupFolder;
    private RenderingWrapper renderingWrapper;
    private final ItemManager itemManager;
    private long timeLastChanged;
    private String displayName;

    public CanvasGroupFolderWrapper(CanvasGroupFolder canvasGroupFolder, RenderingWrapper renderingWrapper, ItemManager itemManager) {
        this.canvasGroupFolder = canvasGroupFolder;
        this.renderingWrapper = renderingWrapper;
        this.itemManager = itemManager;
        setDisplayName("Folder " + canvasGroupFolder.getFolderNumber());
        notifyOfChange();
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String newDisplayName) {
        displayName = newDisplayName;
    }

    @Override
    public RenderData getCanvasGroupRender() {
        return renderingWrapper.getRender(canvasGroupFolder);
    }

    @Override
    public long getLastChangedTime() {
        return timeLastChanged;
    }

    @Override
    public void notifyOfChange() {
        timeLastChanged = System.currentTimeMillis();
    }

    @Override
    public void callTypeActionable(CanvasGroupTypeActionable canvasGroupTypeActionable) {
        canvasGroupTypeActionable.doIfCanvasFolder(this);
    }
}
