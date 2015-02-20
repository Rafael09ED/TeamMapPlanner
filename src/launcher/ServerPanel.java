package launcher;

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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenuItem;


public class ServerPanel extends JPanel implements ConsoleBox{
	
	private static final long serialVersionUID = -2630398121425039827L;
	private JTextArea ServerConsoleTextArea;
	
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

	@Override
	public void consolePrintError(String txtErrorIn) {
		ServerConsoleTextArea.append("ERROR:\t" + txtErrorIn + "\n");
		
	}

	@Override
	public void consolePrintLine(String txtLineIn) {
		ServerConsoleTextArea.append(txtLineIn + "\n");		
	}

}
