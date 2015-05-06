package version5.mapDrawer.gui.tools;

import version5.mapDrawer.gui.GuiFrame;
import version5.mapDrawer.gui.actionManagement.MouseEventPasser;
import version5.mapDrawer.interfacing.taskManagment.tasks.Task_AddCanvasItem;
import version5.mapDrawer.itemManagement.infoTypes.Point2D;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasItems.Item_Line;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rafael on 5/1/2015.
 */
public class ToolsManager {
    public final GuiFrame guiFrame;
    private final ToolButtonBar toolButtonBar;
    private final MouseEventPasser mouseEventPasser;
    private final List<MapPlannerTool> mapPlannerTools;
    private HashMap<JToggleButton, MapPlannerTool> toolHashMap;
    private OnlyOneJToggleDown jToggleDown;

    public ToolsManager(GuiFrame guiFrame) {
        this.guiFrame = guiFrame;
        this.toolButtonBar = new ToolButtonBar();

        toolHashMap = new HashMap<>();
        jToggleDown = new OnlyOneJToggleDown();
        mapPlannerTools = new ArrayList<>();

        Tool_Mouse mouseTool = new Tool_Mouse(this);
        mouseEventPasser = new MouseEventPasser();
        {
            JButton jButtonToAdd;
            jButtonToAdd = new JButton("TESTING RANDOM LINE");
            jButtonToAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CanvasGroupWrapper firstInstance = guiFrame.getSelectedGroup();
                    if (firstInstance instanceof CanvasGroupLayerWrapper) {
                        guiFrame.taskManager.doNewTask(new Task_AddCanvasItem((CanvasGroupLayerWrapper) firstInstance,
                                new Item_Line(randomPointOnCanvas(), randomPointOnCanvas())));
                        System.out.println("We added a line");

                        guiFrame.renderCanvas();
                    }
                }
            });
            toolButtonBar.add(jButtonToAdd);


        }
        createButton(mouseTool);

    }

    private Point2D randomPointOnCanvas() {
        return new Point2D(Math.random() * guiFrame.getFrameBoundingBox().getWidth() - 1, Math.random() * guiFrame.getFrameBoundingBox().getHeight() - 1);
    }

    private void createButton(MapPlannerTool mapPlannerTool) {
        JToggleButton jToggleButtonToAdd;
        jToggleButtonToAdd = new JToggleButton(mapPlannerTool.getDisplayName());
        toolButtonBar.addJToggleButton(jToggleButtonToAdd);
        toolHashMap.put(jToggleButtonToAdd, mapPlannerTool);

        guiFrame.revalidate();
    }

    private void setCurrentTool(MapPlannerTool mapPlannerTool) {
        mouseEventPasser.setToolToSendTo(mapPlannerTool);

    }

    public MouseEventPasser getMouseEventPasser() {
        return mouseEventPasser;
    }

    public ToolButtonBar getToolButtonBar() {
        return toolButtonBar;
    }

    public class ToolButtonBar extends JToolBar {
        public ToolButtonBar() {
            setOrientation(SwingConstants.VERTICAL);
        }

        public void addJToggleButton(JToggleButton jButton) {
            add(jButton);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button Clicked");
                    setCurrentTool(toolHashMap.get(e.getSource()));
                    jToggleDown.setSelectedTool((JToggleButton) e.getSource());
                }
            });
            revalidate();
        }
    }
}
