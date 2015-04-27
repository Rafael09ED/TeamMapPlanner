package version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers;

import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupFolder;

import java.awt.image.BufferedImage;

/**
 * Created by Rafael on 4/25/2015.
 */
public class CanvasGroupFolderWrapper implements CanvasGroupWrapper {
    private long timeLastChanged;
    private String displayName;

    public CanvasGroupFolderWrapper(CanvasGroupFolder canvasGroupFolder) {
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
    public BufferedImage getCanvasGroupRender() {
        //TODO
        return null;
    }

    @Override
    public long getLastChangedTime() {
        return timeLastChanged;
    }

    @Override
    public void notifyOfChange() {
        timeLastChanged = System.currentTimeMillis();
    }
}
