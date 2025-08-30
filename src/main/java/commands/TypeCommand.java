package commands;

import utils.CommandUtils;

import java.util.List;

public class TypeCommand implements ArgumentCommand {
	
	private String commandName;
	
	private final CommandRegistry commandRegistry;
	
	public TypeCommand(CommandRegistry commandRegistry) {
		this.commandRegistry = commandRegistry;
	}
	
	@Override
	public Command withArguments(List<String> arguments) {
		this.commandName = arguments.get(0);
		return this;
	}

	@Override
	public String validateArguments(List<String> arguments) {
		if (arguments.isEmpty()) {
			return "Error: Missing required argument. Provide a command name to check.";
		}
		return null;
	}

	@Override
	public String execute() {
		// validate the commandName
		if(commandRegistry.isCommandRegistered(commandName)){
			return commandName+" is a shell builtin";
		}
		else{
			String commandPath = CommandUtils.findCommandInPath(commandName);
			if (commandPath != null) {
				return commandName + " is " + commandPath;
			}
		}
		return commandName+": not found";
	}
	
}
