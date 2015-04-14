package oldCode.version1.GUI;

import oldCode.version1.application.server.Server;
import oldCode.version1.console.ConsoleOutput;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;


public class ServerPanel extends JPanel implements ConsoleOutput {
	
	private static final long serialVersionUID = -2630398121425039827L;
	private JTextArea ServerConsoleTextArea;
    private Server server;
	
	public ServerPanel() {
		super();
		setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		add(menuBar, BorderLayout.NORTH);
		
		JMenu mnServerActions = new JMenu("Server Actions");
		menuBar.add(mnServerActions);
		
		JMenuItem mntmChangeSessionName = new JMenuItem("Change Session Name");
		mntmChangeSessionName.setEnabled(false);
		mnServerActions.add(mntmChangeSessionName);
		
		JMenuItem mntmEndSession = new JMenuItem("End Session");
		mntmEndSession.setEnabled(false);
		mnServerActions.add(mntmEndSession);
		
		JMenu mnClientsConnected = new JMenu("Clients");
		menuBar.add(mnClientsConnected);
		
		JMenu mnClientList = new JMenu("All Connected");
		mnClientList.setEnabled(false);
		mnClientsConnected.add(mnClientList);
		
		JMenuItem mntmKickAClient = new JMenuItem("Kick a Client");
		mntmKickAClient.setEnabled(false);
		mnClientsConnected.add(mntmKickAClient);
		
		JMenuItem mntmBanAClient = new JMenuItem("Ban a Client");
		mntmBanAClient.setEnabled(false);
		mnClientsConnected.add(mntmBanAClient);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		ServerConsoleTextArea = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(ServerConsoleTextArea);
		panel.add(scrollPane);
		
		JLabel lblConsole = new JLabel("Console:");
		panel.add(lblConsole, BorderLayout.NORTH);
		
	}

    public void setServer(Server server) {
        this.server = server;
    }

    @Override
	public void consolePrintError(String txtErrorIn) {
		ServerConsoleTextArea.append("ERROR:\t" + txtErrorIn + "\n");
	}

    @Override
	public void consolePrintLine(String txtLineIn) {
		ServerConsoleTextArea.append(txtLineIn + "\n");		
	}

}
