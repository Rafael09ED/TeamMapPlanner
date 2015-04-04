package version3.mapDrawer;

import version3.mapDrawer.GUI.*;
import version3.mapDrawer.canvasItemTracking.CanvasItemTracker;
import version3.mapDrawer.rendering.MapDrawerRenderer;
import version3.mapDrawer.settings.InitializeSettings;
import version3.mapDrawer.settings.RENDER_SETTINGS;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by ADMIN on 4/2/2015.
 */
public class MapDrawerCore {
    public static final String GUI_NAME = "Team Map Planner";

    public static void main(String[] args) {
        new MapDrawerCore();
    }

    /*
     * The main layout:
     * http://i.imgur.com/dKA9b7Q.jpg
     */

    private CanvasItemTracker itemTracker;
    private GUICanvasItemInterface itemGUIInterface;
    private MapDrawerRenderer mapDrawerRenderer;
    private MapDrawerGUI mapDrawerGUI;
    private ScheduledExecutorService mainSchedulingLoop;
    private InitializeSettings settingsSetter;

    public MapDrawerCore() {
        itemTracker = new CanvasItemTracker();

        itemGUIInterface = new GUICanvasItemInterface(itemTracker);
        mapDrawerRenderer = new MapDrawerRenderer(itemTracker);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                synchronized (itemTracker) {
                    mapDrawerGUI = new MapDrawerGUI(GUI_NAME, mapDrawerRenderer, itemGUIInterface);
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
        // sets the settings for the program
        settingsSetter = new InitializeSettings();
        //settingsSetter.setFilePath();
        settingsSetter.importAndValidate();


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
                mainSchedulingLoop.scheduleWithFixedDelay(mainLoop, initDelay, 0, TimeUnit.MILLISECONDS);
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
