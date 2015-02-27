package application.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import launcher.ConsoleBox;
import networking.NetworkSyncable;

public class ConnectionToServerManager extends Thread{
//Handles the client's communications to the server
	private String host;
	private int port;
	private Socket socketToServer;
	ObjectOutputStream outToServer;
    ObjectInputStream inFromServer;
    ConsoleBox outputConsole;
    
	public ConnectionToServerManager(String host, int port, ConsoleBox outputConsole) {
		this.host = host;
		this.port = port;
		this.outputConsole = outputConsole;
		outputConsole.consolePrintLine("ConnectionManager connected to console");
		
        try {
        	socketToServer = new Socket(host,port);
        	outToServer = new ObjectOutputStream(socketToServer.getOutputStream());
			inFromServer = new ObjectInputStream(socketToServer.getInputStream());
			outputConsole.consolePrintLine("ConnectionManager created object streams");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
	}
	
	public void sendObjectToServer(NetworkSyncable objectToSend){
		
	}

}
