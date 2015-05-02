package version5.mapDrawer;

import version5.mapDrawer.gui.GuiInit;
import version5.mapDrawer.interfacing.DataGrabber;
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
    private final DataGrabber dataGrabber;
    private final ItemManager itemManager;
    private final RenderingWrapper renderingWrapper;
    private final TaskManager taskManager;
    private final GuiInit gui;

    public MapDrawerCore() {
        renderingWrapper = initializeRenderingWrapper();
        CanvasRoot canvasRoot = initializeCanvasTracker();
        itemManager = initializeItemManager(renderingWrapper, canvasRoot);
        taskManager = initializeTaskManager(itemManager);
        dataGrabber = initializeDataGrabber(itemManager);
        gui = new GuiInit(dataGrabber, taskManager);
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

    private static DataGrabber initializeDataGrabber(ItemManager itemManager){
        DataGrabber TEMP_DataGrabber = new DataGrabber(itemManager);
        return TEMP_DataGrabber;
    }

    private static TaskManager initializeTaskManager(ItemManager itemManager){
        TaskManager TEMP_taskManager = new TaskManager(itemManager);
        return TEMP_taskManager;
    }
}
