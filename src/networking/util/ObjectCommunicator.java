package networking.util;
//http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java
import java.net.*;
import java.util.LinkedList;
import java.io.*;

import networking.sendableObjects.NS_AntiTimeout;
import utilities.console.ConsoleOutput;
import utilities.console.Console;
import networking.interfaces.NetworkSyncable;
import networking.sendableObjects.NS_ClientInformation;

public class ObjectCommunicator extends Thread {
	@SuppressWarnings("unused")
	private Socket clientSocket;
	private ObjectOutputStream outToTarget;
	private ObjectInputStream inFromTarget;
	private Console outputConsole;
	private LinkedList<Object> serverMailboxForClient;
	//private ConnectedClient clientOfCommunicator;
	
	public ObjectCommunicator(Socket clientSocket) throws IOException{
		this.clientSocket 			= clientSocket;
		outToTarget                 = new ObjectOutputStream(clientSocket.getOutputStream());
		inFromTarget                = new ObjectInputStream(clientSocket.getInputStream());
        clientSocket.setSoTimeout(10000);
        serverMailboxForClient      = new LinkedList<Object>();
        outputConsole               = new Console();
	}
	@Override
	public void run() {
		while (true) {
			try {
				 serverMailboxForClient.add((NetworkSyncable) inFromTarget.readObject());
				 outputConsole.consolePrintLine("Object Received from:" + ((NetworkSyncable) serverMailboxForClient.get(0)).getAuthor());
				 //clientOfCommunicator.notifyInbox();
				 if (serverMailboxForClient.get(0) instanceof NS_ClientInformation) {
					 outputConsole.consolePrintLine("ClientInfo Updated: " + ((NS_ClientInformation) serverMailboxForClient.get(0)).getAuthor());
				} else if (serverMailboxForClient.get(0) instanceof NS_AntiTimeout) {
                     System.out.println("Still Here");
                     serverMailboxForClient.remove();
                 }
            } catch (SocketTimeoutException e){
                e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
                System.err.println("Object type from client was not recognized");
                e.printStackTrace();
            }
		}
	}
	public void sendObject(NetworkSyncable objectToSend) throws IOException{
		outToTarget.writeObject(objectToSend);
		outToTarget.flush();
	}

    public void setOutputConsole(ConsoleOutput outputConsole) {
        this.outputConsole.setConsoleOutput(outputConsole);
    }
}
