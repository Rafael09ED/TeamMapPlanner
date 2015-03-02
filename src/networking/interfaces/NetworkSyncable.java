package networking.interfaces;

import java.io.Serializable;
import java.util.UUID;

public interface NetworkSyncable {

		public String getAuthor();
		public String getType();
		public UUID getUniqueID();
}
