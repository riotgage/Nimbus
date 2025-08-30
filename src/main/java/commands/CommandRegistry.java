package commands;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
	private final Map<String, Command> commands = new HashMap<>();

	public CommandRegistry() {
		commands.put("echo", new EchoCommand());
		commands.put("type", new TypeCommand(this));
		commands.put("exit", new ExitCommand());
		commands.put("pwd", new PwdCommand());
		commands.put("cd", new CdCommand());
	}

	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}

	public boolean isCommandRegistered(String commandName) {
		return commands.containsKey(commandName);
	}

	public Map<String, Command> getAllCommands() {
		return commands;
	}
}
