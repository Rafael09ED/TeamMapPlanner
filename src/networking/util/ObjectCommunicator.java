package networking.util;
//http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java

import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.*;

import application.logic.Line;
import networking.interfaces.sendableObjects.NS_AntiTimeout;
import utilities.console.ConsoleOutput;
import utilities.console.Console;
import networking.interfaces.NetworkSyncable;
import networking.interfaces.sendableObjects.NS_ClientInformation;

public class ObjectCommunicator extends Thread {
    @SuppressWarnings("unused")
    private Socket clientSocket;
    private ObjectOutputStream outToTarget;
    private ObjectInputStream inFromTarget;
    private Console outputConsole;
    private ArrayList<Object> communicatorInbox;
    private ArrayList<Line> inbox;
    //private ConnectedClient clientOfCommunicator;

    public ObjectCommunicator(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        outToTarget = new ObjectOutputStream(clientSocket.getOutputStream());
        inFromTarget = new ObjectInputStream(clientSocket.getInputStream());
        clientSocket.setSoTimeout(10000);
        communicatorInbox = new ArrayList<Object>();
        outputConsole = new Console();
    }

    @Override
    public void run() {
        while (true) {
            try {
                communicatorInbox.add((NetworkSyncable) inFromTarget.readObject());
                outputConsole.consolePrintLine("Object Received from:" + ((NetworkSyncable) communicatorInbox.get(0)).getAuthor());
                //clientOfCommunicator.notifyInbox();
                if (communicatorInbox.get(0) instanceof NS_ClientInformation) {
                    outputConsole.consolePrintLine("ClientInfo Updated: " + ((NS_ClientInformation) communicatorInbox.get(0)).getAuthor());
                } else if (communicatorInbox.get(0) instanceof NS_AntiTimeout) {
                   // System.out.println("Still Here");
                } else if (communicatorInbox.get(0) instanceof Line && inbox != null) {
                    inbox.add((Line)communicatorInbox.get(0));
                }
                communicatorInbox.remove(communicatorInbox.size()-1);
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.err.println("Object type from client was not recognized");
                e.printStackTrace();
            }
        }
    }

    public void sendObject(NetworkSyncable objectToSend) throws IOException {
        outToTarget.writeObject(objectToSend);
        outToTarget.flush();
    }

    public void setOutputConsole(ConsoleOutput outputConsole) {
        this.outputConsole.setConsoleOutput(outputConsole);
    }

    public void sendObjects(ArrayList<Line> objectsToSend) {
        try {
            for (Line line : objectsToSend) {
                outToTarget.writeObject((NetworkSyncable) line);
            }
            outToTarget.flush();
            outputConsole.consolePrintLine("Batch Sent");
        } catch (IOException e) {
            e.printStackTrace();
            outputConsole.consolePrintError("Batch Failed to Send");
        }
    }

    public void setInbox(ArrayList<Line> inbox) {
        this.inbox = inbox;
    }
}
