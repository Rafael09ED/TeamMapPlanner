package networking.server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import launcher.ConsoleBox;
import networking.NetworkSyncable;

public class IndividualClientTracker {
//Keeps track of what the client knows and who the client is for the server.
// Also is the parent of the method that handles communications for the client
	
	private Socket clientSocket;
	private String clientUserName;
	private ConsoleBox outputConsole;
	private ArrayList<NetworkSyncable> objectsKnown;
	private LinkedList<NetworkSyncable> objectInboxFromClient;
	private ArrayList<NetworkSyncable> objectsOwnedByClient;
	private IndividualClientCommunicator clientCommunicator;
	
	public IndividualClientTracker(Socket clientSocket, ConsoleBox outputConsole) throws IOException {
		this.outputConsole = outputConsole;
		clientUserName = "Unknown Client";
		objectInboxFromClient = new LinkedList<NetworkSyncable>();
		clientCommunicator = new IndividualClientCommunicator(clientSocket, objectInboxFromClient, this);
		
		outputConsole.consolePrintLine("An Individual Client Tracker was created");
	}
	
	public void sendObjectToClient(NetworkSyncable objectToSend){
		try {
			clientCommunicator.sendObjectToClient(objectToSend);
			outputConsole.consolePrintLine("An Object was sent To Client: " + clientUserName);
		} catch (IOException e) {
			outputConsole.consolePrintError("An Object failed to send to client: " + clientUserName);
			e.printStackTrace();
		}
	}
	public void notifyInbox(){
		outputConsole.consolePrintLine("An Object was recived from Client: " + clientUserName);
	}

}
