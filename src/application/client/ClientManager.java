package application.client;

import java.io.IOException;
import java.util.UUID;

import networking.NetworkSyncable;
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
		try {
			communications.sendObjectToServer(new NetworkSyncable() {
				@Override
				public UUID getUniqueID() {
					return UUID.randomUUID();
				}
				@Override
				public String getType() {
					return "InformationOnClient";
				}
				@Override
				public String getAuthor() {
					return "Client";
				}
				@Override
				public String getAction() {
					return "";
				}
			});
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
