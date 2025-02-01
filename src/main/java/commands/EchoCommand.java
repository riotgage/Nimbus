package commands;

import java.util.List;

public class EchoCommand implements ArgumentCommand{
	private String message;
	
	@Override
	public Command withArguments(List<String> arguments) {
		this.message = String.join(" ",arguments);
		return this;
	}
	
	@Override
	public String execute() {
		return message;
	}
}
