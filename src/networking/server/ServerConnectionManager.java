package networking.server;

/*
 * Resources: 
 * http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java
 * http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 * 
 */

import java.net.*;
import java.io.*;

public class ServerConnectionManager {
	private ServerSocket serverSocket;

	public ServerConnectionManager(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(5000);
	}

}
