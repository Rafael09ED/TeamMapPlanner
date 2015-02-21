package application.server;

import java.util.ArrayList;

import launcher.ConsoleBox;
import networking.PortListener;
import networking.server.IndividualClientTracker;
import networking.server.NetworkConnectionsManager;

/* This Class is the essence of the server.
 * It will be connected to the console, the NetworkConnectionsManager and the syncing manager.
 * 
 */
public class ServerManager implements ConsoleBox{

	private String sessionName;
	private int port;
	private ArrayList<IndividualClientTracker> connectedClients;
	private ConsoleBox outputConsole;
	private NetworkConnectionsManager networkingManager;
	public ServerManager(int port) {
		this.port = port;
		networkingManager = new NetworkConnectionsManager(port, this);
	}
	
	public ServerManager(int port, ConsoleBox outputConsole){
		this.port = port;
		setServerConsole(outputConsole);
		outputConsole.consolePrintLine("Server Manager Created");
		
	}
	public void setServerConsole(ConsoleBox outputConsole){
		this.outputConsole = outputConsole;
		outputConsole.consolePrintLine("Console Box Added to Server Manager");
	}
	
	public void setSessionName(String sessionName){
		this.sessionName = sessionName;
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
	
}
