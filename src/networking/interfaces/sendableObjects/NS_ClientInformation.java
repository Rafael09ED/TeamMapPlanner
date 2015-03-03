package networking.interfaces.sendableObjects;

import java.io.Serializable;
import java.util.UUID;
import networking.interfaces.NetworkSyncable;


public class NS_ClientInformation implements NetworkSyncable, Serializable{

	private UUID uniqueID;
	private static final long serialVersionUID = -1541740741183709714L;
	private String Author = "Unknown User";

	public NS_ClientInformation(String author) {
		uniqueID = UUID.randomUUID();
		this.Author = author;
	}

	@Override
	public String getAuthor() {
		return Author;
	}
	
	@Override
	public String getType() {
		return "ClientInformation";
	}

	@Override
	public UUID getUniqueID() {
		return uniqueID;
	}

}
