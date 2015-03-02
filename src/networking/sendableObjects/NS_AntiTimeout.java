package networking.sendableObjects;

import networking.interfaces.NetworkSyncable;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Rafael on 3/2/2015.
 */
public class NS_AntiTimeout implements NetworkSyncable, Serializable {
    @Override
    public String getAuthor() {
        return "Anti-Timeout";
    }

    @Override
    public String getType() {
        return "Anti-Timeout";
    }

    @Override
    public UUID getUniqueID() {
        return null;
    }
}


