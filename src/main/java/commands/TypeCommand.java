package commands;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TypeCommand implements ArgumentCommand {
	
	private String commandName;
	
	private final List<String> extensions;
	private final CommandRegistry commandRegistry;
	
	public TypeCommand(CommandRegistry commandRegistry) {
		this.commandRegistry = commandRegistry;
		this.extensions = getExecutableExtensions();
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
			String commandPath = findCommandInPath(commandName);
			if (commandPath != null) {
				return commandName + " is " + commandPath;
			}
			
		}
		return commandName+": not found";
	}
	
	private String findCommandInPath(String commandName) {
		
		String envPath = System.getenv("PATH");
		
		String[] paths = envPath.split(File.pathSeparator);
		
		for(String path:paths){
			
			for (String ext: extensions){
				File file = new File(path,commandName+ext);
				if(file.exists() && file.canExecute()){
					return file.getAbsolutePath();
				}
			}
		}
		return null;
	}
	
	private List<String> getExecutableExtensions(){
		
		String pathExt = System.getenv("PATHEXT");
		
		if (pathExt!=null){
			return Arrays.stream(pathExt.split(File.pathSeparator))
					.map(String::toLowerCase)
					.collect(Collectors.toList());
		}
		
		return List.of("");
	}
}
