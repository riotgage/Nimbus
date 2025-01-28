import commands.ArgumentCommand;
import commands.Command;
import commands.CommandFactory;

public class CommandProcessor {
	
	private final CommandFactory commandFactory;
	
	public CommandProcessor(CommandFactory commandFactory) {
		this.commandFactory = commandFactory;
	}
	
	public String process(String input) {
		// Split input into command and arguments
		// As of now only one param can be passed
		// e.g. echo Hello
		String[] parts = input.trim().split("\\s+", 2);
		String commandName = parts[0];
		String arguments = parts.length > 1 ? parts[1] : null;
		
		Command command = commandFactory.createCommand(commandName);
		
		if(command==null){
			return commandName+": command not found";
		}
		else if (command instanceof ArgumentCommand argumentCommand) {
			if (arguments == null) {
				throw new IllegalArgumentException("Missing arguments for command: " + commandName);
			}
			command = argumentCommand.withArguments(arguments);
		}
		
		return command.execute();
	}
}
