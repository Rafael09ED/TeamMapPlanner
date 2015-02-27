package networking.server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import launcher.ConsoleBox;
import networking.IndividualCommunicator;
import networking.NetworkSyncable;

public class IndividualClientTracker {
//Keeps track of what the client knows and who the client is for the server.
// Also is the parent of the method that handles communications for the client
	
	private Socket targetSocket;
	private String targetIdentifier;
	private ConsoleBox outputConsole;
	private LinkedList<NetworkSyncable> objectInboxFromClient;
	private IndividualCommunicator clientCommunicator;
	
	public IndividualClientTracker(Socket clientSocket, ConsoleBox outputConsole) throws IOException {
		this.outputConsole = outputConsole;
		targetIdentifier = "Unknown Target";
		targetSocket = clientSocket;
		targetSocket.setSoTimeout(10000);
		objectInboxFromClient = new LinkedList<NetworkSyncable>();
		clientCommunicator = new IndividualCommunicator(clientSocket, objectInboxFromClient, outputConsole);
		outputConsole.consolePrintLine("An Individual Client Tracker was created");
		clientCommunicator.start();
		
	}
	public void setTargetIdentifier(String targetName){
		targetIdentifier = targetName;
	}
	
	public void sendObjectToTarget(NetworkSyncable objectToSend){
		try {
			clientCommunicator.sendObjectToClient(objectToSend);
			outputConsole.consolePrintLine("An Object was sent To Client: " + targetIdentifier);
		} catch (IOException e) {
			outputConsole.consolePrintError("An Object failed to send to client: " + targetIdentifier);
			e.printStackTrace();
		}
	}
	public void notifyInbox(){
		outputConsole.consolePrintLine("An Object was recived from Client: " + targetIdentifier);
	}

}
