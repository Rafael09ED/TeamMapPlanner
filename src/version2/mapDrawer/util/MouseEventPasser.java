package version2.mapDrawer.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Rafael on 3/20/2015.
 */
public class MouseEventPasser implements MouseListener, MouseMotionListener {
    private MouseInput toolToSendTo;


    public MouseEventPasser(MouseInput toolToSendTo){
        this.toolToSendTo = toolToSendTo;
    }
    public void setToolToSendTo(MouseInput toolToSendTo){
        this.toolToSendTo = toolToSendTo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        toolToSendTo.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        toolToSendTo.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        toolToSendTo.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        toolToSendTo.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        toolToSendTo.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        toolToSendTo.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        toolToSendTo.mouseMoved(e);
    }
}
