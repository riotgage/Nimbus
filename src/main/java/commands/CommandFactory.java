package commands;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory implements CommandRegistry{

	private Map<String,Command> commands = new HashMap<>();
	
	public CommandFactory() {
		// Register all supported commands
//		commandRegistry.put("help", new HelpCommand());
		commands.put("echo", new EchoCommand());
		commands.put("type",new TypeCommand(this));
		commands.put("exit",new ExitCommand());
		// Add more commands as needed
	}
	
	public Command createCommand(String commandName) {
		Command command = commands.get(commandName);

		return command;
	}
	
	@Override
	public boolean isCommandRegistered(String commandName) {
		return commands.containsKey(commandName);
	}
}
