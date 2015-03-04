package application.client;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import application.MapPlannerGUI;
import application.logic.Line;
import application.logic.MapPlanner;
import networking.client.ConnectionToServerManager;
import networking.interfaces.NetworkSendable;
import networking.interfaces.NetworkSyncable;
import networking.interfaces.sendableObjects.NS_ClientInformation;


public class Client extends MapPlanner implements NetworkSendable{
/*
 * This class is the essence of the client. Everything that the client uses goes here.
 * The ClientManager will connect with a communicator, a console, and eventually a GUI.
 */
	private String host;
	private String userName;
	private ConnectionToServerManager communications;
	private LinkedList<String> toOutput;
    private MapPlannerGUI mapPlanner;

	public Client(String host, int port, String userName) {
        super(); // creates console
		this.host = host;
		this.port = port;
		setUserName(userName);

		communications = new ConnectionToServerManager(host, port);
        communications.setUserName(userName);
        communications.sendObjectToServer(new NS_ClientInformation(userName));
        communications.setOutputConsole(outputConsole);
        mapPlanner = new MapPlannerGUI();
        setInbox(mapPlanner.getInBox());
        mapPlanner.setSendable(this);
        mapPlanner.startSending();

    }
    public void setInbox(ArrayList<Line> lineInBox){
        communications.setInbox(lineInBox);
    }
	public void setUserName(String clientName) {
		this.userName = clientName;
		
	}

    @Override
    public void ObjectsToSend(ArrayList<Line> ObjectsToSend) {
        communications.sendObjectsToServer(ObjectsToSend);
    }


}
