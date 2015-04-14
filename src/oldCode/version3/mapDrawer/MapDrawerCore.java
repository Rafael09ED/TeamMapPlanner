package oldCode.version3.mapDrawer;

import oldCode.version3.mapDrawer.GUI.*;
import oldCode.version3.mapDrawer.canvasItemTracking.CanvasItemTracker;
import oldCode.version3.mapDrawer.rendering.Graphics2D.MapDrawerRenderer;
import oldCode.version3.mapDrawer.settings.InitializeSettings;
import oldCode.version3.mapDrawer.settings.RENDER_SETTINGS;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rafael on 4/2/2015.
 */
public class MapDrawerCore {
    public static final String GUI_NAME = "Team Map Planner";

    public static void main(String[] args) {
        new MapDrawerCore();
    }

    private CanvasItemTracker itemTracker;
    private GUICanvasItemInterface itemGUIInterface;
    private MapDrawerRenderer mapDrawerRenderer;
    private MapDrawerGUI mapDrawerGUI;
    private ScheduledExecutorService mainSchedulingLoop;
    private InitializeSettings settingsSetter;

    public MapDrawerCore() {

        // sets the settings for the program
        // currently does nothing
        settingsSetter = new InitializeSettings();
        //settingsSetter.setFilePath();
        settingsSetter.importAndValidate();


        itemTracker = new CanvasItemTracker();
        mapDrawerRenderer = new MapDrawerRenderer(itemTracker);
        itemGUIInterface = new GUICanvasItemInterface(itemTracker, mapDrawerRenderer);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                synchronized (itemTracker) {
                    mapDrawerGUI = new MapDrawerGUI(GUI_NAME, itemGUIInterface);
                    itemTracker.notify();
                }
            }
        });
        // waits until the map drawer is created.
        // I have no Idea if this is a good idea or not
        synchronized (itemTracker){
            try {
                if (mapDrawerGUI == null)
                    itemTracker.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // creates the main logic loop.
        // updates anything that needs to be updated, and then renders.
        Runnable mainLoop = new Runnable() {
            @Override
            public void run() {
                update();
                render();
            }
        };


        mainSchedulingLoop = Executors.newSingleThreadScheduledExecutor();

        { // to delete init vars after use
            double updatesPerSecond = RENDER_SETTINGS.pointUpdatesPerSecond;
            int initDelay = RENDER_SETTINGS.initDelayTime_ms;

            if (updatesPerSecond > 0) {
                int delay_ms = (int) ((1 * 1000) / updatesPerSecond);
                mainSchedulingLoop.scheduleAtFixedRate(mainLoop, initDelay, delay_ms, TimeUnit.MILLISECONDS);

            } else {
                mainSchedulingLoop.scheduleWithFixedDelay(mainLoop, initDelay, 1, TimeUnit.MILLISECONDS);
            }

        }

    }

    private void render() {
        mapDrawerGUI.render();
    }

    private void update() {
        itemTracker.update();
    }
}
