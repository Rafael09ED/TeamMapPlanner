package GUI;

import application.client.Client;
import utilities.console.ConsoleOutput;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ClientPanel extends JPanel implements ConsoleOutput{
	
	private static final long serialVersionUID = -2630398121425039827L;
	private JTextArea clientConsoleTextArea;
    private Client client;
	
	public ClientPanel() {
		super();
		setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		add(menuBar, BorderLayout.NORTH);
		
		JMenu mnclientActions = new JMenu("client Actions");
		menuBar.add(mnclientActions);
		
		JMenuItem mntmChangeSessionName = new JMenuItem("Change Session Name");
		mntmChangeSessionName.setEnabled(false);
		mnclientActions.add(mntmChangeSessionName);
		
		JMenuItem mntmEndSession = new JMenuItem("End Session");
		mntmEndSession.setEnabled(false);
		mnclientActions.add(mntmEndSession);
		
		JMenu mnClientsConnected = new JMenu("Clients");
		menuBar.add(mnClientsConnected);
		
		JMenu mnClientList = new JMenu("All Connected");
		mnClientList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO: UPDATE USE LIST HERE
			}
		});
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
		
		clientConsoleTextArea = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(clientConsoleTextArea);
		panel.add(scrollPane);
		
		JLabel lblConsole = new JLabel("Console:");
		panel.add(lblConsole, BorderLayout.NORTH);
		
	}

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
	public void consolePrintError(String txtErrorIn) {
		clientConsoleTextArea.append("ERROR:\t" + txtErrorIn + "\n");
		
	}

	@Override
	public void consolePrintLine(String txtLineIn) {
		clientConsoleTextArea.append(txtLineIn + "\n");		
	}


}
