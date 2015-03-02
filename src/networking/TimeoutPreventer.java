package networking;

import networking.interfaces.NetworkSyncable;
import networking.sendableObjects.NS_AntiTimeout;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TimeoutPreventer extends TimerTask {
    private ObjectCommunicator communicator;

    public TimeoutPreventer(ObjectCommunicator communicator) {
        this.communicator = communicator;
        Timer timer = new Timer();
        timer.schedule(this, 1000, 5000);
    }

    @Override
    public void run() {
        try {
            communicator.sendObject(new NS_AntiTimeout());
            //System.out.println("UN-TIME-ING OUT");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
