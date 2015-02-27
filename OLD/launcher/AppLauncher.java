package launcher;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.GridLayout;

import javax.swing.JTabbedPane;

import java.awt.FlowLayout;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JEditorPane;

import java.awt.Component;

import javax.swing.Box;

import application.client.ClientManager;
import application.server.ServerManager;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;


public class AppLauncher extends JFrame {

	/** A program that will be like Google Documents with drawing and pictures.
	 * 	After thinking about the program, I realize that it might have been a better Idea to 
	 */
	private static final long serialVersionUID = -8214547356453125098L;
	private JPanel contentPane;
	private JTextField textField;
	private JTabbedPane tabbedPane;
	
	final public String appVersion = "0.0.0.0.2 a";
	final public String[] authors = {"Rafael09ED"};
	private JTextField connectionKeyField;
	private JTextField tf_SessionHost;
	private JFormattedTextField  tf_SessionPort;
	private JFormattedTextField  tf_Port;
	private JTextField tf_SessionUserName;
	private JTextField tf_ServerName;
	private NumberFormatter portTextboxFormat;
	private int serverCounter, ClientCounter = 0;
	
	private ArrayList<ServerManager> serversList;
	private ArrayList<ClientManager> sessionsList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					AppLauncher frame = new AppLauncher();
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
	public AppLauncher() {
		//declare arrays
		sessionsList = new ArrayList<ClientManager>();
		serversList = new ArrayList<ServerManager>();
		
		
		setTitle("Team Map Planner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		String allAuthors = authors[0];
		for (int i = 1; i < authors.length; i++) {
			if (authors.length - 1 == i) {
				allAuthors += " and";
				allAuthors +=  " " + authors[i] ;
			} else {
				allAuthors +=  ", " + authors[i] ;
			}
		}
		String parsedTitle = "<html>" + "<b> Team Map Planner </b>" + "<br> Ver. " + appVersion + "<br> By: " + allAuthors + "." + "</html>";
		JLabel lblTeamMapPlanner = new JLabel(parsedTitle);
		lblTeamMapPlanner.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblTeamMapPlanner, BorderLayout.NORTH);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel landingPanel = new JPanel();
		tabbedPane.addTab("Home", null, landingPanel, null);
		GridBagLayout gbl_landingPanel = new GridBagLayout();
		gbl_landingPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_landingPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_landingPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_landingPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		landingPanel.setLayout(gbl_landingPanel);
		
		Component rigidArea = Box.createRigidArea(new Dimension(0, 0));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea.gridx = 0;
		gbc_rigidArea.gridy = 0;
		landingPanel.add(rigidArea, gbc_rigidArea);
				
		JButton btnHostSession = new JButton("Host a Session");
		btnHostSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		GridBagConstraints gbc_btnHostSession = new GridBagConstraints();
		gbc_btnHostSession.insets = new Insets(0, 0, 5, 5);
		gbc_btnHostSession.gridx = 1;
		gbc_btnHostSession.gridy = 1;
		landingPanel.add(btnHostSession, gbc_btnHostSession);
		
		JButton btnConnectToSession = new JButton("Connect to Session");
		btnConnectToSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		GridBagConstraints gbc_btnConnectToSession = new GridBagConstraints();
		gbc_btnConnectToSession.insets = new Insets(0, 0, 5, 0);
		gbc_btnConnectToSession.gridx = 3;
		gbc_btnConnectToSession.gridy = 1;
		landingPanel.add(btnConnectToSession, gbc_btnConnectToSession);
		
		JLabel lblPasteConnectionKey = new JLabel("Paste Connection Key:");
		GridBagConstraints gbc_lblPasteConnectionKey = new GridBagConstraints();
		gbc_lblPasteConnectionKey.insets = new Insets(0, 0, 5, 0);
		gbc_lblPasteConnectionKey.gridx = 3;
		gbc_lblPasteConnectionKey.gridy = 2;
		landingPanel.add(lblPasteConnectionKey, gbc_lblPasteConnectionKey);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;
		landingPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JPanel hostingPanel = new JPanel();
		tabbedPane.addTab("Hosting a Session", null, hostingPanel, "Detailed options for hosting a session");
		GridBagLayout gbl_hostingPanel = new GridBagLayout();
		gbl_hostingPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_hostingPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_hostingPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_hostingPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		hostingPanel.setLayout(gbl_hostingPanel);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(0,0));
		GridBagConstraints gbc_rigidArea_2 = new GridBagConstraints();
		gbc_rigidArea_2.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_2.gridx = 0;
		gbc_rigidArea_2.gridy = 0;
		hostingPanel.add(rigidArea_2, gbc_rigidArea_2);
		
