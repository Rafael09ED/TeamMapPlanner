package utilities.console;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class Console implements ConsoleOutput {
	private ConsoleOutput outputConsole;
	private boolean showTimeStamp = false;
	private boolean printToSysOut = false;
	private boolean printToFile = false;
	private BufferedWriter fileBufferOut = null;
	
	public Console() {
	}
	
	public Console(ConsoleOutput outputConsoleIn) {
		setConsoleOutput(outputConsoleIn);
	}
	
	@Override
	public void consolePrintLine(String txtLineIn) {
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

    }
	
	@Override
	public void consolePrintError(String txtErrorIn) {
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
	}
	
	@Override
	public void setConsoleOutput(ConsoleOutput outputConsoleIn) {
		if (this != outputConsoleIn) {
			outputConsole = outputConsoleIn;
		}

	}

	public static String getTimeStamp(){
		return new SimpleDateFormat("yyyy.MM.dd - HH:mm:ss").format(new Date());
		
	}
    private static String getFileTimeStamp(){
        return new SimpleDateFormat("yyyy-MM-dd--HH-mm-ss").format(new Date());

    }
	public void setPrintToFile(String fileName) throws IOException{
		//returns true if can create the file to output
		printToFile = true;
		File file = new File(System.getProperty("user.dir") + "\\ConsoleLogs\\" + "Log_" + fileName +"_" +getTimeStamp() +  ".txt");
		
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		fileBufferOut = new BufferedWriter(fw);
		
		printLine("Console Printer Created With Name: " + fileName);
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
}
