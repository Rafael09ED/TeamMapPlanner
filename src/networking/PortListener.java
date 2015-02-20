package networking;

/*
 * Resources: 
 * http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java
 * http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 * 
 */

import java.net.*;
import java.util.ArrayList;
import java.io.*;

import networking.server.ClientConnectionManager;

public class PortListener extends Thread {
	
	private int portNumber;
	private ClientConnectionManager clientManager;
	public PortListener(int portNumber, ClientConnectionManager clientManager) throws IOException{
		this.portNumber = portNumber;
		this.clientManager = clientManager;
	}

	@Override
	public void run() {
		boolean listening = true;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            while (listening) {
            	clientManager.createClientTracker(serverSocket.accept());
	        }
	    } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.out.println();
            System.exit(-1);
        }
	}
}
