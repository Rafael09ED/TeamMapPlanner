package version5.mapDrawer.interfacing.taskManagment.tasks;

import version5.mapDrawer.interfacing.taskManagment.TaskData;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;
import version5.mapDrawer.itemManagement.itemTracker.canvasItems.CanvasItem;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Rafael on 4/27/2015.
 */
public class Task_AddCanvasItem implements TaskManagerTask {

    private TaskData taskData;
    private final CanvasGroupLayerWrapper canvasGroupLayerWrapper;
    private CanvasGroupLayer canvasGroupLayer;
    private final CanvasItem canvasItem;
    private List<CanvasGroupLayer> canvasGroupLayersChanged;

    public Task_AddCanvasItem(CanvasGroupLayerWrapper canvasGroupLayerWrapper, CanvasItem canvasItem) {
        this.canvasGroupLayerWrapper = canvasGroupLayerWrapper;
        this.canvasItem = canvasItem;
        canvasGroupLayersChanged = new ArrayList<>();
    }

    @Override
    public void execute() throws NoSuchElementException{
        CanvasGroupLayer canvasGroupLayer = taskData.getCanvasGroupLayer(canvasGroupLayerWrapper);
        if(canvasGroupLayer == null) {
            throw new NoSuchElementException("TaskData CanvasGroup was null");
        }
        canvasGroupLayer.addCanvasItem(canvasItem);
        canvasGroupLayersChanged.add(canvasGroupLayer);
    }

    @Override
    public void passTaskData(TaskData taskData, UniqueTaskManagerTasks uniqueTasks) {
        this.taskData = taskData;
    }

    @Override
    public void unexecute() {
        canvasGroupLayer.removeCanvasItem(canvasItem);
    }

    @Override
    public void reexecute() {
        canvasGroupLayer.addCanvasItem(canvasItem);
    }

    @Override
    public List<CanvasGroupLayer> getLayersChangedByTask() {
        return canvasGroupLayersChanged;
    }
}
