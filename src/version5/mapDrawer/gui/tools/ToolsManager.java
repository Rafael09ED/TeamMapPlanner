package version5.mapDrawer.gui.tools;

import version5.mapDrawer.gui.GuiFrame;
import version5.mapDrawer.gui.actionManagement.MouseEventPasser;

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

        createButton(mouseTool);
    }
    private void createButton(MapPlannerTool mapPlannerTool){
        JToggleButton jButton = new JToggleButton(mapPlannerTool.getDisplayName());
        toolButtonBar.addJToggleButton(jButton);
        toolHashMap.put(jButton,mapPlannerTool);
        guiFrame.revalidate();
    }
    private void setCurrentTool(MapPlannerTool mapPlannerTool){
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
