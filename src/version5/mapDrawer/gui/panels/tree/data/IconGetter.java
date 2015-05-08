package version5.mapDrawer.gui.panels.tree.data;

import version5.mapDrawer.interfacing.CanvasGroupTypeActionable;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupFolderWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupWrapper;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;

/**
 * Created by Rafael on 5/5/2015.
 */
public class IconGetter implements CanvasGroupTypeActionable {
    private Icon icon = MetalIconFactory.getTreeComputerIcon();
    public IconGetter(CanvasGroupWrapper canvasGroupWrapper) {
        canvasGroupWrapper.callTypeActionable(this);
    }

    @Override
    public void doIfCanvasLayer(CanvasGroupLayerWrapper canvasGroupLayerWrapper) {
        icon = MetalIconFactory.getTreeFloppyDriveIcon();
    }

    @Override
    public void doIfCanvasFolder(CanvasGroupFolderWrapper canvasGroupFolderWrapper) {
        icon = MetalIconFactory.getTreeHardDriveIcon();
    }

    public Icon getIcon() {
        return icon;
    }
}
