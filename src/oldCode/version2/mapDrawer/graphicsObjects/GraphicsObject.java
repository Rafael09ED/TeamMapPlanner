package oldCode.version2.mapDrawer.graphicsObjects;

import java.awt.*;

public abstract class GraphicsObject {

	public GraphicsObject() {

	}
	
	public abstract void paint(Graphics g);
	public abstract Point getMaxPoint();

}
