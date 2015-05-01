package version5.mapDrawer.rendering;

import version5.mapDrawer.itemManagement.infoTypes.BoundingBox2D;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupFolder;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;
import version5.mapDrawer.itemManagement.itemTracker.canvasItems.CanvasItem;
import version5.mapDrawer.rendering.optimization.CanvasGroupRenderOptimizer;
import version5.mapDrawer.rendering.optimization.RenderData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafael on 4/24/2015.
 */
public class RenderingWrapper {

    private final CanvasGroupRenderOptimizer canvasGroupRenderOptimizer;
    private Set<CanvasGroupLayer> changedLayers;

    public RenderingWrapper() {
        canvasGroupRenderOptimizer = new CanvasGroupRenderOptimizer(this);
    }

    public RenderData getRender(CanvasGroupFolder canvasGroupFolder) {
        if (true) {
            List<CanvasGroupLayer> canvasGroupLayers = new ArrayList<>();
            canvasGroupFolder.getAllSubLayersOrdered(canvasGroupLayers);

            BoundingBox2D boundingBox2D = new BoundingBox2D();
            for (CanvasGroupLayer canvasGroupLayer : canvasGroupLayers) {
                boundingBox2D.addBoundingBox(canvasGroupLayer.getBoundingBox());
            }

            BufferedImage bufferedImage = new BufferedImage(
                    boundingBox2D.getIntBoxWidth(),
                    boundingBox2D.getIntBoxHeight(),
                    BufferedImage.TYPE_INT_ARGB
            );
            for (CanvasGroupLayer canvasGroupLayer : canvasGroupLayers) {


                Graphics2DRenderer graphics2DRenderer = new Graphics2DRenderer();



                graphics2DRenderer.setGraphicsToRenderTo((Graphics2D) bufferedImage.getGraphics());

                for (CanvasItem canvasItem : canvasGroupLayer.getCanvasItems()) {
                    canvasItem.render(graphics2DRenderer);
                }
            }
            return new RenderData(bufferedImage,boundingBox2D);
        }
        return canvasGroupRenderOptimizer.getRender(canvasGroupFolder);
    }

    public RenderData getRender(CanvasGroupLayer canvasGroupLayer) {
        return canvasGroupRenderOptimizer.getRender(canvasGroupLayer);
    }

    public void setChangedLayers(Set<CanvasGroupLayer> changedLayers) {
        this.changedLayers = changedLayers;
        canvasGroupRenderOptimizer.setChangedLayers(changedLayers);
    }
}
