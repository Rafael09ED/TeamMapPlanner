package application.server;

import java.util.ArrayList;

import application.MapPlanner;
import networking.server.ConnectedClient;
import networking.server.NetworkConnectionsManager;
import utilities.console.Console;
import utilities.console.ConsoleOutput;

/* This Class is the essence of the server.
 * It will be connected to the console, the NetworkConnectionsManager and the syncing manager.
 */

public class Server extends MapPlanner {

	private String sessionName;
	private int port;
	private ArrayList<ConnectedClient> connectedClients;
	private Console outputConsole;
	private NetworkConnectionsManager networkingManager;
	public Server(int port) {
        super();
		this.port = port;
        networkingManager = new NetworkConnectionsManager(port);

	}
	
	public void setSessionName(String sessionName){
		this.sessionName = sessionName;
	}

}
