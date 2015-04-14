package oldCode.version1.networking.util;

/*
 * Resources: 
 * http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java
 * http://docs.oracle.com/javase/tutorial/version1.networking/sockets/clientServer.html
 * 
 */

import java.net.*;
import java.io.*;

import oldCode.version1.networking.interfaces.ConnectionAcceptor;

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
                System.out.println("Connection Accepted");
	        }
	    } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
	}
}
