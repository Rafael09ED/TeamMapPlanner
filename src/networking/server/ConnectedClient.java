package networking.server;

import application.logic.Line;
import networking.interfaces.NetworkSendable;
import networking.util.ObjectCommunicator;
import networking.util.TimeoutPreventer;
import networking.interfaces.NetworkSyncable;
import utilities.console.Console;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class ConnectedClient {
    //Keeps track of what the client knows and who the client is for the server.
    //Also is the parent of the method that handles communications for the client
	
	private Socket targetSocket;
	private String clientIdentifier;
	private Console outputConsole;
	private LinkedList<NetworkSyncable> objectInboxFromClient;
    private ObjectCommunicator clientCommunicator;
    private String printIdentifier = "Connected Client: Unknown Client";
    TimeoutPreventer timeoutPreventer;

	public ConnectedClient(Socket clientSocket) throws IOException {
		clientIdentifier = "Unknown Client";
		targetSocket = clientSocket;
		targetSocket.setSoTimeout(10000);
		objectInboxFromClient = new LinkedList<NetworkSyncable>();
		clientCommunicator = new ObjectCommunicator(clientSocket);	
		clientCommunicator.start();
        outputConsole = new Console();
        timeoutPreventer = new TimeoutPreventer(clientCommunicator);
        timeoutPreventer.setParent("Server");
        clientCommunicator.setOutputConsole(outputConsole);

    }

	public void setTargetIdentifier(String clientName){
		clientIdentifier = clientName;
        printIdentifier = "ConnectedClient: "  + clientName;
	}

    public void setOutputConsole(Console outputConsole) {
        this.outputConsole.setConsoleOutput(outputConsole);
        outputConsole.setPrintToSysOut(true);

    }

    public void sendObjectToTarget(NetworkSyncable objectToSend){
		try {
			clientCommunicator.sendObject(objectToSend);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void sendObjectsToTarget(ArrayList<Line> objectsToSend) {
        clientCommunicator.sendObjects(objectsToSend);
    }

    public void setInBox(ArrayList<Line> inBox) {
        clientCommunicator.setInbox(inBox);
    }
}
