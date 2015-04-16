package version4.mapDrawer.rendering;

import version4.mapDrawer.gui.ItemTrackerInterface;
import version4.mapDrawer.itemTracker.CanvasItemTracker;
import version4.mapDrawer.itemTracker.canvasGroups.CanvasGroup;
import version4.mapDrawer.itemTracker.canvasGroups.CanvasGroupLayer;
import version4.mapDrawer.itemTracker.canvasItems.CanvasItem;
import version4.mapDrawer.itemTracker.canvasItems.informationStorage.BoundingBox2D;
import version4.mapDrawer.rendering.tools.CanvasGroupGraphicsData;

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

    private List<CanvasGroupLayer> preArrangedLayers;
    private HashMap<CanvasGroup, CanvasGroupGraphicsData> layerToDataHM;

    public CanvasItemRenderer(CanvasItemTracker canvasItemTracker, ItemTrackerInterface itemTrackerInterface) {
        // Stores the params
        this.canvasItemTracker = canvasItemTracker;
        this.itemTrackerInterface = itemTrackerInterface;

        // Creates objects
        preArrangedLayers = new ArrayList<>();
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
        canvasItemTracker.getAllSubLayers(canvasGroupLayers);

        for (int i = 0; i < canvasGroupLayers.size(); i++) {

            CanvasGroupLayer canvasGroupLayer = canvasGroupLayers.get(i);
            CanvasGroupGraphicsData graphicsData = redoDataObject(canvasGroupLayer);
            layerToDataHM.put(canvasGroupLayer, graphicsData);
        }
    }

    /**
     * Is used to render a graphicsGroup
     * @param   layer   the CanvasGroupLayer that a CanvasGroupGraphicsData will created from
     * @return          a CanvasGroupGraphicsData object that has a a rendered image of the param.
     */
    private static CanvasGroupGraphicsData redoDataObject(CanvasGroupLayer layer) {
        // Takes a layer, and creates a canvasGroupGraphicsData that stores the data
        // TODO: Implement updating the values instead of creating a new object
        CanvasGroupGraphicsData graphicsData = new CanvasGroupGraphicsData();

        // Gets the canvasLayer's bounding box and creates a new BoundingBox for the graphicsData
        // TODO: Implement a BoundingBox grabber for the GraphicsData Object so it is a direct reference
        BoundingBox2D boundingBox = layer.getBoundingBox();

        // Creates a new buffered image to render the canvasLayer to
        BufferedImage image = new BufferedImage(boundingBox.getIntBoxWidth(), boundingBox.getIntBoxHeight(), BufferedImage.TYPE_INT_ARGB);

        // Create a temporary renderer for the graphics layer
        Graphics2DRenderer renderer = new Graphics2DRenderer();
        renderer.setGraphicsToRenderTo((Graphics2D) image.getGraphics());
        renderer.setOffset(boundingBox.getOffsetAsInt());
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
            // If the order of the layers have changed

            // Get the new render order.
            preArrangedLayers = new ArrayList<>();
            canvasItemTracker.getAllSubLayers(preArrangedLayers);
        }

        {
            // Get set of the layers that have changed and need to be re-rendered;
            // A set is used in case a layer is modified several times, so it will only iterate once.
            Set<CanvasGroupLayer> listOfChangedLayers = itemTrackerInterface.changedSet(true);

            for (CanvasGroupLayer changedLayer : listOfChangedLayers) {
                // Re-render the layers
                //TODO: change to a taskQueue system in order to optimize adding new things.
                layerToDataHM.put(changedLayer, redoDataObject(changedLayer));
            }
        }

        for (int i = 0; i < preArrangedLayers.size(); i++) {
            //For each layer render the graphics data to the canvas buffer
            CanvasGroupLayer canvasGroupLayer = preArrangedLayers.get(i);
            CanvasGroupGraphicsData gData = layerToDataHM.get(canvasGroupLayer);

            //Render to the canvas.
            renderingInterface.drawImage(gData.getCanvasOffset(), gData.getRender(), 1, 1);
        }
    }
}