		JLabel lblHostingIntro = new JLabel("Fill in the necessary information to host a session");
		GridBagConstraints gbc_lblHostingIntro = new GridBagConstraints();
		gbc_lblHostingIntro.gridwidth = 2;
		gbc_lblHostingIntro.insets = new Insets(0, 0, 5, 0);
		gbc_lblHostingIntro.gridx = 1;
		gbc_lblHostingIntro.gridy = 1;
		hostingPanel.add(lblHostingIntro, gbc_lblHostingIntro);
		
		JLabel lblSessionName = new JLabel("Session Name:");
		GridBagConstraints gbc_lblSessionName = new GridBagConstraints();
		gbc_lblSessionName.insets = new Insets(0, 0, 5, 5);
		gbc_lblSessionName.anchor = GridBagConstraints.EAST;
		gbc_lblSessionName.gridx = 1;
		gbc_lblSessionName.gridy = 3;
		hostingPanel.add(lblSessionName, gbc_lblSessionName);
		
		tf_ServerName = new JTextField();
		tf_ServerName.setText("Name");
		GridBagConstraints gbc_tf_ServerName = new GridBagConstraints();
		gbc_tf_ServerName.insets = new Insets(0, 0, 5, 0);
		gbc_tf_ServerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_ServerName.gridx = 2;
		gbc_tf_ServerName.gridy = 3;
		hostingPanel.add(tf_ServerName, gbc_tf_ServerName);
		tf_ServerName.setColumns(10);
		
		JLabel lblPort_1 = new JLabel("Port:");
		GridBagConstraints gbc_lblPort_1 = new GridBagConstraints();
		gbc_lblPort_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort_1.anchor = GridBagConstraints.EAST;
		gbc_lblPort_1.gridx = 1;
		gbc_lblPort_1.gridy = 5;
		hostingPanel.add(lblPort_1, gbc_lblPort_1);
		
		
		//Int only for Ports
		//USED MULTIPLE TIMES 
		NumberFormat format = NumberFormat.getInstance();
		format.setGroupingUsed(false); // NO COMMAS
	    NumberFormatter portTextboxFormat = new NumberFormatter(format);
	    portTextboxFormat.setValueClass(Integer.class);
	    portTextboxFormat.setMinimum(0);
	    portTextboxFormat.setMaximum(Integer.MAX_VALUE);
	    //Value to be committed on each keystroke
	    portTextboxFormat.setCommitsOnValidEdit(true);
	    
	    
	    
		tf_Port = new JFormattedTextField(portTextboxFormat);
		tf_Port.setText("22102");
		GridBagConstraints gbc_tf_Port = new GridBagConstraints();
		gbc_tf_Port.insets = new Insets(0, 0, 5, 0);
		gbc_tf_Port.anchor = GridBagConstraints.WEST;
		gbc_tf_Port.gridx = 2;
		gbc_tf_Port.gridy = 5;
		hostingPanel.add(tf_Port, gbc_tf_Port);
		tf_Port.setColumns(10);
		
		JButton btnHost = new JButton("Host");
		btnHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Call to create the server
				ConsoleBox serverConsole = createServerTab(tf_ServerName.getText());
				ServerManager server = createServer(serverConsole, (int) tf_Port.getValue());
				server.setSessionName(tf_ServerName.getText());
				
