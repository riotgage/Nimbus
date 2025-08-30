package commands;

import java.util.List;

public class ExitCommand implements ArgumentCommand{
	
	private String exitCode;
	@Override
	public Command withArguments(List<String> arguments) {
		this.exitCode=arguments.get(0);
		return this;
	}

	@Override
	public String validateArguments(List<String> arguments) {
		return null;
	}

	@Override
	public String execute() {
		int code = 0;
		if (exitCode != null && !exitCode.isEmpty()) {
			try {
				code = Integer.parseInt(exitCode);
			} catch (NumberFormatException e) {
				return "Invalid exit code: " + exitCode;
			}
		}
		System.exit(code);
		return "";
	}
}
