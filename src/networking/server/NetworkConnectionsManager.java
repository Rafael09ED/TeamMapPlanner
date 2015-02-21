package networking.server;

/*
 * Resources: 
 * http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java
 * http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 * 
 */

import java.net.*;
import java.util.ArrayList;
import java.io.*;

import launcher.ConsoleBox;
import networking.NetworkSyncable;
import networking.PortListener;


//Keeps track of clients connected, and the port listening for new clients
public class NetworkConnectionsManager implements ConsoleBox{
	private ArrayList<IndividualClientTracker> ClientList;
	private ConsoleBox outputConsole;
	
	public NetworkConnectionsManager(int portNumber, ConsoleBox outputConsole) {
		ClientList = new ArrayList<IndividualClientTracker>();
		this.outputConsole = outputConsole;
		try {
			new PortListener(portNumber, this);
			outputConsole.consolePrintLine("Server Listening For Clients on port:" + portNumber);
		} catch (IOException e) {
			outputConsole.consolePrintError("Server Cound not listen to clients on port:" + portNumber);
			System.err.println("Server Port Error: Exception in NetworkConnectionsManager");
			e.printStackTrace();
		}
	}

	public void createClientTracker(Socket clientSocket) throws IOException {
		IndividualClientTracker clientToAdd = new IndividualClientTracker(clientSocket, outputConsole);
		ClientList.add(clientToAdd);
		
	}
	
	public void sendObjectToClient(NetworkSyncable objectToSend, IndividualClientTracker clientTarget){
		clientTarget.sendObjectToClient(objectToSend); // A little silly, IDK why I have this 
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
