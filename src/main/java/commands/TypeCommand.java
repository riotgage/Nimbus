package commands;

import utils.CommandUtils;
public class TypeCommand implements ArgumentCommand {
	
	private String commandName;
	
	private final CommandRegistry commandRegistry;
	
	public TypeCommand(CommandRegistry commandRegistry) {
		this.commandRegistry = commandRegistry;
	}
	
	@Override
	public Command withArguments(String arguments) {
		this.commandName = arguments;
		return this;
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
