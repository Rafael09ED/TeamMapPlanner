package networking.server;

/*
 * Resources: 
 * http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java
 * http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 */

import networking.interfaces.ConnectionAcceptor;
import networking.interfaces.NetworkSyncable;
import networking.util.PortListener;
import networking.util.TimeoutPreventer;
import utilities.console.Console;


import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


//Keeps track of clients connected, and the port listening for new clients
public class NetworkConnectionsManager implements ConnectionAcceptor{
	private ArrayList<ConnectedClient> ClientList;
	private Console outputConsole;
	
	public NetworkConnectionsManager(int portNumber) {

		ClientList = new ArrayList<ConnectedClient>();

		outputConsole = new Console();

		outputConsole.consolePrintLine("Starting Network Connections Manager");
		try {
			new PortListener(portNumber, this).start();
			outputConsole.consolePrintLine("Server Listening For Clients on port:" + portNumber);
		} catch (IOException e) {
			outputConsole.consolePrintError("Server Could not listen to clients on port:" + portNumber);
			System.err.println("Server Port Error: Exception in NetworkConnectionsManager");
			e.printStackTrace();
		}

	}

	public void acceptConnection(Socket clientSocket){
        ConnectedClient clientToAdd;
        try {
            clientToAdd = new ConnectedClient(clientSocket);
            ClientList.add(clientToAdd);
            clientToAdd.setOutputConsole(outputConsole);
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

    public void setOutputConsole(Console outputConsole) {
        this.outputConsole.setConsoleOutput(outputConsole);
    }

    public void sendObjectToClient(NetworkSyncable objectToSend, ConnectedClient clientTarget){
		clientTarget.sendObjectToTarget(objectToSend); // A little silly, IDK why I have this 
	}

}
