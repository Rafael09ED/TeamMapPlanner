package version5.mapDrawer.itemManagement.taskManagment.tasks;

import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;
import version5.mapDrawer.itemManagement.itemTracker.canvasItems.CanvasItem;
import version5.mapDrawer.itemManagement.taskManagment.TaskData;

import java.util.NoSuchElementException;

/**
 * Created by Rafael on 4/27/2015.
 */
public class addCanvasItem implements TaskManagerTask {

    private final TaskData taskData;
    private final CanvasGroupLayerWrapper canvasGroupLayerWrapper;
    private CanvasGroupLayer canvasGroupLayer;
    private final CanvasItem canvasItem;

    public addCanvasItem(CanvasGroupLayerWrapper canvasGroupLayerWrapper, CanvasItem canvasItem) {
        this.canvasGroupLayerWrapper = canvasGroupLayerWrapper;
        this.canvasItem = canvasItem;
        taskData = new TaskData();
        taskData.setCanvasGroupWrapper(canvasGroupLayerWrapper);
    }

    @Override
    public void execute() throws NoSuchElementException{
        if(taskData.getCanvasGroup() == null) {
            throw new NoSuchElementException("TaskData CanvasGroup was null");
        }
        canvasGroupLayer = (CanvasGroupLayer) taskData.getCanvasGroup();
    }

    @Override
    public TaskData getCanvasTaskData() {
        return taskData;
    }

    @Override
    public void notifyChanged() {
        canvasGroupLayerWrapper.notifyOfChange();
    }

    @Override
    public void unexecute() {

    }

    @Override
    public void reexecute() {
        execute();
    }
}
