package networking;
//http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java
import java.net.*;
import java.util.LinkedList;
import java.io.*;

import networking.sendableObjects.NS_ClientInformation;
import launcher.ConsoleBox;

public class IndividualCommunicator extends Thread {
	@SuppressWarnings("unused")
	private Socket clientSocket;
	private ObjectOutputStream outToClient;
	private ObjectInputStream inFromClient;
	private ConsoleBox outputConsole;
	private LinkedList<NetworkSyncable> serverMailboxForClient;
	//private IndividualClientTracker clientOfCommunicator;
	
	public IndividualCommunicator(Socket clientSocket, LinkedList<NetworkSyncable> objectInboxFromClient, ConsoleBox outputConsole) throws IOException{
		this.clientSocket 			= clientSocket;
		this.outputConsole 			= outputConsole;
		//this.clientOfCommunicator	= clientOfCommunicator;
		
		serverMailboxForClient 		= objectInboxFromClient;
		inFromClient  				= new ObjectInputStream(clientSocket.getInputStream());
		outToClient 				= new ObjectOutputStream(clientSocket.getOutputStream());
	}
	@Override
	public void run() {
		while (true) {
			try {
				 serverMailboxForClient.add((NetworkSyncable) inFromClient.readObject());
				 outputConsole.consolePrintLine("Object Recieved");
				 //clientOfCommunicator.notifyInbox();
				 if (serverMailboxForClient.get(0) instanceof NS_ClientInformation) {
					 outputConsole.consolePrintLine("New Object from: " + ((NS_ClientInformation) serverMailboxForClient.get(0)).getAuthor());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.err.println("Object type from client was not recognized");
				e.printStackTrace();
			}
		}
	}
	public void sendObjectToClient(NetworkSyncable objectToSend) throws IOException{
		outToClient.writeObject(objectToSend);
		outToClient.flush();
	}
	
}
