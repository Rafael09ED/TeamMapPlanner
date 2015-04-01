package version2.mapDrawer.graphicsObjects.layers;

import version2.mapDrawer.rendering.GraphicsObjectLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by ADMIN on 3/31/2015.
 */
public class LayerGUIElement extends JToggleButton {
    private GraphicsObjectLayer graphicsObjectLayer;
    public LayerGUIElement(GraphicsObjectLayer graphicsObjectLayer) {
        super(graphicsObjectLayer.getName());
        this.graphicsObjectLayer = graphicsObjectLayer;
        setIcon(new ImageIcon(graphicsObjectLayer.getPreRendered()));
        setText(graphicsObjectLayer.getName());
    }

    public int getUniqueLayerID() {
        return graphicsObjectLayer.getUniqueLayerID();
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage img = new BufferedImage(100,50, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g2 = img.createGraphics();
        g2.setColor(Color.WHITE);
        g2.drawRect(0, 0, 100, 50);
        g2.drawImage(graphicsObjectLayer.getPreRendered(), 0, 0, 100, 50, null);
        g2.dispose();
        setIcon(new ImageIcon(img));
        super.paint(g);
    }
}
