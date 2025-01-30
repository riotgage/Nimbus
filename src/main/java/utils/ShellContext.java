package utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ShellContext {
	
	private static final ShellContext INSTANCE = new ShellContext();
	
	private Path currentDirectory;
	
	private ShellContext(){
		this.currentDirectory  = Paths.get(System.getProperty("user.dir"));
	}
	
	public static ShellContext getINSTANCE() {
		return INSTANCE;
	}
	
	public Path getCurrentDirectory(){
		return currentDirectory;
	}
	
	public void setCurrentDirectory(Path newDirectory) {
		this.currentDirectory = newDirectory;
	}
}
