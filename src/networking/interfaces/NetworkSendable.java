package networking.interfaces;

import application.logic.Line;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Rafael on 3/3/2015.
 */
public interface NetworkSendable {
    public void ObjectsToSend(ArrayList<Line> objectsToSend);
}
