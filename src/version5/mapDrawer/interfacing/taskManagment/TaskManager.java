package version5.mapDrawer.interfacing.taskManagment;

import version5.mapDrawer.interfacing.taskManagment.tasks.UniqueTaskManagerTasks;
import version5.mapDrawer.itemManagement.ItemManager;
import version5.mapDrawer.interfacing.taskManagment.tasks.TaskManagerTask;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Rafael on 4/26/2015.
 */
public class TaskManager {
    private final ItemManager itemManager;
    private List<TaskManagerTask> taskQue;
    private int taskIndex = 0;
    private final UniqueTaskManagerTasks uniqueTasks;

    public TaskManager(ItemManager itemManager) {
        this.itemManager = itemManager;
        taskQue = new ArrayList<>();
        uniqueTasks = new UniqueTaskManagerTasks(itemManager);
    }

    /**
     * executes a task for the first time, adds it to the done task que
     * prepares for an undo
     * removes any possible redo actions
     * @param taskManagerTask task to run
     */
    public void doNewTask(TaskManagerTask taskManagerTask){
        if (taskQue.size()-1 >= taskIndex) {
            taskQue.subList(taskIndex, taskQue.size() - 1).clear();
        }
        taskQue.add(taskManagerTask);
        taskManagerTask.passUniqueTasks(uniqueTasks);
        itemManager.fillData(taskManagerTask.getCanvasTaskData());

        try {
            taskManagerTask.execute();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        //TODO BUG: Updating change list causes bounding box to be 0;
        itemManager.addToChangedSet(taskManagerTask.getLayersChangedByTask());
        taskIndex++;


    }

    public boolean undoTask(){
        try {
            int index = --taskIndex;
            taskQue.get(index).unexecute();
            itemManager.addToChangedSet(taskQue.get(index).getLayersChangedByTask());
            return true;
        }catch (Exception e){
            taskIndex++;
            e.printStackTrace();
        }

        return false;
    }
    public boolean redoTask(){
        try {
            taskQue.get(taskIndex).reexecute();
            itemManager.addToChangedSet(taskQue.get(taskIndex).getLayersChangedByTask());
            taskIndex++;
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public ItemManager getItemManager(){
        return itemManager;
    }
}
