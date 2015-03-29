package version2.mapDrawer.tools;

import testingTools.printEverySec;
import version2.mapDrawer.DrawingCanvas;
import version2.mapDrawer.GraphicsObjectTracker;
import version2.mapDrawer.tools.toolBars.ToolToolBar;

import java.awt.event.MouseEvent;

public class MouseTool extends MapDrawerTool {

    private boolean mouseDown = false;


    public MouseTool(GraphicsObjectTracker graphicsObjectTracker, DrawingCanvas drawingCanvas) {
        super(graphicsObjectTracker, drawingCanvas);
        printer = new printEverySec("Mouse Status:");
    }

    @Override
    public String getToolString() {
        return "Mouse";
    }

    @Override
    public String getToolDisplayName() {
        return "Mouse";
    }

    @Override
    public void toolSelected() {
        printer.setAppendToPrint("Not Clicked");
    }

    @Override
    public void toolDeSelected() {
        mouseDown = false;
        printer.setAppendToPrint("Not Selected");
    }

    @Override
    public void update() {

    }

    @Override
    public ToolToolBar getToolbar() {
        return null;
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
