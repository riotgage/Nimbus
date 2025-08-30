package commands;

import utils.CommandUtils;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory{

	private final CommandRegistry commandRegistry;

	public CommandFactory(CommandRegistry commandRegistry) {
		this.commandRegistry = commandRegistry;
	}

	public Command createCommand(String commandName) {
		Command command = commandRegistry.getCommand(commandName);
	
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
}
