package version5.mapDrawer.gui.actionManagement;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Rafael on 3/20/2015.
 */
public class MouseEventPasser implements MouseListener {
    private MouseListener toolToSendTo;

    public MouseEventPasser(MouseListener toolToSendTo) {
        this.toolToSendTo = toolToSendTo;
    }

    public MouseEventPasser() {
    }

    public void setToolToSendTo(MouseListener toolToSendTo) {
        this.toolToSendTo = toolToSendTo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (toolToSendTo != null) {
            toolToSendTo.mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (toolToSendTo != null) {
            toolToSendTo.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (toolToSendTo != null) {
            toolToSendTo.mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (toolToSendTo != null) {
            toolToSendTo.mouseEntered(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (toolToSendTo != null) {
            toolToSendTo.mouseExited(e);
        }
    }
}
