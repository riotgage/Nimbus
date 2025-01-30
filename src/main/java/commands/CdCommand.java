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
		
		Path newPath = Paths.get(targetDirectory).isAbsolute() ?
				Paths.get(targetDirectory)
				: context.getCurrentDirectory().resolve(targetDirectory).normalize();
		
		if (Files.exists(newPath) && Files.isDirectory(newPath)) {
			context.setCurrentDirectory(newPath);
			return "";
		} else {
			return "cd: " + targetDirectory + ": No such file or directory";
		}
	}
}
