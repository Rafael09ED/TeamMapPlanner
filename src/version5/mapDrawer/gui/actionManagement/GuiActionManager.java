package version5.mapDrawer.gui.actionManagement;

import version5.mapDrawer.gui.GuiFrame;
import version5.mapDrawer.gui.tools.ToolsManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Rafael on 5/1/2015.
 */
public class GuiActionManager {
    private final GuiFrame guiFrame;
    private final ToolsManager toolsManager;
    public MouseListener canvasClickActionListener;
    public GuiActionManager(GuiFrame guiFrame, ToolsManager toolsManager) {
        this.guiFrame = guiFrame;
        this.toolsManager = toolsManager;

        //Initialize ActionListeners
        canvasClickActionListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toolsManager.getMouseEventPasser().mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                toolsManager.getMouseEventPasser().mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                toolsManager.getMouseEventPasser().mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                toolsManager.getMouseEventPasser().mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                toolsManager.getMouseEventPasser().mouseExited(e);
            }
        };

    }
}
