package networking.server;

import networking.ObjectCommunicator;
import networking.TimeoutPreventer;
import networking.interfaces.NetworkSyncable;
import utilities.console.Console;
import utilities.console.ConsoleOutput;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConnectedClient {
    //Keeps track of what the client knows and who the client is for the server.
    //Also is the parent of the method that handles communications for the client
	
	private Socket targetSocket;
	private String clientIdentifier;
	private Console outputConsole;
	private LinkedList<NetworkSyncable> objectInboxFromClient;
    private ObjectCommunicator clientCommunicator;
    private String printIdentifier = "Connected Client: Unknown Client";

	public ConnectedClient(Socket clientSocket) throws IOException {
		clientIdentifier = "Unknown Client";
		targetSocket = clientSocket;
		//targetSocket.setSoTimeout(10000);
		objectInboxFromClient = new LinkedList<NetworkSyncable>();
		clientCommunicator = new ObjectCommunicator(clientSocket);	
		clientCommunicator.start();
        outputConsole = new Console();
        TimeoutPreventer timeoutPreventer = new TimeoutPreventer(clientCommunicator);
    }

	public void setTargetIdentifier(String clientName){
		clientIdentifier = clientName;
        printIdentifier = "ConnectedClient: "  + clientName;
	}

	public void sendObjectToTarget(NetworkSyncable objectToSend){
		try {
			clientCommunicator.sendObject(objectToSend);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
