package application.client;

import java.io.IOException;
import java.util.LinkedList;

import application.MapPlanner;
import networking.sendableObjects.NS_ClientInformation;
import utilities.console.Console;
import utilities.console.ConsoleOutput;


public class Client extends MapPlanner{
/*
 * This class is the essence of the client. Everything that the client uses goes here.
 * The ClientManager will connect with a communicator, a console, and eventually a GUI.
 */
	private String host;
	private String userName;
	private ConnectionToServerManager communications;
	private LinkedList<String> toOutput;

	public Client(String host, int port, String userName) {
        super();
		this.host = host;
		this.port = port;
		setUserName(userName);

		communications = new ConnectionToServerManager(host, port);
        communications.sendObjectToServer(new NS_ClientInformation(userName));
    }

	public void setUserName(String clientName) {
		this.userName = clientName;
		
	}

}
