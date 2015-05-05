package version5.mapDrawer.interfacing.taskManagment.tasks;

import version5.mapDrawer.interfacing.taskManagment.TaskData;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Rafael on 4/26/2015.
 */
public interface TaskManagerTask{
    void execute() throws NoSuchElementException;
    void passTaskData(TaskData taskData, UniqueTaskManagerTasks uniqueTasks);
    void unexecute();
    void reexecute();
    List<CanvasGroupLayer> getLayersChangedByTask();
}
