package version5.mapDrawer.gui;

import version5.SETTINGS.GENERAL_SETTINGS;
import version5.mapDrawer.interfacing.GroupDataInterface;
import version5.mapDrawer.interfacing.taskManagment.TaskManager;

/**
 * Created by Rafael on 4/30/2015.
 */
public class GuiInit {
    private final GroupDataInterface groupDataInterface;
    private final TaskManager taskManager;
    private final GuiFrame GuiFrame;

    public GuiInit(GroupDataInterface groupDataInterface, TaskManager taskManager) {
        this.groupDataInterface = groupDataInterface;
        this.taskManager = taskManager;

        String programTitle = GENERAL_SETTINGS.PROGRAM_TITLE;
        GuiFrame = new GuiFrame(programTitle, groupDataInterface, taskManager);
    }
}
