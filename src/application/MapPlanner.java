package application;

import utilities.console.Console;
import utilities.console.ConsoleOutput;

/**
 * Created by Rafael on 3/2/2015.
 */
public class MapPlanner {
    protected int port;
    protected Console outputConsole;

    public MapPlanner() {
        outputConsole = new Console();
    }

    public void setConsole(ConsoleOutput outputConsole){
        this.outputConsole.setConsoleOutput(outputConsole);
    }

}
