package commands;

import utils.ShellContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CdCommand implements ArgumentCommand{
	
	private String targetDirectory;
	@Override
	public Command withArguments(String arguments) {
		this.targetDirectory = arguments.trim().isEmpty() ? System.getProperty("user.home") : arguments.trim();
		return this;
	}
	
	@Override
	public String execute() {
		
		ShellContext context = ShellContext.getINSTANCE();
		
		Path newPath = resolvePath(targetDirectory,context);
		
		
		if (Files.exists(newPath) && Files.isDirectory(newPath)) {
			context.setCurrentDirectory(newPath);
			return "";
		} else {
			return "cd: " + targetDirectory + ": No such file or directory";
		}
	}
	
	private Path resolvePath(String targetDirectory, ShellContext context) {
		
		if(targetDirectory.equals("~"))return Paths.get(System.getProperty("user.home"));
		
		if (targetDirectory.startsWith("~/"))
			return Paths.get(System.getProperty("user.home")).resolve(targetDirectory.substring(2)).normalize();
		
		Path path = Paths.get(targetDirectory);
		
		return  path.isAbsolute() ?
				path
				: context.getCurrentDirectory().resolve(targetDirectory).normalize();
	}
}
