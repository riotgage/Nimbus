package commands;

import utils.CommandUtils;

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
		commands.put("pwd",new PwdCommand());
		commands.put("cd",new CdCommand());
		// Add more commands as needed
	}
	
	public Command createCommand(String commandName) {
		Command command = commands.get(commandName);
	
		// this is not a built in command
		// check if it is available in PATH
		if(command==null){
			String commandPath = CommandUtils.findCommandInPath(commandName);
			if (commandPath!=null){
				return new ExternalCommand();
			}
		}
		return command;
	}
	
	@Override
	public boolean isCommandRegistered(String commandName) {
		return commands.containsKey(commandName);
	}
}
