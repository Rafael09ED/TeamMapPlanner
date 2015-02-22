package networking;

import java.util.UUID;

public interface NetworkSyncable {

		public String getAuthor();
		public String getAction();
		public String getType();
		public UUID getUniqueID();
}
