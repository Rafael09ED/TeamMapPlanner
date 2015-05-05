package version5.mapDrawer.interfacing.taskManagment.tasks;

import version5.mapDrawer.interfacing.taskManagment.TaskData;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupFolderWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Rafael on 4/28/2015.
 */
public class Task_NewCanvasLayer implements TaskManagerTask {

    private final CanvasGroupFolderWrapper parentFolder;
    private UniqueTaskManagerTasks uniqueTaskManagerTasks;
    private CanvasGroupLayerWrapper canvasGroupLayerWrapper;

    public Task_NewCanvasLayer(CanvasGroupFolderWrapper parentFolder) {
        this.parentFolder = parentFolder;
    }

    @Override
    public void execute() throws NoSuchElementException {
        canvasGroupLayerWrapper = uniqueTaskManagerTasks.createNewCanvasLayer(parentFolder);
    }

    @Override
    public void passTaskData(TaskData taskData, UniqueTaskManagerTasks uniqueTasks) {
        uniqueTaskManagerTasks = uniqueTasks;
    }

    @Override
    public void unexecute() {
//TODO: FIX
    }

    @Override
    public void reexecute() {
// TODO: TODO
    }

    @Override
    public List<CanvasGroupLayer> getLayersChangedByTask() {
        //TODO Fix
        canvasGroupLayerWrapper.getCanvasGroupRender();
        return new ArrayList<>();
    }

}
