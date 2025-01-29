package utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandUtils {
	
	// Get the full path of a command if it exists in PATH
	public static String findCommandInPath(String command) {
		String pathEnv = System.getenv("PATH");
		if (pathEnv == null) {
			return null;
		}
		
		List<String> executableExtensions = getExecutableExtensions();
		String[] paths = pathEnv.split(File.pathSeparator);
		
		for (String path : paths) {
			for (String ext : executableExtensions) {
				File file = new File(path, command + ext);
				if (file.exists() && file.canExecute()) {
					return file.getAbsolutePath();
				}
			}
		}
		return null;
	}
	
	// Get executable extensions (Windows uses PATHEXT, Linux/macOS don't need extensions)
	public static List<String> getExecutableExtensions() {
		String pathExt = System.getenv("PATHEXT");
		if (pathExt != null) {
			return Arrays.stream(pathExt.split(File.pathSeparator))
					.map(String::toLowerCase)
					.collect(Collectors.toList());
		}
		return List.of(""); // No extensions needed for Linux/macOS
	}
}
