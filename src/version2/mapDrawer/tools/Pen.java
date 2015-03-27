package version2.mapDrawer.tools;

import testingTools.printEverySec;
import version2.mapDrawer.DrawingCanvas;
import version2.mapDrawer.GraphicsObjectTracker;
import version2.mapDrawer.graphicsObjects.Line;
import version2.mapDrawer.tools.toolBars.PenToolBar;
import version2.mapDrawer.tools.toolBars.ToolToolBar;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Rafael on 3/20/2015.
 */
public class Pen extends MapDrawerTool {

    private static final String TOOL_NAME_STRING= "Pen";
    private boolean mouseDown = false;
    private printEverySec printer;
    private Line currentLine;
    private Color currentColor;
    private int currentStroke;
    private PenToolBar toolBar;

    public Pen(GraphicsObjectTracker graphicsObjectTracker, DrawingCanvas drawingCanvas) {
        super(graphicsObjectTracker, drawingCanvas);
        printer = new printEverySec("Pen Status:");
        currentColor = Color.BLACK;
        currentStroke = 1;
        toolBar = new PenToolBar(this);
    }

    public void setCurrentStroke(int currentStroke) {
        if (currentStroke <= 0){
            return;
        }
        this.currentStroke = currentStroke;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    @Override
    public String getToolString() {
        return TOOL_NAME_STRING;
    }

    @Override
    public String getToolDisplayName() {
        return "Pen";
    }

    @Override
    public void toolSelected() {
        //toolBar.showColorPicker(true);
    }

    @Override
    public void toolDeSelected() {
        mouseDown = false;
        currentLine = null;
        toolBar.showColorPicker(false);
    }

    @Override
    public void update() {
        // if the mouse is pressed down
        if (mouseDown){
            // get the mouse position
            PointerInfo a = MouseInfo.getPointerInfo();
            Point mousePoint = new Point(a.getLocation().x - drawingCanvas.getLocationOnScreen().x,
                    a.getLocation().y - drawingCanvas.getLocationOnScreen().y);

            // if there is a current line that we are working with
            if (currentLine != null){

                // if the new point is an extension of the line
                if (currentLine.isPointCollinear(mousePoint)){
                    // extend the line to include the point
                    currentLine.changeEndPoint(mousePoint);
                    //System.out.println("The Line changed!");
                    //render the line for the current frame
                    graphicsObjectTracker.addCurrentFrameObject(currentLine);
                } else { // if it isn't a continuation of the line
                    // add the old line and create a new one
                    graphicsObjectTracker.addGraphicsObject(currentLine);

                    // create a new line that extends from the last point
                    currentLine = newLineFromLastPoint(mousePoint);

                    // render the new line for the current frame
                    graphicsObjectTracker.addCurrentFrameObject(currentLine);
                }
            } else { // or if we are creating a new line
                // create a new line
                currentLine = newLineFromPoint(mousePoint);
                graphicsObjectTracker.addCurrentFrameObject(currentLine);
            }


        } else if (currentLine != null){  // or if the mouse was released and we had a line we were working with
            // add the last line as a permanent line
            graphicsObjectTracker.addGraphicsObject(currentLine);
            currentLine = null;
        }
    }

    @Override
    public ToolToolBar getToolbar() {
        return toolBar;
    }

    private Line newLineFromLastPoint(Point mousePoint) {
        return new Line(currentLine.getLastPoint(), mousePoint, currentColor, currentStroke);
    }

    private Line newLineFromPoint(Point pointToPass) {
        return new Line(pointToPass, pointToPass, currentColor, currentStroke);
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
        //update();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public int getCurrentStroke() {
        return currentStroke;
    }
}
