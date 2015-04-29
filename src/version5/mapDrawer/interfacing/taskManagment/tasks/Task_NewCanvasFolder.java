package version5.mapDrawer.interfacing.taskManagment.tasks;

import version5.mapDrawer.interfacing.taskManagment.TaskData;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupFolderWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Rafael on 4/28/2015.
 */
public class Task_NewCanvasFolder implements TaskManagerTask {
    private final CanvasGroupFolderWrapper parentFolder;
    private UniqueTaskManagerTasks uniqueTaskManagerTasks;
    private CanvasGroupFolderWrapper canvasGroupFolderWrapper;

    public Task_NewCanvasFolder(CanvasGroupFolderWrapper parentFolder) {
        this.parentFolder = parentFolder;
    }

    @Override
    public void execute() throws NoSuchElementException {
        canvasGroupFolderWrapper = uniqueTaskManagerTasks.createNewCanvasFolder(parentFolder);
    }

    @Override
    public TaskData getCanvasTaskData() {
        return new TaskData();
    }

    @Override
    public void unexecute() {
//TODO FIX
    }

    @Override
    public void reexecute() {
//TODO FIX
    }

    @Override
    public List<CanvasGroupLayer> getLayersChangedByTask() {
        return new ArrayList<>();
    }

    @Override
    public void passUniqueTasks(UniqueTaskManagerTasks uniqueTaskManagerTasks) {
        this.uniqueTaskManagerTasks = uniqueTaskManagerTasks;
    }
}
