package version2.mapDrawer.tools;

import version2.mapDrawer.GraphicsObjectTracker;
import version2.mapDrawer.util.MouseEventPasser;

import java.util.ArrayList;
import java.util.List;

public class ToolsManager {
	
	private MapDrawerTool activeTool;
	private List<MapDrawerTool> mapDrawerTools;
    private MouseEventPasser mouseEventPasser;
    private GraphicsObjectTracker graphicsObjectTracker;

    public ToolsManager(GraphicsObjectTracker graphicsObjectTracker) {
        this.graphicsObjectTracker = graphicsObjectTracker;

        mapDrawerTools = new ArrayList<MapDrawerTool>();

        //Mouse tool is always created, and is always created first
		activeTool = new Mouse(graphicsObjectTracker);
        mouseEventPasser = new MouseEventPasser(activeTool);
		mapDrawerTools.add(activeTool);	
	}
    public MouseEventPasser getMouseEventPasser(){
        return mouseEventPasser;
    }

    public void createTools() {
        mapDrawerTools.add(new Pen(graphicsObjectTracker));
    }

    public MapDrawerTool getTool(int index){
        if ( !(mapDrawerTools.size() > index)){
            return null;
        }
        return mapDrawerTools.get(index);
    }

    public MapDrawerTool getActiveTool(){
        return activeTool;
    }

    public void setTool(String actionCommand) {
        for (MapDrawerTool mapDrawerTool : mapDrawerTools) {
            if (mapDrawerTool.getToolString().equals(actionCommand)){
                // tell old tool it was deselected
                activeTool.toolDeSelected();

                //set new active tool
                activeTool = mapDrawerTool;
                mouseEventPasser.setToolToSendTo(activeTool);
                activeTool.toolSelected();
                return;
            }
        }
    }

    private enum ToolsEnum {
		Mouse(1, "Mouse"), Pen(2,"Pen"), Eraser(3,"Eraser"), Line(4,"Line"), Graphic(5,"Graphics");
		
	    private int toolAsInt;
	    private String displayName;
	    
	    private ToolsEnum(int toolAsInt, String displayName) {
	            this.toolAsInt = toolAsInt;
	            this.displayName = displayName;
	    }
	    public String displayName(){
	    	return displayName;
	    }
        public ToolsEnum parseString(String enumToParse){
            switch (enumToParse) {
                case "Mouse":
                    return ToolsEnum.Mouse;
                case "Pen":
                    return ToolsEnum.Pen;
            }
        return null;


        }
	}
}
