package networking.server;

/*
 * Resources: 
 * http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java
 * http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 */

import application.logic.Line;
import networking.interfaces.ConnectionAcceptor;
import networking.interfaces.NetworkSendable;
import networking.interfaces.NetworkSyncable;
import networking.util.PortListener;
import networking.util.TimeoutPreventer;
import utilities.console.Console;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;


//Keeps track of clients connected, and the port listening for new clients
public class NetworkConnectionsManager implements ConnectionAcceptor, NetworkSendable{
	private ArrayList<ConnectedClient> ClientList;
	private Console outputConsole;

	private ArrayList<Line> Inbox;
    private ActionListener outBoxLoop;
    private NetworkSendable networkSender;

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
        networkSender = this;

	}

    public void startOutputBox() {

        outBoxLoop = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (networkSender != null && Inbox != null && Inbox.size() > 0) {
                    networkSender.ObjectsToSend(Inbox);
                    Inbox = new ArrayList<>();
                    for (ConnectedClient connectedClient : ClientList) {
                        connectedClient.setInBox(Inbox);
                    }
                }
                //System.out.println("lol");
            }
        };
        new Timer(1000, outBoxLoop).start();

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
