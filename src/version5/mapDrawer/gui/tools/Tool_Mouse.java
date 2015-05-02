package version5.mapDrawer.gui.tools;

import version5.mapDrawer.interfacing.taskManagment.tasks.Task_AddCanvasItem;
import version5.mapDrawer.interfacing.taskManagment.tasks.Task_NewCanvasLayer;
import version5.mapDrawer.itemManagement.infoTypes.Point2D;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasItems.Item_Line;

import java.awt.event.MouseEvent;

/**
 * Created by Rafael on 5/1/2015.
 */
public class Tool_Mouse implements MapPlannerTool{
    private final ToolsManager toolsManager;
    private final static String displayName = "Mouse";
    public Tool_Mouse(ToolsManager toolsManager) {
        this.toolsManager = toolsManager;
        toolsManager.guiFrame.taskManager.doNewTask(new Task_NewCanvasLayer(toolsManager.guiFrame.dataGrabber.getRootWrapper()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO: Remove Demo Code after testing is done
        System.out.println("clicked");
        CanvasGroupWrapper firstInstance = toolsManager.guiFrame.dataGrabber.getChildrenWrapped(toolsManager.guiFrame.dataGrabber.getRootWrapper()).get(0);
        if (firstInstance instanceof CanvasGroupLayerWrapper) {
            toolsManager.guiFrame.taskManager.doNewTask(new Task_AddCanvasItem((CanvasGroupLayerWrapper) firstInstance,
                            new Item_Line(randomPointOnCanvas(), randomPointOnCanvas())));
            System.out.println("We added a line");
        }
        toolsManager.guiFrame.renderCanvas();
    }

    private Point2D randomPointOnCanvas() {
        return new Point2D(Math.random() * toolsManager.guiFrame.getFrameBoundingBox().getWidth() - 1, Math.random() * toolsManager.guiFrame.getFrameBoundingBox().getHeight() - 1);
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
