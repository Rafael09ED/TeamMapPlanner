package oldCode.version4.mapDrawer.rendering;

import oldCode.version4.mapDrawer.gui.ItemTrackerInterface;
import oldCode.version4.mapDrawer.itemTracker.CanvasItemTracker;
import oldCode.version4.mapDrawer.itemTracker.canvasGroups.CanvasGroup;
import oldCode.version4.mapDrawer.itemTracker.canvasGroups.CanvasGroupLayer;
import oldCode.version4.mapDrawer.itemTracker.canvasItems.CanvasItem;
import oldCode.version4.mapDrawer.itemTracker.canvasItems.informationStorage.BoundingBox2D;
import oldCode.version4.mapDrawer.rendering.tools.CanvasGroupGraphicsData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafael on 4/14/2015.
 */
public class CanvasItemRenderer {
    private CanvasItemTracker canvasItemTracker;
    private ItemTrackerInterface itemTrackerInterface;
    private Graphics2DRenderer renderingInterface;

    private List<CanvasGroupLayer> allLayersOrdered;
    private HashMap<CanvasGroup, CanvasGroupGraphicsData> layerToDataHM;

    public CanvasItemRenderer(CanvasItemTracker canvasItemTracker, ItemTrackerInterface itemTrackerInterface) {
        // Stores the params
        this.canvasItemTracker = canvasItemTracker;
        this.itemTrackerInterface = itemTrackerInterface;

        // Creates objects
        allLayersOrdered = new ArrayList<>();
        layerToDataHM = new HashMap<>();
        renderingInterface = new Graphics2DRenderer();
    }

    public void setGraphics2d(Graphics2D graphics2d) {
        // passes the graphics to the renderer
        // TODO: Have the graphics passed to renderAll
        renderingInterface.setGraphicsToRenderTo(graphics2d);
    }

    /**
     *  Used to render every canvas layer
     */
    private void rebuildLayerToDataHM() {
        layerToDataHM = new HashMap<>();
        List<CanvasGroupLayer> canvasGroupLayers = new ArrayList<>();
        canvasItemTracker.getAllSubLayersOrdered(canvasGroupLayers);

        for (int i = 0; i < canvasGroupLayers.size(); i++) {

            CanvasGroupLayer canvasGroupLayer = canvasGroupLayers.get(i);
            CanvasGroupGraphicsData graphicsData = createRenderedGraphicsDataFromLayer(canvasGroupLayer);
            layerToDataHM.put(canvasGroupLayer, graphicsData);
        }
    }

    /**
     * Is used to render a graphicsGroup
     * @param layer The CanvasGroupLayer that a CanvasGroupGraphicsData will created from
     * @return A CanvasGroupGraphicsData object that has the rendered image of the layer.
     */
    private static CanvasGroupGraphicsData createRenderedGraphicsDataFromLayer(CanvasGroupLayer layer) {
        // TODO: Implement updating the values instead of creating a new object
        CanvasGroupGraphicsData graphicsData = new CanvasGroupGraphicsData();

        // TODO: Implement a BoundingBox grabber for the GraphicsData Object so it is a direct reference
        BoundingBox2D boundingBox = layer.getBoundingBox();

        BufferedImage image = new BufferedImage(boundingBox.getIntBoxWidth(), boundingBox.getIntBoxHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2DRenderer renderer = new Graphics2DRenderer((Graphics2D) image.getGraphics(), boundingBox.getOffsetAsInt());
        graphicsData.setCanvasOffset(boundingBox.getOffsetAsInt());

        {
            List<CanvasItem> canvasItems = layer.getCanvasItems();
            for (int i = 0; i < canvasItems.size(); i++) {
                // For each canvas item

                canvasItems.get(i).render(renderer);
                // Have the canvasItem tell the renderer what to render.
            }
        }

        // Set the render to the graphicsData
        graphicsData.setRender(image);

        return graphicsData;
    }

    /**
     *  Renders all objects in the CanvasItemTracker
     *  First it checks for any layer order changes
     *  Second it checks for any changes to any layers and renders the canvasLayer
     *  Third it renders all the canvasLayers to the graphics that was passed to the object before
     */

    public void renderAll() {
        if (itemTrackerInterface.hasLayerOrderChanged(true)) {

            // Get the new render order.
            allLayersOrdered = new ArrayList<>();
            canvasItemTracker.getAllSubLayersOrdered(allLayersOrdered);
        }

        {
            Set<CanvasGroupLayer> setOfChangedLayers = itemTrackerInterface.getSetOfChangedLayers(true);

            for (CanvasGroupLayer changedLayer : setOfChangedLayers) {
                // Re-render the layers
                //TODO: change to a taskQueue system in order to optimize adding new things.
                layerToDataHM.put(changedLayer, createRenderedGraphicsDataFromLayer(changedLayer));
            }
        }

        for (int i = 0; i < allLayersOrdered.size(); i++) {
            //For each layer render the graphics data to the canvas buffer
            CanvasGroupLayer canvasGroupLayer = allLayersOrdered.get(i);
            CanvasGroupGraphicsData gData = layerToDataHM.get(canvasGroupLayer);

            //Render to the canvas.
            renderingInterface.drawImage(gData.getCanvasOffset(), gData.getRender(), 1, 1);
        }
    }
}
