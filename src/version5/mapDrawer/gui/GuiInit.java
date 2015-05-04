package version5.mapDrawer.gui;

import version5.SETTINGS.GENERAL_SETTINGS;
import version5.mapDrawer.interfacing.DataGrabber;
import version5.mapDrawer.interfacing.taskManagment.TaskManager;

/**
 * Created by Rafael on 4/30/2015.
 */
public class GuiInit {
    private final DataGrabber dataGrabber;
    private final TaskManager taskManager;
    private final GuiFrame GuiFrame;

    public GuiInit(DataGrabber dataGrabber, TaskManager taskManager) {
        this.dataGrabber = dataGrabber;
        this.taskManager = taskManager;

        String programTitle = GENERAL_SETTINGS.PROGRAM_TITLE;
        GuiFrame = new GuiFrame(programTitle,dataGrabber, taskManager);
    }
}