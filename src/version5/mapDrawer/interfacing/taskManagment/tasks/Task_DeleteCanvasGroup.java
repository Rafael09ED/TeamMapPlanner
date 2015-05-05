package version5.mapDrawer.interfacing.taskManagment.tasks;

import version5.mapDrawer.interfacing.CanvasGroupTypeActionable;
import version5.mapDrawer.interfacing.taskManagment.TaskData;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupFolderWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroup;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupFolder;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Rafael on 5/4/2015.
 */
public class Task_DeleteCanvasGroup implements TaskManagerTask {
    private final CanvasGroupWrapper canvasGroupWrapperToRemove;
    private TaskData taskData;
    private CanvasGroup canvasGroupDeleted;
    private CanvasGroupFolder parent;
    private int childIndexInParent;

    public Task_DeleteCanvasGroup(CanvasGroupWrapper canvasGroupWrapperToRemove) {
        this.canvasGroupWrapperToRemove = canvasGroupWrapperToRemove;
    }

    @Override
    public void execute() throws NoSuchElementException {
        canvasGroupWrapperToRemove.callTypeActionable(new GetUnwrappedGroup());
        parent = taskData.getCanvasGroupFolder(taskData.getRootWrapped()).findParent(canvasGroupDeleted);
        if (parent == null){
            throw new NoSuchElementException("The Parent or Child you tried to deleted was not found or valid");
        }
        childIndexInParent = parent.removeChild(canvasGroupDeleted);
    }

    @Override
    public void passTaskData(TaskData taskData, UniqueTaskManagerTasks uniqueTasks) {
        this.taskData = taskData;
    }

    @Override
    public void unexecute() {
        if (childIndexInParent >= 0) {
            parent.addCanvasGroup(canvasGroupDeleted,childIndexInParent);
        } else {
            System.err.println("The Index Returned was <0 so no item was re-added since none was removed");
        }
    }

    @Override
    public void reexecute() {
        childIndexInParent = parent.removeChild(canvasGroupDeleted);
    }

    @Override
    public List<CanvasGroupLayer> getLayersChangedByTask() {
        List<CanvasGroupLayer> canvasGroupLayersAffected = new ArrayList<>();
        return canvasGroupLayersAffected;
    }


    private class GetUnwrappedGroup implements CanvasGroupTypeActionable{
        @Override
        public void doIfCanvasLayer(CanvasGroupLayerWrapper canvasGroupLayerWrapper) {
            canvasGroupDeleted = taskData.getCanvasGroupLayer(canvasGroupLayerWrapper);
        }

        @Override
        public void doIfCanvasFolder(CanvasGroupFolderWrapper canvasGroupFolderWrapper) {
            canvasGroupDeleted = taskData.getCanvasGroupFolder(canvasGroupFolderWrapper);
        }
    }
}
