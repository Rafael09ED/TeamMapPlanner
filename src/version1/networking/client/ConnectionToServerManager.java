package version1.networking.client;

import version1.application.logic.Line;
import version1.networking.interfaces.NetworkSyncable;
import version1.networking.util.ObjectCommunicator;
import version1.networking.util.TimeoutPreventer;
import version1.console.Console;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionToServerManager{
//Handles the client's communications to the server
	private String host;
	private int port;
	private Socket socketToServer;
    private ObjectCommunicator objectCommunicator;
    private Console outputConsole;
    private TimeoutPreventer timeoutPreventer;
	public ConnectionToServerManager(String host, int port) {

		this.host = host;
		this.port = port;
		this.outputConsole = new Console();

        try {
        	socketToServer = new Socket(host,port);
            objectCommunicator = new ObjectCommunicator(socketToServer);
            objectCommunicator.setOutputConsole(outputConsole);
            objectCommunicator.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
        timeoutPreventer = new TimeoutPreventer(objectCommunicator);
    }
    public void setUserName(String nameIn){
        timeoutPreventer.setParent(nameIn);
    }
    public void setOutputConsole(Console outputConsole) {
        this.outputConsole.setConsoleOutput(outputConsole);
        outputConsole.consolePrintLine("Console Connected to ConnectToServerManager");
    }

    public void sendObjectToServer(NetworkSyncable objectToSend){
        try {
            objectCommunicator.sendObject(objectToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInbox(ArrayList<Line> lineInBox) {
        objectCommunicator.setInbox(lineInBox);
    }

    public void sendObjectsToServer(ArrayList<Line> objectsToSend) {
        System.out.println(objectsToSend.size() + "is the size");
        objectCommunicator.sendObjects(objectsToSend);
    }
}
