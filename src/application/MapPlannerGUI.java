package application;

import application.logic.Line;
import application.logic.Map;
import networking.interfaces.NetworkSendable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MapPlannerGUI extends JFrame {

	private JPanel contentPane;
	protected Map drawingPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MapPlannerGUI frame = new MapPlannerGUI();
					frame.setVisible(true);
					frame.requestFocusInWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MapPlannerGUI() {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmClearEverything = new JMenuItem("Clear Everything");
		mnFile.add(mntmClearEverything);
		
		JMenu mnConnection = new JMenu("Connection");
		menuBar.add(mnConnection);
		
		JMenuItem mntmHost = new JMenuItem("Host");
		mnConnection.add(mntmHost);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmChangeName = new JMenuItem("Change Name");
		mnSettings.add(mntmChangeName);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JProgressBar progressBar = new JProgressBar();
		contentPane.add(progressBar, BorderLayout.SOUTH);
		
		drawingPanel = new Map();
		contentPane.add(drawingPanel, BorderLayout.CENTER);
		drawingPanel.requestFocus();
		
	}

    public ArrayList<Line> getInBox() {
        return drawingPanel.getLineInBox();

    }

    public void setSendable(NetworkSendable sendable){
        drawingPanel.setOutBoxSender(sendable);
    }

    public void startSending(){
        drawingPanel.startOutputBox();
    }


}
