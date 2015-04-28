package version5.mapDrawer.rendering.optimization;

import version5.mapDrawer.itemManagement.infoTypes.BoundingBox2D;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupFolder;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroups.CanvasGroupLayer;
import version5.mapDrawer.itemManagement.itemTracker.canvasItems.CanvasItem;
import version5.mapDrawer.rendering.Graphics2DRenderer;
import version5.mapDrawer.rendering.RenderingWrapper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafael on 4/25/2015.
 */
public class CanvasGroupRenderOptimizer {
    private HashMap<CanvasGroupLayer, RenderData> canvasLayerToRenderData;
    private RenderingWrapper renderingWrapper;
    private Set<CanvasGroupLayer> changedLayers;

    public CanvasGroupRenderOptimizer(RenderingWrapper renderingWrapper) {
        this.renderingWrapper = renderingWrapper;
    }
    public BufferedImage getRender(CanvasGroupFolder canvasGroupFolder) {
        List<CanvasGroupLayer> canvasGroupLayers = new ArrayList<>();
        canvasGroupFolder.getAllSubLayersOrdered(canvasGroupLayers);
        return renderLayers(canvasGroupLayers);
    }

    private BufferedImage renderLayers(List<CanvasGroupLayer> canvasGroupLayers){
        BoundingBox2D offsetBoundingBox = renderRequired(canvasGroupLayers);

        BufferedImage bufferedImage = new BufferedImage(
                offsetBoundingBox.getIntBoxWidth(),
                offsetBoundingBox.getIntBoxHeight(),
                BufferedImage.TYPE_INT_ARGB);

        for (CanvasGroupLayer canvasGroupLayer : canvasGroupLayers) {
            bufferedImage.getGraphics().drawImage(canvasLayerToRenderData.get(canvasGroupLayer).getCurrentRender(), 0, 0, null);
        }

        return bufferedImage;
    }

    private BoundingBox2D renderRequired(List<CanvasGroupLayer> canvasGroupLayers){
        BoundingBox2D canvasLayersListBoundingBox = new BoundingBox2D();

        for (CanvasGroupLayer canvasGroupLayer : canvasGroupLayers) {
            if (changedLayers.contains(canvasGroupLayer)){
                Graphics2DRenderer graphics2DRenderer = new Graphics2DRenderer();

                BoundingBox2D canvasLayerBoundingBox = canvasGroupLayer.getBoundingBox();
                canvasLayersListBoundingBox.addBoundingBox(canvasLayerBoundingBox);

                BufferedImage bufferedImage = new BufferedImage(
                        canvasLayerBoundingBox.getIntBoxWidth(),
                        canvasLayerBoundingBox.getIntBoxHeight(),
                        BufferedImage.TYPE_INT_ARGB
                );

                graphics2DRenderer.setGraphicsToRenderTo((Graphics2D) bufferedImage.getGraphics());

                for (CanvasItem canvasItem : canvasGroupLayer.getCanvasItems()) {
                    canvasItem.render(graphics2DRenderer);
                }

                canvasLayerToRenderData.get(canvasGroupLayer)
                        .setNewRender(bufferedImage, canvasLayerBoundingBox.getTopLeft());

                changedLayers.remove(canvasGroupLayer);
            }
        }
        return canvasLayersListBoundingBox;
    }

    public BufferedImage getRender(CanvasGroupLayer canvasGroupLayer) {
        List<CanvasGroupLayer> renderList =  new ArrayList<>();
        renderList.add(canvasGroupLayer);
        return renderLayers(renderList);
    }

    public void setChangedLayers(Set<CanvasGroupLayer> changedLayers) {
        this.changedLayers = changedLayers;
    }
}
