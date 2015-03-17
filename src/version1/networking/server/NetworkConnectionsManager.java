package version1.networking.server;

/*
 * Resources: 
 * http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java
 * http://docs.oracle.com/javase/tutorial/version1.networking/sockets/clientServer.html
 */

import version1.application.logic.Line;
import version1.networking.interfaces.ConnectionAcceptor;
import version1.networking.interfaces.NetworkSendable;
import version1.networking.interfaces.NetworkSyncable;
import version1.networking.util.PortListener;
import version1.console.Console;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.TimerTask;


//Keeps track of clients connected, and the port listening for new clients
public class NetworkConnectionsManager implements ConnectionAcceptor, NetworkSendable{
	private ArrayList<ConnectedClient> ClientList;
	private Console outputConsole;

	private ArrayList<Line> Inbox;
    private ActionListener outBoxLoop;
    private NetworkSendable networkSender;

	public NetworkConnectionsManager(int portNumber) {
        ClientList = new ArrayList<ConnectedClient>();
        Inbox = new ArrayList<>();
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
        networkSender = this;

	}

    public void startOutputBox() {

        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sendObjects();
            }
        }, 2 * 1 * 1000, 2 * 1 * 1000);

    }

    private void sendObjects() {
        if (Inbox.size() > 0){
            ObjectsToSend(new ArrayList<Line>(Inbox));
            System.out.println("Server Sending Inbox Objects");
        } else {
            System.out.println("Server did not send Inbox Objects, inbox is 0");
        }
    }

    public void acceptConnection(Socket clientSocket){
        ConnectedClient clientToAdd;
        try {
            clientToAdd = new ConnectedClient(clientSocket);
            ClientList.add(clientToAdd);
            clientToAdd.setInBox(Inbox);
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

    @Override
    public void ObjectsToSend(ArrayList<Line> objectsToSend) {
        for (ConnectedClient connectedClient : ClientList) {
            connectedClient.sendObjectsToTarget(objectsToSend);
        }
    }
}
