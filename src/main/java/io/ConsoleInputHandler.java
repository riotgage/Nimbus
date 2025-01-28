package io;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler{
	
	private final Scanner scanner = new Scanner(System.in);
	
	@Override
	public String readInput() {
		System.out.print("$ ");
		return scanner.nextLine();
	}
}
