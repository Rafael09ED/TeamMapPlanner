package oldCode.version4.mapDrawer;

import oldCode.version4.SETTINGS.GENERAL_SETTINGS;
import oldCode.version4.SETTINGS.RENDER_SETTINGS;
import oldCode.version4.mapDrawer.gui.ItemTrackerInterface;
import oldCode.version4.mapDrawer.gui.MapDrawerGUI;
import oldCode.version4.mapDrawer.itemTracker.CanvasItemTracker;
import oldCode.version4.mapDrawer.itemTracker.canvasGroups.CanvasGroupLayer;
import oldCode.version4.mapDrawer.itemTracker.canvasItems.CanvasItem_Line;
import oldCode.version4.mapDrawer.itemTracker.canvasItems.informationStorage.Point2D;
import oldCode.version4.mapDrawer.rendering.CanvasItemRenderer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rafael on 4/14/2015.
 */
public class MapDrawerCore {
    public static void main(String[] args) {
        new MapDrawerCore();
    }

    //Data Holding Classes
    private MapDrawerGUI mapDrawerGUI;
    private CanvasItemTracker canvasItemTracker;
    private CanvasItemRenderer canvasItemRenderer;
    private ItemTrackerInterface itemTrackerInterface;

    // Utility classes
    private ScheduledExecutorService mainSchedulingLoop;


    public MapDrawerCore() {
        // declare fields and pass construct classes
        declarations();
        waitForGUI();
        TESTING_DoOnce();
        initializeMainLoop();
    }

    private void waitForGUI() {
        synchronized (canvasItemTracker){
            try {
                if (mapDrawerGUI == null)
                    canvasItemTracker.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeMainLoop() {
        Runnable mainLoop = new Runnable() {
            private boolean errored = false;
            @Override
            public void run() {
                try {
                    if (!errored)
                        loop();
                } catch (Exception e){
                    e.printStackTrace();
                    errored = true;
                }
            }

            private void loop(){
                update();
                TESTING_DoEvery();
                render();
            }
        };


        mainSchedulingLoop = Executors.newSingleThreadScheduledExecutor();


        {
            int initDelay = RENDER_SETTINGS.initialDelayTime_ms;

            if (RENDER_SETTINGS.renderAfterFrameCompletion) {
                int frameDelay = RENDER_SETTINGS.delayBetweenFrames_ms;
                mainSchedulingLoop.scheduleWithFixedDelay(mainLoop, initDelay, frameDelay, TimeUnit.MILLISECONDS);

            } else {
                int delay_ms = (int) ((1 * 1000) / RENDER_SETTINGS.UPDATES_PER_SECOND);
                mainSchedulingLoop.scheduleAtFixedRate(mainLoop, initDelay, delay_ms, TimeUnit.MILLISECONDS);

            }
        }

    }

    private void TESTING_DoEvery() {
        List<CanvasGroupLayer> layers = new ArrayList<>();
        canvasItemTracker.getAllSubLayersOrdered(layers);

        if (!(layers.size() > 0)) {
            itemTrackerInterface.newLayer(canvasItemTracker, false);
            canvasItemTracker.getAllSubLayersOrdered(layers);
        }

        itemTrackerInterface.addCanvasItemTo(new CanvasItem_Line(rndPoint(), rndPoint()), layers.get(0));

    }

    private Point2D rndPoint() {
        return new Point2D(Math.random() * 500, Math.random() * 500);
    }

    private void render() {
        mapDrawerGUI.render();
    }

    private void update() {
        mapDrawerGUI.update();
    }

    private void TESTING_DoOnce() {
        itemTrackerInterface.newLayer(null, false);
    }


    private void declarations() {
        canvasItemTracker = new CanvasItemTracker();
        itemTrackerInterface = new ItemTrackerInterface(canvasItemTracker);
        canvasItemRenderer = new CanvasItemRenderer(canvasItemTracker, itemTrackerInterface);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                synchronized (canvasItemTracker) {
                    mapDrawerGUI =
                            new MapDrawerGUI(GENERAL_SETTINGS.PROGRAM_TITLE, itemTrackerInterface, canvasItemRenderer);
                    canvasItemTracker.notify();
                }
            }
        });
    }
}
