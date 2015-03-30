package version2.mapDrawer.tools;

import testingTools.printEverySec;
import version2.mapDrawer.DrawingCanvas;
import version2.mapDrawer.rendering.GraphicsObjectTracker;
import version2.mapDrawer.tools.toolBars.ToolToolBar;

import java.awt.*;
import java.awt.event.MouseListener;

public abstract class MapDrawerTool implements MouseListener {
    protected GraphicsObjectTracker graphicsObjectTracker;
    protected printEverySec printer;
    protected DrawingCanvas drawingCanvas;
	
	public MapDrawerTool(GraphicsObjectTracker graphicsObjectTracker, DrawingCanvas drawingCanvas ){

        this.graphicsObjectTracker = graphicsObjectTracker;
        this.drawingCanvas = drawingCanvas;

        printEverySec.setGlobalPrintStatus(false);
    }
    public Point getMousePoint() {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point mousePoint = new Point(a.getLocation().x - drawingCanvas.getLocationOnScreen().x,
                a.getLocation().y - drawingCanvas.getLocationOnScreen().y);
        return mousePoint;
    }

    public abstract String getToolString();
    public abstract String getToolDisplayName();
	public abstract void toolSelected();
	public abstract void toolDeSelected();
	public abstract void update();
    public abstract ToolToolBar getToolbar();
}
