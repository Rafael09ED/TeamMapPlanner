package networking.interfaces;

import java.net.Socket;

public interface ConnectionAcceptor {
	public void acceptConnection(Socket socket);
}
