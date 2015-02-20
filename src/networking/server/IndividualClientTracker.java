package networking.server;

import java.io.IOException;
import java.net.Socket;

public class IndividualClientTracker {
//Keeps track of what the client knows and who the client is for the server.
	
	private Socket clientSocket;
	private String clientUserName;
	
	public IndividualClientTracker(Socket clientSocket) throws IOException {
		new IndividualClientCommunicator(clientSocket);
	}
	
	

}
