package networking.server;
//http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java
import java.net.*;
import java.io.*;

import networking.NetworkSyncable;

public class IndividualClientCommunicator extends Thread {
	private Socket clientSocket;
	private ObjectOutputStream outToClient;
	private ObjectInputStream inFromClient;
	
	public IndividualClientCommunicator(Socket clientSocket) throws IOException{
		this.clientSocket = clientSocket;
		inFromClient  	= new ObjectInputStream(clientSocket.getInputStream());
		outToClient 	= new ObjectOutputStream(clientSocket.getOutputStream());
	}

	@Override
	public void run() {
		while (true) {

			try {
				inFromClient.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.err.println("Object type from client was not recognized");
				e.printStackTrace();
			}
		}
	}
	public void sendObjectToClient(NetworkSyncable objectToSend){
		
	}
}
