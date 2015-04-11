package version2.mapDrawer;

import version2.mapDrawer.rendering.GraphicsObjectTracker;
import version2.mapDrawer.tools.MapDrawerTool;
import version2.mapDrawer.tools.ToolsManager;
import version2.mapDrawer.graphicsObjects.layers.LayersToolbar;
import version2.mapDrawer.util.onlyOneJToggleDown;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class MapDrawerGUI extends JFrame implements KeyListener {
	private JPanel contentPane;
	private DrawingCanvas drawingCanvas;

	private List<Integer> activeKeys;
	private List<JToggleButton> toolToggleButtons;

	private JToolBar toolToolBar;
	private onlyOneJToggleDown selectOneTool;
	private ToolsManager toolsManager;
    private GraphicsObjectTracker graphicsObjectTracker;

	private LayersToolbar layerToolBar;

    private ActionListener toolButtonListener;

    // TODO: java global var for checking and calling vars
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Main method generated by eclipse
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// set the system's Look and Feel
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
					MapDrawerGUI frame = new MapDrawerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MapDrawerGUI() {

		// initialize arrays & other things

		activeKeys = new ArrayList<Integer>();
		toolToggleButtons = new ArrayList<JToggleButton>();

        // creates the graphics object tracker
        graphicsObjectTracker = new GraphicsObjectTracker();
        // creates the Drawing Canvas
        drawingCanvas = new DrawingCanvas(graphicsObjectTracker, this);
        // creates the tool manager
		toolsManager = new ToolsManager(graphicsObjectTracker,drawingCanvas);
		drawingCanvas.setToolsManager(toolsManager);
		// GUI built with Eclipse's window builder

		setTitle("Map Planner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		addKeyListener(this);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// Adds the drawing canvas

		contentPane.add(drawingCanvas);
        drawingCanvas.addMouseInput(toolsManager);
		setVisible(true);

		while (! isDisplayable() ){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Create Action Listener for JavaButtons
		toolButtonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof JToggleButton) {
					selectOneTool.setSelectedTool((JToggleButton) e.getSource());
                    toolsManager.setTool(e.getActionCommand());
                }
			}
		};

		// Creates Java Toolbar

		toolToolBar = new JToolBar();
		toolToolBar.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(toolToolBar, BorderLayout.WEST);

		layerToolBar = new LayersToolbar(drawingCanvas);
		contentPane.add(layerToolBar, BorderLayout.EAST);
		layerToolBar.setVisible(true);



		// creates Tools

        toolsManager.createTools();
        newToolButton(toolsManager.getTool(0));

        // tool makes it so only one tool is selected at a time
        selectOneTool = new onlyOneJToggleDown(toolToggleButtons.get(0));

        // adds rest of tools
        for (int i = 1; toolsManager.getTool(i) != null; i++) {
             newToolButton(toolsManager.getTool(i));
        }
    }

	private void newToolButton(MapDrawerTool tool) {
		JToggleButton tempTgglButton = new JToggleButton(tool.getToolDisplayName());
        tempTgglButton.setActionCommand(tool.getToolString());
        tempTgglButton.addActionListener(toolButtonListener);
        tempTgglButton.setFocusable(false);
        toolToolBar.add(tempTgglButton);
		toolToggleButtons.add(tempTgglButton);
	}

	private void checkPressedKeys() {

		// Handles KeyPresses
		for (Integer key : activeKeys) {
			switch (key) {
			case KeyEvent.VK_SPACE:

				break;

			default:
				break;
			}
		}
	}

	// Methods for Key Listener
	@Override
	public void keyPressed(KeyEvent e) {
		if (activeKeys.indexOf(e.getKeyCode()) == -1) {
			activeKeys.add(e.getKeyCode());
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		activeKeys.remove((Integer) e.getKeyCode());

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// activeKeys.remove((Object) e.getKeyCode());
		// TODO: Change this for lower fps
	}

}
