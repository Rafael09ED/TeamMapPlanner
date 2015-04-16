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
        this.canvasItemTracker = canvasItemTracker;
        this.itemTrackerInterface = itemTrackerInterface;

        preArrangedLayers = new ArrayList<>();
        layerToDataHM = new HashMap<>();
        renderingInterface = new Graphics2DRenderer();
    }

    public void setGraphics2d(Graphics2D graphics2d) {
       renderingInterface.setGraphicsToRenderTo(graphics2d);
    }

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

    private static CanvasGroupGraphicsData redoDataObject(CanvasGroupLayer layer) {
        CanvasGroupGraphicsData graphicsData = new CanvasGroupGraphicsData();

        BoundingBox2D boundingBox = layer.getBoundingBox();
        BufferedImage image = new BufferedImage(boundingBox.getIntBoxWidth(), boundingBox.getIntBoxHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2DRenderer renderer = new Graphics2DRenderer();
        renderer.setGraphicsToRenderTo((Graphics2D) image.getGraphics());
        renderer.setOffset(boundingBox.getOffsetAsInt());
        graphicsData.setCanvasOffset(boundingBox.getOffsetAsInt());
       // image.getGraphics().setColor(Color.BLACK);
       // image.getGraphics().fillRect(0,0,boundingBox.getIntBoxWidth(),boundingBox.getIntBoxHeight());

        List<CanvasItem> canvasItems = layer.getCanvasItems();
        for (int i = 0; i < canvasItems.size(); i++) {
            CanvasItem canvasItem = canvasItems.get(i);
            canvasItem.render(renderer);
        }

        graphicsData.setRender(image);
        return graphicsData;
    }

    public void renderAll() {
        if (itemTrackerInterface.hasChanged(true)) {
            preArrangedLayers = new ArrayList<>();
            canvasItemTracker.getAllSubLayers(preArrangedLayers);
        }
        Set<CanvasGroupLayer> listOfChangedLayers = itemTrackerInterface.changedSet(true);
        for (CanvasGroupLayer changedLayer : listOfChangedLayers) {
            layerToDataHM.put(changedLayer, redoDataObject(changedLayer));
        }

        for (int i = 0; i < preArrangedLayers.size(); i++) {
            CanvasGroupLayer canvasGroupLayer = preArrangedLayers.get(i);
            CanvasGroupGraphicsData gData = layerToDataHM.get(canvasGroupLayer);
            renderingInterface.drawImage( gData.getCanvasOffset(), gData.getRender(), 1, 1);
        }
    }
}