				//Not needed since console created before server : server.setServerConsole(serverConsole);
			}
		});
		GridBagConstraints gbc_btnHost = new GridBagConstraints();
		gbc_btnHost.insets = new Insets(0, 0, 0, 5);
		gbc_btnHost.gridx = 1;
		gbc_btnHost.gridy = 13;
		hostingPanel.add(btnHost, gbc_btnHost);
		
		JPanel connectingPanel = new JPanel();
		tabbedPane.addTab("Connecting to Session", null, connectingPanel, null);
		GridBagLayout gbl_connectingPanel = new GridBagLayout();
		gbl_connectingPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_connectingPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_connectingPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_connectingPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		connectingPanel.setLayout(gbl_connectingPanel);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(0, 0));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_1.gridx = 0;
		gbc_rigidArea_1.gridy = 0;
		connectingPanel.add(rigidArea_1, gbc_rigidArea_1);
		
		JLabel lblConnectingIntro = new JLabel("Fill in the nessessary information to connect to a session");
		GridBagConstraints gbc_lblConnectingIntro = new GridBagConstraints();
		gbc_lblConnectingIntro.gridwidth = 2;
		gbc_lblConnectingIntro.insets = new Insets(0, 0, 5, 5);
		gbc_lblConnectingIntro.gridx = 1;
		gbc_lblConnectingIntro.gridy = 1;
		connectingPanel.add(lblConnectingIntro, gbc_lblConnectingIntro);
		
		JLabel lblUserName = new JLabel("User Name:");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.anchor = GridBagConstraints.EAST;
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 1;
		gbc_lblUserName.gridy = 3;
		connectingPanel.add(lblUserName, gbc_lblUserName);
		
		tf_SessionUserName = new JTextField();
		tf_SessionUserName.setText("User");
		GridBagConstraints gbc_tf_SessionUserName = new GridBagConstraints();
		gbc_tf_SessionUserName.insets = new Insets(0, 0, 5, 5);
		gbc_tf_SessionUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_SessionUserName.gridx = 2;
		gbc_tf_SessionUserName.gridy = 3;
		connectingPanel.add(tf_SessionUserName, gbc_tf_SessionUserName);
		tf_SessionUserName.setColumns(10);
		
		JLabel lblConnectionKey = new JLabel("Connection Key:");
		GridBagConstraints gbc_lblConnectionKey = new GridBagConstraints();
		gbc_lblConnectionKey.insets = new Insets(0, 0, 5, 5);
		gbc_lblConnectionKey.anchor = GridBagConstraints.EAST;
		gbc_lblConnectionKey.gridx = 1;
		gbc_lblConnectionKey.gridy = 5;
		connectingPanel.add(lblConnectionKey, gbc_lblConnectionKey);
		
		connectionKeyField = new JTextField();
		GridBagConstraints gbc_connectionKeyField = new GridBagConstraints();
		gbc_connectionKeyField.insets = new Insets(0, 0, 5, 5);
		gbc_connectionKeyField.fill = GridBagConstraints.HORIZONTAL;
		gbc_connectionKeyField.gridx = 2;
		gbc_connectionKeyField.gridy = 5;
		connectingPanel.add(connectionKeyField, gbc_connectionKeyField);
		connectionKeyField.setColumns(10);
		
		JLabel lblHost = new JLabel("Host:");
		GridBagConstraints gbc_lblHost = new GridBagConstraints();
		gbc_lblHost.anchor = GridBagConstraints.EAST;
		gbc_lblHost.insets = new Insets(0, 0, 5, 5);
		gbc_lblHost.gridx = 1;
		gbc_lblHost.gridy = 7;
		connectingPanel.add(lblHost, gbc_lblHost);
		
		tf_SessionHost = new JTextField();
		tf_SessionHost.setText("127.0.0.1");
		GridBagConstraints gbc_tf_SessionHost = new GridBagConstraints();
		gbc_tf_SessionHost.insets = new Insets(0, 0, 5, 5);
		gbc_tf_SessionHost.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_SessionHost.gridx = 2;
		gbc_tf_SessionHost.gridy = 7;
		connectingPanel.add(tf_SessionHost, gbc_tf_SessionHost);
		tf_SessionHost.setColumns(10);
		
		JLabel lblPort = new JLabel("Port:");
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort.anchor = GridBagConstraints.EAST;
		gbc_lblPort.gridx = 1;
		gbc_lblPort.gridy = 8;
		connectingPanel.add(lblPort, gbc_lblPort);
		
		tf_SessionPort = new JFormattedTextField(portTextboxFormat);
		tf_SessionPort.setText("22102");
		tf_SessionPort.setColumns(10);
		GridBagConstraints gbc_tf_SessionPort = new GridBagConstraints();
		gbc_tf_SessionPort.anchor = GridBagConstraints.WEST;
		gbc_tf_SessionPort.insets = new Insets(0, 0, 5, 5);
		gbc_tf_SessionPort.gridx = 2;
		gbc_tf_SessionPort.gridy = 8;
		connectingPanel.add(tf_SessionPort, gbc_tf_SessionPort);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Action for connecting to a session goes here
				
				ConsoleBox clientConsole = joiningSessionTab(tf_SessionUserName.getText());
				ClientManager client = createClient(tf_SessionHost.getText(), (int) tf_SessionPort.getValue(), clientConsole, tf_SessionUserName.getText());
				sessionsList.add(client);
			}
		});
		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.insets = new Insets(0, 0, 0, 5);
		gbc_btnConnect.gridx = 1;
		gbc_btnConnect.gridy = 13;
		connectingPanel.add(btnConnect, gbc_btnConnect);
	}
	private ConsoleBox joiningSessionTab(String ClientName) {
		ServerPanel clientPanelBox = new ServerPanel();
		tabbedPane.addTab("Session #" + ClientCounter, clientPanelBox);
		tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Session #" + ClientCounter));
		ClientCounter++;
		return clientPanelBox;
	}

	private ServerManager createServer(ConsoleBox serverConsole, int port){
		ServerManager server = new ServerManager(port, serverConsole);
		return server;
	}
	private ServerManager createServer(int port){
		ServerManager server = new ServerManager(port);
		return server;
	}
	private ConsoleBox createServerTab(String sessionName){
		//SessionName not used
		ServerPanel serverPanelBox = new ServerPanel();
		tabbedPane.addTab("Server #" + serverCounter, serverPanelBox);
		tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Server #" + serverCounter));
		serverCounter++;
		return serverPanelBox;
	}
	private ClientManager createClient(String host, int port, ConsoleBox clientConsole, String clientName){
		ClientManager client = new ClientManager(host, port, clientConsole, clientName);
		return client;
	}
	

}
