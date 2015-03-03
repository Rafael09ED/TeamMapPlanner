package application.client;

import java.util.LinkedList;

import application.MapPlanner;
import networking.client.ConnectionToServerManager;
import networking.interfaces.sendableObjects.NS_ClientInformation;


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
        super(); // creates console
		this.host = host;
		this.port = port;
		setUserName(userName);

		communications = new ConnectionToServerManager(host, port);
        communications.setUserName(userName);
        communications.sendObjectToServer(new NS_ClientInformation(userName));
        communications.setOutputConsole(outputConsole);
    }

	public void setUserName(String clientName) {
		this.userName = clientName;
		
	}

}
