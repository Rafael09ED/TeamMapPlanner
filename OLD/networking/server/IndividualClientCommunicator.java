package networking.server;
//http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java
import java.net.*;
import java.util.LinkedList;
import java.io.*;

import launcher.ConsoleBox;
import networking.NetworkSyncable;

public class IndividualClientCommunicator extends Thread {
	@SuppressWarnings("unused")
	private Socket clientSocket;
	private ObjectOutputStream outToClient;
	private ObjectInputStream inFromClient;
	private ConsoleBox outputConsole;
	private LinkedList<NetworkSyncable> serverMailboxForClient;
	//private IndividualClientTracker clientOfCommunicator;
	
	public IndividualClientCommunicator(Socket clientSocket, LinkedList<NetworkSyncable> objectInboxFromClient) throws IOException{
		this.clientSocket 			= clientSocket;
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
				 //clientOfCommunicator.notifyInbox();
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
