package version2.mapDrawer.tools.interfaces;

import java.awt.*;

/**
 * Created by Rafael on 3/27/2015.
 */
public interface StrokeAndColor {
    public void setCurrentColor(Color selectedColor);
    public void setCurrentStroke(int value);

    public Color getCurrentColor();
    public int getCurrentStroke();
}
