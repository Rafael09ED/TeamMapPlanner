package version2.mapDrawer;

import version2.mapDrawer.tools.ToolsManager;
import version2.mapDrawer.tools.toolBars.ToolToolBar;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class DrawingCanvas extends Canvas {
	private static final long serialVersionUID = -2889984729709356980L;
    private static final int BUFFER_CREATION_DELAY_TIME = 10;
	private BufferStrategy strategy;
	private BufferedImage blankImage = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
	private ToolsManager toolsManager;
    private GraphicsObjectTracker graphicsObjects;
    private MapDrawerGUI mapDrawerGUI;

	public DrawingCanvas(GraphicsObjectTracker graphicsObjectsTracker, MapDrawerGUI mapDrawerGUI) {
		// Initializes Arrays and Objects
        graphicsObjects = graphicsObjectsTracker;
        this.mapDrawerGUI = mapDrawerGUI;

		// creates a blank background to render to
		Graphics2D g2 = blankImage.createGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, blankImage.getWidth(), blankImage.getHeight());
		g2.dispose();
		
		// Sets up Timer for delayed tasks
        Timer timer = new Timer();
        
        // Sets up Buffer Strategy
        timer.schedule(new TimerTask() {
			@Override
			public void run() {
                if (isDisplayable()) {
                    createBufferStrategy(2);
                    strategy = getBufferStrategy();
                }else{
                    try {
                        Thread.sleep(BUFFER_CREATION_DELAY_TIME);
                        run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

			}
		}, BUFFER_CREATION_DELAY_TIME);

        // Sets up rendering loop
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
                render();
            }
        }, BUFFER_CREATION_DELAY_TIME * 2, (long) (1.0 * 1000.0 / 60));

	}

    public void setToolsManager(ToolsManager toolsManager) {
        this.toolsManager = toolsManager;
    }

    public void addMouseInput(ToolsManager toolsManager){
        // Listens to the Mouse and keyboard
        this.addMouseListener(toolsManager.getMouseEventPasser());
        this.addMouseMotionListener(toolsManager.getMouseEventPasser());
    }
	public void render() {
		// gets buffer strategy to render to
		Graphics g  = strategy.getDrawGraphics();
		
		// draws a blank background
		g.drawImage(blankImage, 0, 0, getWidth(), getHeight(), null);

		//draws objects
        graphicsObjects.renderGraphicsObjects(g);

		g.dispose();
		strategy.show();
	};

	public void update() {
        toolsManager.updateCurrentTool();
	}

    public void addToolBar(ToolToolBar toolToolBar) {
        mapDrawerGUI.add(toolToolBar, BorderLayout.NORTH);
        mapDrawerGUI.revalidate();
    }
}
