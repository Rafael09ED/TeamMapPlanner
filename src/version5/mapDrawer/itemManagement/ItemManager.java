package version5.mapDrawer.itemManagement;

import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroup;
import version5.mapDrawer.itemManagement.taskManagment.TaskData;
import version5.mapDrawer.rendering.RenderingWrapper;

import java.util.HashMap;

/**
 * Created by Rafael on 4/24/2015.
 */
public class ItemManager {
    private final RenderingWrapper renderingWrapper;
    public HashMap<CanvasGroupWrapper, CanvasGroup> wrapperTranslator;

    public ItemManager(RenderingWrapper renderingWrapper) {
        this.renderingWrapper = renderingWrapper;
        wrapperTranslator = new HashMap<>();
        //ToDo
    }

    public void fillData(TaskData canvasTaskData) {
        CanvasGroup canvasGroup = wrapperTranslator.get(canvasTaskData.getCanvasGroupWrapper());
    }
    public void associateCanvasWrapper(CanvasGroup canvasGroup, CanvasGroupWrapper canvasGroupWrapper){
        wrapperTranslator.put(canvasGroupWrapper,canvasGroup);
    }
}
