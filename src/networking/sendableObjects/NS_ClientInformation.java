package networking.sendableObjects;

import java.io.Serializable;
import java.util.UUID;

import networking.NetworkSyncable;

public class NS_ClientInformation implements NetworkSyncable, Serializable{

	private UUID uniqueID;
	private static final long serialVersionUID = -1541740741183709714L;

	public NS_ClientInformation() {
		uniqueID = UUID.randomUUID();
	}

	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return null;
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
