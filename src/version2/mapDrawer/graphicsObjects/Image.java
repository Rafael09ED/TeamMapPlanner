package version2.mapDrawer.graphicsObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by ADMIN on 3/29/2015.
 */
public class Image extends GraphicsObject {
    private BufferedImage image;
    private Point pos;
    private double scale;
    private float alpha;
    private Point maxPoint;

    public Image(BufferedImage imageIn, Point position, double scale, float alpha) {
        pos = position;
        image = imageIn;
        this.scale = scale;
        this.alpha = alpha;
        maxPoint = new Point((int)(pos.getX() + (image.getWidth() * scale)) + 1, (int) (pos.getY() + (image.getHeight()*scale)) + 1);
    }

    @Override
    public void paint(Graphics g) {
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g.drawImage(image, pos.x, pos.y, (int) (scale * image.getWidth()), (int) (scale * image.getHeight()), null);
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }

    @Override
    public Point getMaxPoint() {
        return maxPoint;
    }
}
