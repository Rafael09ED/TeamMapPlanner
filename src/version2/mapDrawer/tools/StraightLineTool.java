package version2.mapDrawer.tools;

import version2.mapDrawer.DrawingCanvas;
import version2.mapDrawer.GraphicsObjectTracker;
import version2.mapDrawer.graphicsObjects.Line;
import version2.mapDrawer.tools.interfaces.StrokeAndColor;
import version2.mapDrawer.tools.toolBars.ToolToolBar;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Rafael on 3/25/2015.
 */
public class StraightLineTool extends MapDrawerTool implements StrokeAndColor {
    private static final String TOOL_NAME_STRING= "StraightLine";
    private Line currentLine;
    private int currentStroke;
    private Color currentColor;

    public StraightLineTool(GraphicsObjectTracker graphicsObjectTracker, DrawingCanvas drawingCanvas) {
        super(graphicsObjectTracker, drawingCanvas);
        //TODO: Start Coding Class
        currentStroke = 1;
        currentColor = Color.BLACK;
    }

    @Override
    public String getToolString() {
        return TOOL_NAME_STRING;
    }

    @Override
    public String getToolDisplayName() {
        return "Line";
    }

    @Override
    public void toolSelected() {

    }

    @Override
    public void toolDeSelected() {
        currentLine = null;
    }

    @Override
    public void update() {
        if (currentLine != null) {
            currentLine.changeEndPoint(getMousePoint());
            graphicsObjectTracker.addCurrentFrameObject(currentLine);
        }
    }

    @Override
    public ToolToolBar getToolbar() {
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point mousePoint = getMousePoint();
        currentLine = new Line(mousePoint,mousePoint);
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
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void setCurrentColor(Color selectedColor) {
        currentColor = selectedColor;
    }

    @Override
    public void setCurrentStroke(int value) {
        currentStroke = value;
    }

    @Override
    public Color getCurrentColor() {
        return currentColor;
    }

    @Override
    public int getCurrentStroke() {
        return currentStroke;
    }
}
