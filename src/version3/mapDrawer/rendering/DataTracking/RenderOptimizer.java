package version3.mapDrawer.rendering.DataTracking;

import version3.mapDrawer.canvasItemTracking.canvasGroups.CanvasGroup;
import version3.mapDrawer.rendering.Graphics2D.MapDrawerRenderer;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by Rafael09ED on 4/11/2015.
 */

public class RenderOptimizer {
    private MapDrawerRenderer mapDrawerRenderer;
    private HashMap<CanvasGroup, CGGraphicsData> layerToDataHM;


    public RenderOptimizer(MapDrawerRenderer mapDrawerRenderer) {
        this.mapDrawerRenderer = mapDrawerRenderer;
        layerToDataHM = new HashMap<>();
    }

    public void setHashMapData(CanvasGroup canvasGroup, CGGraphicsData hashMapData) {
        layerToDataHM.put(canvasGroup,hashMapData);
    }

    CGGraphicsData getGroupData(CanvasGroup canvasGroup){
        return layerToDataHM.get(canvasGroup);
    }

    public BufferedImage render(CanvasGroup canvasGroup){
        CGGraphicsData data = layerToDataHM.get(canvasGroup);
        if (data != null){
            return data.render();
        } else {
            return null;
        }
    }
    //TODO: make useful
}
