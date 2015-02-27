package utilities.console;

public interface ConsoleOutput {
	void consolePrintLine(String txtLineIn);
	void consolePrintError(String txtErrorIn);
	void setConsoleOutput(ConsoleOutput outputConsole);
}
