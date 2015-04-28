package version5.mapDrawer.itemManagement.taskManagment;

import version5.mapDrawer.itemManagement.ItemManager;
import version5.mapDrawer.itemManagement.taskManagment.tasks.TaskManagerTask;

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

    public TaskManager(ItemManager itemManager) {
        this.itemManager = itemManager;
        taskQue = new ArrayList<>();
    }

    /**
     * executes a task for the first time, adds it to the done task que
     * prepares for an undo
     * removes any possible redo actions
     * @param taskManagerTask task to run
     */
    public void doNewTask(TaskManagerTask taskManagerTask){
        taskQue.add(taskManagerTask);

        itemManager.fillData(taskManagerTask.getCanvasTaskData());

        try {
            taskManagerTask.execute();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        taskIndex++;

        taskQue.subList(taskIndex,taskQue.size()-1).clear();
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
            taskQue.get(taskIndex++).reexecute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
