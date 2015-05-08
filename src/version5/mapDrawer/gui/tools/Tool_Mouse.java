package version5.mapDrawer.gui.tools;

import version5.mapDrawer.interfacing.taskManagment.tasks.Task_NewCanvasLayer;

import java.awt.event.MouseEvent;

/**
 * Created by Rafael on 5/1/2015.
 */
public class Tool_Mouse implements MapPlannerTool{
    private final ToolsManager toolsManager;
    private final static String displayName = "Mouse";
    public Tool_Mouse(ToolsManager toolsManager) {
        this.toolsManager = toolsManager;
        toolsManager.guiFrame.taskManager.doNewTask(new Task_NewCanvasLayer(toolsManager.guiFrame.groupDataInterface.getRootWrapper()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked");

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void toolSelected() {
        System.out.println("Mouse Tool Selected");
    }

    @Override
    public void toolDeSelected() {

    }
}
