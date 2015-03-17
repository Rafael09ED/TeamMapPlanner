package version1.networking.interfaces;

import version1.application.logic.Line;

import java.util.ArrayList;

/**
 * Created by Rafael on 3/3/2015.
 */
public interface NetworkSendable {
    public void ObjectsToSend(ArrayList<Line> objectsToSend);
}
