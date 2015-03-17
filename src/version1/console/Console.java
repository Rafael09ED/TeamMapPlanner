package version1.console;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console implements ConsoleOutput {
	private ConsoleOutput outputConsole;
	private boolean showTimeStamp = false;
	private boolean printToSysOut = false;
	private boolean printToFile = false;
	private BufferedWriter fileBufferOut = null;
	private String printIdentifier = "Unknown";

	public Console() {
	}

    //Constructor that sets a console for everything to be forwarded to
	public Console(ConsoleOutput outputConsoleIn) {
		setConsoleOutput(outputConsoleIn);
	}

	@Override
	public void consolePrintLine(String txtLineIn) {
        // Prints info based off of settings
		if (outputConsole != null) {
			outputConsole.consolePrintLine(txtLineIn);
		}
		if (printToFile && fileBufferOut != null) {
            try {
                fileBufferOut.write(txtLineIn);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (printToSysOut){
            System.out.println(Console.getSysOutFormatString(txtLineIn, "Info" ,printIdentifier));
        }
    }

    @Override
    public void consolePrintError(String txtErrorIn) {
        // Prints errors based off of settings
        if (outputConsole != null) {
            outputConsole.consolePrintError(txtErrorIn);
        }
        if (printToFile && fileBufferOut != null) {
            try {
                printLine(txtErrorIn);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println();
            }
        }
        if (printToSysOut){
            System.out.println(getSysOutFormatString(txtErrorIn, "Error", printIdentifier));
        }
    }

	public void setConsoleOutput(ConsoleOutput outputConsoleIn) {
		if (this != outputConsoleIn) {
			outputConsole = outputConsoleIn;
		}
	}

    public void setPrintIdentifier(String printIdentifier){
        this.printIdentifier = printIdentifier;
    }

	public void setPrintToFile(String fileName) throws IOException{
		//returns true if can create the file to output
        if (fileBufferOut !=  null ){
            fileBufferOut.close();
        }

		File file = new File(System.getProperty("user.dir") + "\\ConsoleLogs\\" + "Log_"+ ManagementFactory.getRuntimeMXBean().getName() +  " " + fileName +"_" +getFileTimeStamp() +  ".txt");
		if (!file.exists()) {
            file.getParentFile().mkdirs();
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		fileBufferOut = new BufferedWriter(fw);

        printToFile = true;
        //Sets boolean here so if it fails to create the file then it won't keep printing

		printLine("Console Printer Created With Name: " + fileName);
        printLine("Identifier: " + printIdentifier);
        consolePrintLine("Console Log File Created");
	}

	private void printLine(String lineToPrint) throws IOException{
		if (showTimeStamp) {
			fileBufferOut.write(getTimeStamp() + " - " +lineToPrint);
		} else {
			fileBufferOut.write(lineToPrint);
		}

		fileBufferOut.newLine();
		fileBufferOut.flush();
	}


    public void setPrintToSysOut(boolean printToSysOut) {
        this.printToSysOut = printToSysOut;
    }

    // Quick Formatting Methods for printing lines and getting the timestamp format.

    public static String getTimeStamp(){
        return new SimpleDateFormat("yyyy.MM.dd - HH:mm:ss").format(new Date());
    }

    private static String getFileTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd--HH-mm-ss").format(new Date());
    }

    public static String getSysOutFormatString(String messageIn, String messageType, String stringFrom){
        return getTimeStamp() + " - " + messageType + "From: " + stringFrom + " : " + messageIn;
    }


}
