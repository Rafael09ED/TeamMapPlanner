package application.server;

import java.util.ArrayList;

import launcher.ConsoleBox;
import networking.server.IndividualClientTracker;

/* This Class is the essence of the server.
 * It will be connected to the console and the syncing manager.
 * 
 */
public class ServerManager implements ConsoleBox{

	private String sessionName;
	private int port;
	private ArrayList<IndividualClientTracker> connectedClients;
	private ConsoleBox outputConsole;
	
	public ServerManager(int port) {
		this.port = port;
	}
	
	public ServerManager(int port, ConsoleBox outputConsole){
		this.port = port;
		setServerConsole(outputConsole);
		outputConsole.consolePrintLine("TESTING THE CONSOLE");
		
	}
	public void setServerConsole(ConsoleBox outputConsole){
		this.outputConsole = outputConsole;
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
