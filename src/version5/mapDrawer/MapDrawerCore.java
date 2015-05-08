package version5.mapDrawer;

import version5.mapDrawer.gui.GuiInit;
import version5.mapDrawer.interfacing.GroupDataInterface;
import version5.mapDrawer.interfacing.taskManagment.TaskManager;
import version5.mapDrawer.itemManagement.ItemManager;
import version5.mapDrawer.itemManagement.itemTracker.CanvasRoot;
import version5.mapDrawer.rendering.RenderingWrapper;

/**
 * Created by Rafael on 4/24/2015.
 */
public class MapDrawerCore {

    public static void main(String[] args){
        new MapDrawerCore();
    }
    private final GroupDataInterface groupDataInterface;
    private final ItemManager itemManager;
    private final RenderingWrapper renderingWrapper;
    private final TaskManager taskManager;
    private final GuiInit gui;

    public MapDrawerCore() {
        renderingWrapper = initializeRenderingWrapper();
        CanvasRoot canvasRoot = initializeCanvasTracker();
        itemManager = initializeItemManager(renderingWrapper, canvasRoot);
        taskManager = initializeTaskManager(itemManager);
        groupDataInterface = initializeDataGrabber(itemManager);
        gui = new GuiInit(groupDataInterface, taskManager);
    }

    private static CanvasRoot initializeCanvasTracker() {
        CanvasRoot TEMP_canvasRoot = new CanvasRoot();
        return TEMP_canvasRoot;
    }

    private static RenderingWrapper initializeRenderingWrapper(){
        RenderingWrapper TEMP_renderingWrapper = new RenderingWrapper();
        return TEMP_renderingWrapper;
    }


    private static ItemManager initializeItemManager(RenderingWrapper renderingWrapper, CanvasRoot canvasRoot){
        ItemManager TEMP_itemManager = new ItemManager(canvasRoot, renderingWrapper);
        return TEMP_itemManager;
    }

    private static GroupDataInterface initializeDataGrabber(ItemManager itemManager){
        GroupDataInterface TEMP_Group_DataProcessor = new GroupDataInterface(itemManager);
        return TEMP_Group_DataProcessor;
    }

    private static TaskManager initializeTaskManager(ItemManager itemManager){
        TaskManager TEMP_taskManager = new TaskManager(itemManager);
        return TEMP_taskManager;
    }
}
