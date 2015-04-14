package prototyping;

import oldCode.version1.console.Console;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Rafael on 2/26/2015.
 */
public class TesterClass {
    public static void main(String[] args) {
        Point point = new Point(10, 10);
        Point point2 = new Point(10, 10);
        point2.x += 2;
        point2.x += -2;
        System.out.println((point.equals(point2)));
        Console console = new Console();
        console.setPrintIdentifier("Tester");
        //System.out.println(System.getProperty("user.dir") + "\\ConsoleLogs\\" + "Log_"+ ManagementFactory.getRuntimeMXBean().getName() +  " " +  "FILENAME" +"_" + "TIMEHERE" +  ".txt");
        try {
            console.setPrintToFile("LOLCATZ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}