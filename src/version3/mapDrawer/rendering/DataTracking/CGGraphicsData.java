package version3.mapDrawer.rendering.DataTracking;

import version3.mapDrawer.canvasItemTracking.canvasGroups.CanvasGroup;
import version3.mapDrawer.canvasItemTracking.canvasItems.CanvasItem;

import java.awt.image.BufferedImage;

/**
 * Created by Rafael on 4/10/2015.
 */
public class CGGraphicsData {
    private BufferedImage bufferedImage;
    private CanvasGroup CanvasGroup;

    public CGGraphicsData(CanvasGroup CanvasGroup){
        this.CanvasGroup = CanvasGroup;
        bufferedImage = new BufferedImage(10,10,BufferedImage.TYPE_INT_ARGB);
    }

    public BufferedImage render() {





        return bufferedImage;
    }
}
