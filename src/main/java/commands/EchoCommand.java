package commands;

public class EchoCommand implements ArgumentCommand{
	private String message;
	
	@Override
	public Command withArguments(String arguments) {
		this.message = arguments;
		return this;
	}
	
	@Override
	public String execute() {
		return message;
	}
}
