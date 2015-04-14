package version3.mapDrawer.rendering.DataTracking;

import version3.mapDrawer.canvasItemTracking.canvasGroups.CanvasGroup;
import version3.mapDrawer.rendering.Graphics2D.Graphics2DRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Rafael on 4/10/2015.
 */
public class CGGraphicsData {
    private BufferedImage bufferedImage;
    private CanvasGroup canvasGroup;
    boolean canvasChanged;

    public CGGraphicsData(CanvasGroup canvasGroup){
        this.canvasGroup = canvasGroup;
        bufferedImage = new BufferedImage(10,10,BufferedImage.TYPE_INT_ARGB);
    }

    public BufferedImage render() {
        if (canvasChanged) {
            Graphics2DRenderer renderingInterface = new Graphics2DRenderer();
            BufferedImage image = new BufferedImage((int) canvasGroup.getBoundingBox().getBottomRight().getX() + 1, (int) canvasGroup.getBoundingBox().getBottomRight().getY() + 1, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
            renderingInterface.setGraphicsToRenderTo(g);
            canvasGroup.render(renderingInterface);
            bufferedImage = image;
        }
        return bufferedImage;
    }
}
