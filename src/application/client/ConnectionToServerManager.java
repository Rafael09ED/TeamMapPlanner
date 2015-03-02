package application.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import networking.ObjectCommunicator;
import networking.TimeoutPreventer;
import utilities.console.Console;
import networking.interfaces.NetworkSyncable;
import utilities.console.Console;

public class ConnectionToServerManager{
//Handles the client's communications to the server
	private String host;
	private int port;
	private Socket socketToServer;
    private ObjectCommunicator objectCommunicator;
    private Console outputConsole;
    
	public ConnectionToServerManager(String host, int port) {
		this.host = host;
		this.port = port;
		this.outputConsole = new Console();

        try {
        	socketToServer = new Socket(host,port);
            objectCommunicator = new ObjectCommunicator(socketToServer);
            objectCommunicator.start();
			outputConsole.consolePrintLine("ConnectionManager created object streams");
		} catch (IOException e) {
			e.printStackTrace();
		}
        TimeoutPreventer timeoutPreventer = new TimeoutPreventer(objectCommunicator);
	}

	public void sendObjectToServer(NetworkSyncable objectToSend){
        try {
            objectCommunicator.sendObject(objectToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
