package version5.mapDrawer.itemManagement.taskManagment.tasks;

import version5.mapDrawer.itemManagement.taskManagment.TaskData;

import java.util.NoSuchElementException;

/**
 * Created by Rafael on 4/26/2015.
 */
public interface TaskManagerTask{
    void execute() throws NoSuchElementException;
    TaskData getCanvasTaskData();
    void notifyChanged();
    void unexecute();
    void reexecute();
}
