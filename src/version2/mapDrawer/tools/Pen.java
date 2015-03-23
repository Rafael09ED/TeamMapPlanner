package version2.mapDrawer.tools;

import testingTools.printEverySec;
import version2.mapDrawer.GraphicsObjectTracker;

import java.awt.event.MouseEvent;

/**
 * Created by Rafael on 3/20/2015.
 */
public class Pen extends MapDrawerTool {

    private boolean mouseDown = false;
    private printEverySec printer;

    public Pen(GraphicsObjectTracker graphicsObjectTracker) {
        super(graphicsObjectTracker);

        printer = new printEverySec("Pen Status:");

    }

    @Override
    public String getToolString() {
        return "Pen";
    }

    @Override
    public String getToolDisplayName() {
        return "Pen";
    }

    @Override
    public void toolSelected() {

    }

    @Override
    public void toolDeSelected() {

    }

    @Override
    public void update() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
        printer.setAppendToPrint(mouseDown + "");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
        printer.setAppendToPrint(mouseDown + "");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
