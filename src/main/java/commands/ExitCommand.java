package commands;

public class ExitCommand implements ArgumentCommand{
	
	private String exitCode;
	@Override
	public Command withArguments(String arguments) {
		this.exitCode=arguments;
		return this;
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
