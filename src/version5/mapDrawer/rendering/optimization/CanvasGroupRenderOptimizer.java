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
        canvasLayerToRenderData = new HashMap<>();
    }
    private RenderData renderLayers(List<CanvasGroupLayer> canvasGroupLayers){
        renderUpdatedRequiredLayers(canvasGroupLayers);
        BoundingBox2D offsetBoundingBox = getBoundingBoxForLayer(canvasGroupLayers);

        if (offsetBoundingBox.getIntBoxWidth()<=1||offsetBoundingBox.getIntBoxHeight()<=1) {
            System.out.println("We Rendered Nothing");
            return new RenderData(new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB),new BoundingBox2D());
        }
        //TODO: BUG: OFFSETS are Doubled causing an incorrect render.
        BufferedImage bufferedImage = new BufferedImage(
                offsetBoundingBox.getIntBoxWidth(),
                offsetBoundingBox.getIntBoxHeight(),
                BufferedImage.TYPE_INT_ARGB);



        for (CanvasGroupLayer canvasGroupLayer : canvasGroupLayers) {
            RenderData renderData = canvasLayerToRenderData.get(canvasGroupLayer);

            bufferedImage.getGraphics().drawImage(
                    renderData.getCurrentRender(),
                    -renderData.getXCanvasOffset()+(int)offsetBoundingBox.getTopLeft().getX(),
                    -renderData.getYCanvasOffset()+(int)offsetBoundingBox.getTopLeft().getY(),
                    null);
            BufferedImage bufferedImage1 = new BufferedImage(renderData.getBoundingBoxOnCanvas().getIntBoxWidth(),renderData.getBoundingBoxOnCanvas().getIntBoxHeight(),BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = (Graphics2D) bufferedImage1.getGraphics();
            graphics2D.setColor(Color.BLACK);
            graphics2D.setComposite(AlphaComposite.SrcOver.derive(0.2f));

            graphics2D.fillRect(0, 0, renderData.getBoundingBoxOnCanvas().getIntBoxWidth(), renderData.getBoundingBoxOnCanvas().getIntBoxHeight());
            //graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
             bufferedImage.getGraphics().drawImage(bufferedImage1, -renderData.getXCanvasOffset()+(int)offsetBoundingBox.getTopLeft().getX(), -renderData.getYCanvasOffset()+(int)offsetBoundingBox.getTopLeft().getY(), null);
        }

        RenderData returnRenderData = new RenderData(bufferedImage,offsetBoundingBox);
        return returnRenderData;
    }

    private void renderUpdatedRequiredLayers(List<CanvasGroupLayer> canvasGroupLayers){
        for (CanvasGroupLayer canvasGroupLayer : canvasGroupLayers) {
            if (changedLayers.contains(canvasGroupLayer)
                    || canvasLayerToRenderData.get(canvasGroupLayer) == null
                    || canvasLayerToRenderData.get(canvasGroupLayer).getCurrentRender() == null){

                Graphics2DRenderer graphics2DRenderer = new Graphics2DRenderer();

                BoundingBox2D canvasLayerBoundingBox = canvasGroupLayer.getBoundingBox();
                BufferedImage bufferedImage = new BufferedImage(
                        canvasLayerBoundingBox.getIntBoxWidth(),
                        canvasLayerBoundingBox.getIntBoxHeight(),
                        BufferedImage.TYPE_INT_ARGB
                );

                graphics2DRenderer.setGraphicsToRenderTo((Graphics2D) bufferedImage.getGraphics());

                for (CanvasItem canvasItem : canvasGroupLayer.getCanvasItems()) {
                    canvasItem.render(graphics2DRenderer);
                }

                canvasLayerToRenderData.put(canvasGroupLayer, new RenderData(bufferedImage, canvasLayerBoundingBox));
                changedLayers.remove(canvasGroupLayer);
            }
        }
    }

    public RenderData getRender(CanvasGroupFolder canvasGroupFolder) {
        List<CanvasGroupLayer> canvasGroupLayers = new ArrayList<>();
        canvasGroupFolder.getAllSubLayersOrdered(canvasGroupLayers);
        return renderLayers(canvasGroupLayers);
    }

    public RenderData getRender(CanvasGroupLayer canvasGroupLayer) {
        List<CanvasGroupLayer> renderList =  new ArrayList<>();
        renderList.add(canvasGroupLayer);
        return renderLayers(renderList);
    }

    private BoundingBox2D getBoundingBoxForLayer(List<CanvasGroupLayer> canvasGroupLayers) {
        BoundingBox2D boundingBox2D = new BoundingBox2D();
        for (CanvasGroupLayer canvasGroupLayer : canvasGroupLayers) {
            boundingBox2D.addBoundingBox(canvasGroupLayer.getBoundingBox());
        }
        return boundingBox2D;
    }

    public void setChangedLayers(Set<CanvasGroupLayer> changedLayers) {
        this.changedLayers = changedLayers;
    }
}
