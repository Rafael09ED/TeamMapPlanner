package version5.mapDrawer.gui.tools;

import java.awt.event.MouseListener;

/**
 * Created by Rafael on 5/1/2015.
 */
public interface MapPlannerTool extends MouseListener {
    String getDisplayName();
    void toolSelected();
    void toolDeSelected();
}
