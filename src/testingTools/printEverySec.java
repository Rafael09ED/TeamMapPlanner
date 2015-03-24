package testingTools;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rafael on 3/20/2015.
 */
public class printEverySec {
    String textToPrint;
    private String appendToPrint = "";
    private boolean print = true;
    private static boolean printGlobal = true;


    public printEverySec(final String textToPrint) {

        this.textToPrint = textToPrint;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (print && printGlobal) {
                    System.out.println(textToPrint + " " + appendToPrint);
                }
            }
        } , 1 * 1000 ,1 * 1000);

    }
    public void setAppendToPrint(String appendToPrint){
        this.appendToPrint = appendToPrint;
    }
    public void printTextReceived(Boolean printIn){
        this.print = printIn;
    }
    public static void setGlobalPrintStatus(Boolean printGlobalIn){
        printGlobal = printGlobalIn;
    }
}
