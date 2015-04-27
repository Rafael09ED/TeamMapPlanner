package version5.mapDrawer;

import version5.mapDrawer.itemManagement.ItemManager;
import version5.mapDrawer.itemManagement.itemTracker.CanvasTracker;
import version5.mapDrawer.itemManagement.taskManagment.TaskManager;
import version5.mapDrawer.rendering.RenderingWrapper;

/**
 * Created by Rafael on 4/24/2015.
 */
public class MapDrawerCore {
    public static void main(){
        new MapDrawerCore();
    }

    private final ItemManager itemManager;
    private final RenderingWrapper renderingWrapper;
    private final TaskManager taskManager;

    public MapDrawerCore() {
        renderingWrapper = initializeRenderingWrapper();
        CanvasTracker canvasTracker = new CanvasTracker();
        itemManager = initializeItemManager(renderingWrapper);
        taskManager = initializeTaskManager(itemManager);

    }

    private static RenderingWrapper initializeRenderingWrapper(){
        RenderingWrapper TEMP_renderingWrapper = new RenderingWrapper();
        return TEMP_renderingWrapper;
    }


    private static ItemManager initializeItemManager(RenderingWrapper renderingWrapper){
        ItemManager TEMP_itemManager = new ItemManager(renderingWrapper);
        return TEMP_itemManager;
    }

    private static void initializeDataGrabber(){

    }

    private static TaskManager initializeTaskManager(ItemManager itemManager){
        TaskManager TEMP_taskManager = new TaskManager(itemManager);
        return TEMP_taskManager;
    }
}
