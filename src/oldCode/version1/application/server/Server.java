package oldCode.version1.application.server;

import oldCode.version1.application.logic.Line;
import oldCode.version1.application.logic.MapPlanner;
import oldCode.version1.networking.server.ConnectedClient;
import oldCode.version1.networking.server.NetworkConnectionsManager;

import java.util.ArrayList;

/* This Class is the essence of the server.
 * It will be connected to the console, the NetworkConnectionsManager and the syncing manager.
 */

public class Server extends MapPlanner {

    private String sessionName;
    private int port;
    private ArrayList<ConnectedClient> connectedClients;
    private NetworkConnectionsManager networkingManager;

    public Server(int port) {
        super(); // creates Console
        this.port = port;

        networkingManager = new NetworkConnectionsManager(port);
        networkingManager.setOutputConsole(outputConsole);
        networkingManager.startOutputBox();
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    @Override
    public void ObjectsToSend(ArrayList<Line> ObjectsToSend) {
        System.out.println("Sending an Object Set  - Size: " + ObjectsToSend.size());
        networkingManager.ObjectsToSend(ObjectsToSend);
    }


}
