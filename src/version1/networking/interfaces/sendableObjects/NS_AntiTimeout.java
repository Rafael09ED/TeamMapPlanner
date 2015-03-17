package version1.networking.interfaces.sendableObjects;

import version1.networking.interfaces.NetworkSyncable;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Rafael on 3/2/2015.
 */
public class NS_AntiTimeout implements NetworkSyncable, Serializable {
    private String author = "Anti-Timeout";
    public NS_AntiTimeout(String author) {
        this.author = author;
    }

    @Override
    public String getAuthor() {
        return author;
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


