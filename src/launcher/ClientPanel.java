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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ClientPanel extends JPanel implements ConsoleBox{
	
	private static final long serialVersionUID = -2630398121425039827L;
	private JTextArea clientConsoleTextArea;
	
	public ClientPanel() {
		super();
		setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		add(menuBar, BorderLayout.NORTH);
		
		JMenu mnclientActions = new JMenu("Client Actions");
		menuBar.add(mnclientActions);
		
		JMenuItem mntmChangeSessionName = new JMenuItem("Change User Name");
		mntmChangeSessionName.setEnabled(false);
		mnclientActions.add(mntmChangeSessionName);
		
		JMenuItem mntmDisconnect = new JMenuItem("Disconnect");
		mntmDisconnect.setEnabled(false);
		mnclientActions.add(mntmDisconnect);
		
		JMenu mnClientsConnected = new JMenu("Clients");
		menuBar.add(mnClientsConnected);
		
		JMenu mnClientList = new JMenu("All Connected");
		mnClientList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//UPDATE USE LIST HERE
			}
		});
		mnClientList.setEnabled(false);
		mnClientsConnected.add(mnClientList);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		clientConsoleTextArea = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(clientConsoleTextArea);
		panel.add(scrollPane);
		
		JLabel lblConsole = new JLabel("Console:");
		panel.add(lblConsole, BorderLayout.NORTH);
		
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
