package application.client;

import java.io.IOException;
import java.util.LinkedList;
import java.util.UUID;

import networking.NetworkSyncable;
import networking.client.ConnectionToServerManager;
import networking.sendableObjects.NS_ClientInformation;
import launcher.ConsoleBox;

public class ClientManager implements ConsoleBox{
/*
 * This class is the essence of the client. Everything that the client uses goes here.
 * The ClientManager will connect with a communicator, a console, and eventually a GUI.
 */
	private String host;
	private int port;
	private ConsoleBox outputConsole;
	private String userName;
	private ConnectionToServerManager communications;
	private LinkedList<String> toOutput;
	
	public ClientManager(String host, int port, ConsoleBox clientConsole, String userName) {
		this.host = host;
		this.port = port;
		setUserName(userName);
		setOutputConsole(clientConsole);
		communications = new ConnectionToServerManager(host, port, this);
		outputConsole.consolePrintLine("Client Manager Created");
		try {
			communications.sendObjectToServer(new NS_ClientInformation(userName));
			outputConsole.consolePrintLine("Test Object Sent");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ClientManager(String host, int port) {
		this.host = host;
		this.port = port;
	}
	@Override
	public void consolePrintError(String txtErrorIn) {
		if (outputConsole != null) {
			outputConsole.consolePrintError(txtErrorIn);
		} else {
			
		}
	}
	@Override
	public void consolePrintLine(String txtLineIn) {
		if (outputConsole != null) {
			outputConsole.consolePrintLine(txtLineIn);
		} else {
			
		}
	}
	public void setUserName(String clientName) {
		this.userName = clientName;
		
	}
	public void setOutputConsole(ConsoleBox consoleIn){
		this.outputConsole = consoleIn;
		outputConsole.consolePrintLine("Output console Added to Client Manager");
	}
	@Override
	public void addConsolebox(ConsoleBox outputConsole) {
		
		
	}

}
