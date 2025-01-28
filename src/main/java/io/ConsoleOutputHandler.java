package io;

public class ConsoleOutputHandler implements OutputHandler{
	
	@Override
	public void printOutput(String output) {
		System.out.println(output);
	}
}
