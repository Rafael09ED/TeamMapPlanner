package version2.mapDrawer.tools;

import testingTools.printEverySec;
import version2.mapDrawer.DrawingCanvas;
import version2.mapDrawer.GraphicsObjectTracker;
import version2.mapDrawer.tools.toolBars.ToolToolBar;
import version2.mapDrawer.util.MouseInput;

public abstract class MapDrawerTool implements MouseInput{
    protected GraphicsObjectTracker graphicsObjectTracker;
    protected printEverySec printer;
    protected DrawingCanvas drawingCanvas;
	
	public MapDrawerTool(GraphicsObjectTracker graphicsObjectTracker, DrawingCanvas drawingCanvas ){

        this.graphicsObjectTracker = graphicsObjectTracker;
        this.drawingCanvas = drawingCanvas;

        printEverySec.setGlobalPrintStatus(false);
    }

    public abstract String getToolString();
    public abstract String getToolDisplayName();
	public abstract void toolSelected();
	public abstract void toolDeSelected();
	public abstract void update();
    public abstract ToolToolBar getToolbar();
}
