package commands;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

	private Map<String,Command> commandRegistry = new HashMap<>();
	
	public CommandFactory() {
		// Register all supported commands
//		commandRegistry.put("help", new HelpCommand());
		commandRegistry.put("echo", new EchoCommand());
		// Add more commands as needed
	}
	
	public Command createCommand(String input) {
		String[] parts = input.split("\\s+", 2);
		String commandName = parts[0];
		String arguments = parts.length > 1 ? parts[1] : "";
		
		Command command = commandRegistry.get(commandName);
		
		if (command instanceof ArgumentCommand) {
			return ((ArgumentCommand) command).withArguments(arguments);
		}
		return command;
	}
}
