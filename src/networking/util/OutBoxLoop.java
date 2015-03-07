package networking.util;

import application.logic.Line;
import networking.interfaces.NetworkSendable;

import java.util.*;

/**
 * Created by Rafael on 3/4/2015.
 */
public class OutBoxLoop extends TimerTask {
    //private ArrayList<Line> lineOutBox;
    private NetworkSendable outBoxSender;
    private List<Line> lineOutBox;

    public OutBoxLoop() {
        //lineOutBox = new ArrayList<Line>();
        lineOutBox = Collections.synchronizedList(new ArrayList<Line>());
    }

    @Override
    public void run() {
        System.out.println((outBoxSender != null) + " " + (lineOutBox != null) + " " + (lineOutBox.size() > 0));
        System.out.println(lineOutBox.size());
        if (outBoxSender != null && lineOutBox != null && lineOutBox.size() > 0) {
            outBoxSender.ObjectsToSend(new ArrayList<Line>(lineOutBox));
            //lineOutBox = new ArrayList<Line>();
            System.out.println("Sending List");
        }
        
        System.out.println("run ID: " + System.identityHashCode(this.lineOutBox));

    }
    public void setLineOutBox(Line lineIn){
        System.out.println("! List Recived " + lineIn.toString());
        lineOutBox.add(lineIn);
        System.out.println(lineOutBox.size());
        
        System.out.println("set ID: " + System.identityHashCode(this.lineOutBox));
        
    }
    public void setNetworkSender(NetworkSendable outBoxSender){
        this.outBoxSender = outBoxSender;
    }

}
