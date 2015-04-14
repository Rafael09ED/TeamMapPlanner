package oldCode.version1.application.logic;

import oldCode.version1.console.Console;
import oldCode.version1.console.ConsoleOutput;

import java.util.ArrayList;

/**
 * Created by Rafael on 3/2/2015.
 */
public abstract class  MapPlanner {
    protected int port;
    protected Console outputConsole;

    public MapPlanner() {
        outputConsole = new Console();
    }
    public abstract void ObjectsToSend(ArrayList<Line> ObjectsToSend);

    public void setConsole(ConsoleOutput outputConsole){
        this.outputConsole.setConsoleOutput(outputConsole);
    }

}
