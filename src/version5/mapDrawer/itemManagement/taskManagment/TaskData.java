package version5.mapDrawer.itemManagement.taskManagment;

import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroup;

/**
 * Created by Rafael on 4/27/2015.
 */
public class TaskData {
    private CanvasGroup canvasGroup;
    private CanvasGroupWrapper canvasGroupWrapper;

    public TaskData() {
    }

    public CanvasGroup getCanvasGroup() {
        return canvasGroup;
    }

    public void setCanvasGroup(CanvasGroup canvasGroup) {
        this.canvasGroup = canvasGroup;
    }

    public CanvasGroupWrapper getCanvasGroupWrapper() {
        return canvasGroupWrapper;
    }

    public void setCanvasGroupWrapper(CanvasGroupWrapper canvasGroupWrapper) {
        this.canvasGroupWrapper = canvasGroupWrapper;
    }
}
