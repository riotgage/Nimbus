package io;

public class ConsoleOutputHandler implements OutputHandler{
	
	@Override
	public void printOutput(String output) {
		if(output !=null && !output.isEmpty())
			System.out.println(output);
	}
}
