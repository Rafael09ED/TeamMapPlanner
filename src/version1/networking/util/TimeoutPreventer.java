package version1.networking.util;

import version1.networking.interfaces.sendableObjects.NS_AntiTimeout;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TimeoutPreventer extends TimerTask {
    private ObjectCommunicator communicator;
    private String parent = "Unknown";

    public TimeoutPreventer(ObjectCommunicator communicator) {
        this.communicator = communicator;
        Timer timer = new Timer();
        timer.schedule(this, 1000, 5000);
        System.out.println("Timeout Preventer Created");
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        try {
            communicator.sendObject(new NS_AntiTimeout(parent));
            //System.out.println("UN-TIME-ING OUT");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
