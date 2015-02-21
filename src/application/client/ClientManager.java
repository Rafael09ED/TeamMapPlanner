package application.client;

import networking.client.ConnectionToServerManager;
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
	private String clientName;
	private ConnectionToServerManager communications;
	
	public ClientManager(String host, int port, ConsoleBox clientConsole) {
		this.host = host;
		this.port = port;
		setOutputConsole(clientConsole);
		communications = new ConnectionToServerManager(host, port, this);
		outputConsole.consolePrintLine("Client Manager Created");
	}
	public ClientManager(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void consolePrintError(String txtErrorIn) {
		if (outputConsole != null) {
			outputConsole.consolePrintError(txtErrorIn);
		}
	}

	@Override
	public void consolePrintLine(String txtLineIn) {
		if (outputConsole != null) {
			outputConsole.consolePrintLine(txtLineIn);
		}		
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
		
	}
	public void setOutputConsole(ConsoleBox consoleIn){
		this.outputConsole = consoleIn;
		outputConsole.consolePrintLine("Output console Added to Client Manager");
	}

}
