package commands;

import utils.CommandUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExternalCommand implements ArgumentCommand{
	
	List<String> args;
	String commandName;
	@Override
	public Command withArguments(List<String> arguments) {
		this.commandName = arguments.get(0);
		if (arguments.size()>1){
			this.args = arguments.subList(1,arguments.size());
		}else {
			this.args=null;
		}
		return this;
	}
	
	@Override
	public String execute() {
		String commandPath = CommandUtils.findCommandInPath(commandName);
		if (commandPath == null) {
			return commandName + ": command not found";
		}
		
		// Command Exists. Create a process builder to execute the command
		
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command(buildCommand());
		processBuilder.redirectErrorStream(true);
		
		try{
			Process process = processBuilder.start();
			
			BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String stdout = stdoutReader.lines().collect(Collectors.joining("\n"));
			
			// Read stderr
			BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String stderr = stderrReader.lines().collect(Collectors.joining("\n"));
			
			int retValue = process.waitFor();
			
			if(retValue!=0){
				return !stderr.isEmpty() ? stderr : stdout;
			}
			return stdout;
		}
		catch (IOException | InterruptedException e) {
			return "Error executing " + commandName + ": " + e.getMessage();
		}
		
	}
	
	private List<String> buildCommand() {
		List<String> fullCommand = new ArrayList<>();
		
		fullCommand.add(commandName);
		fullCommand.addAll(args);
		return fullCommand;
	}
}
