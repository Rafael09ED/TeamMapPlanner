package networking;

/*
 * Resources: 
 * http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java
 * http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 * 
 */

import java.net.*;
import java.io.*;

import networking.interfaces.ConnectionAcceptor;

public class PortListener extends Thread {
	
	private int portNumber;
	private ConnectionAcceptor clientAcceptor;
	public PortListener(int portNumber, ConnectionAcceptor clientAcceptor) throws IOException{
		this.portNumber = portNumber;
		this.clientAcceptor = clientAcceptor;
	}

	@Override
	public void run() {
		System.out.println("PortListener started on port:" + portNumber);
		boolean listening = true;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            while (listening) {
            	clientAcceptor.acceptConnection(serverSocket.accept());
            	System.out.println("I accepted someone's connection");
	        }
	    } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
	}
}
