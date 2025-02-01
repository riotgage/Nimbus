package commands;

import utils.ShellContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CdCommand implements ArgumentCommand{
	
	private List<String> arguments;
	@Override
	public Command withArguments(List<String> arguments) {
		this.arguments = arguments;
		return this;
	}
	
	@Override
	public String execute() {
		if(arguments.isEmpty()){
			return "Target directory not specified";
		}
		
		if (arguments.size() > 1) {
			return "cd: too many arguments";
		}
		
		ShellContext context = ShellContext.getINSTANCE();
		
		String targetDirectory = arguments.get(0);
		Path newPath = resolvePath(targetDirectory,context);
		
		
		if (Files.exists(newPath) && Files.isDirectory(newPath)) {
			context.setCurrentDirectory(newPath);
			return "";
		} else {
			return "cd: " + targetDirectory + ": No such file or directory";
		}
	}
	
	private Path resolvePath(String targetDirectory, ShellContext context) {
		
		String homeDir = System.getenv("HOME");  // ✅ Fetch home directory from environment
		if (homeDir == null || homeDir.isEmpty()) {
			homeDir = System.getProperty("user.home"); // Fallback in case HOME is not set
		}
		
		Path homePath = Paths.get(homeDir);
		if(targetDirectory.equals("~"))return homePath;
		
		if (targetDirectory.startsWith("~/"))
			return homePath.resolve(targetDirectory.substring(2)).normalize();
		
		Path path = Paths.get(targetDirectory);
		
		return  path.isAbsolute() ?
				path
				: context.getCurrentDirectory().resolve(targetDirectory).normalize();
	}
}
