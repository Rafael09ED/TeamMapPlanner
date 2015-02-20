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

import networking.PortListener;

public class ClientConnectionManager {
	private ArrayList<IndividualClientTracker> ClientList;
	
	public ClientConnectionManager(int portNumber) {
		try {
			new PortListener(portNumber, this);
		} catch (IOException e) {
			System.err.println("Port Listener Threw an Exception");
			e.printStackTrace();
		}
	}

	public void createClientTracker(Socket clientSocket) throws IOException {
		ClientList.add(new IndividualClientTracker(clientSocket));
		
	}

}
