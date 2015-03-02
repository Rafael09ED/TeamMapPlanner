import utilities.console.Console;

import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * Created by Rafael on 2/26/2015.
 */
public class TesterClass {
    public static void main(String[] args) {
        Console console = new Console();
        console.setPrintIdentifier("Tester");
        System.out.println(System.getProperty("user.dir") + "\\ConsoleLogs\\" + "Log_"+ ManagementFactory.getRuntimeMXBean().getName() +  " " +  "FILENAME" +"_" + "TIMEHERE" +  ".txt");
        try {
            console.setPrintToFile("LOLCATZ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}