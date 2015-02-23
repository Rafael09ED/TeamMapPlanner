package networking.sendableObjects;

import java.io.Serializable;
import java.util.UUID;

import networking.NetworkSyncable;

public class NS_ClientInformation implements NetworkSyncable, Serializable{

	private UUID uniqueID;
	private static final long serialVersionUID = -1541740741183709714L;
	private String Author;

	public NS_ClientInformation(String author) {
		uniqueID = UUID.randomUUID();
		this.Author = author;
	}

	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return Author;
	}

	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return "No Action";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "ClientInformation";
	}

	@Override
	public UUID getUniqueID() {
		return uniqueID;
	}

}
