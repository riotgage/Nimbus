import commands.*;

import java.util.ArrayList;
import java.util.List;

public class CommandProcessor {
	
	private final CommandFactory commandFactory;
	
	public CommandProcessor(CommandFactory commandFactory, CommandRegistry commandRegistry) {
		this.commandFactory = commandFactory;
	}
	
	public String process(String input) {

		// Split input into command and arguments
		// Lets create a list of args so we can handle single quotes and double quotes efficiently
		
		List<String> tokens = tokenizeInput(input);
		
		if(tokens.isEmpty())return null;
		
		String commandName = tokens.get(0);
		List<String> arguments = tokens.subList(1,tokens.size());
		
		Command command = commandFactory.createCommand(commandName);
		
		if(command==null){
			return commandName+": command not found";
		}

		if (command instanceof ArgumentCommand argumentCommand) {

			// Validate arguments
			String validationError = argumentCommand.validateArguments(arguments);
			if (validationError != null) {
				return validationError;
			}
			if (command instanceof ExternalCommand){
				command = ((ExternalCommand) command).withArguments(tokens);
			}
			else{
				command = argumentCommand.withArguments(arguments);
			}

		}
		
		return command.execute();
	}
	
	private List<String> tokenizeInput(String input) {
		
		List<String> tokens = new ArrayList<>();
		StringBuilder currentToken = new StringBuilder();
		boolean inSingleQuote = false;
		
		for (char ch: input.toCharArray()){
			if (ch=='\''){
				inSingleQuote = !inSingleQuote;
				continue;
			}
			
			if(Character.isWhitespace(ch) && !inSingleQuote){
				if (currentToken.length()>0){
					tokens.add(currentToken.toString());
					currentToken.setLength(0);
				}
			}else{
				currentToken.append(ch);
			}
		}
		
		if (currentToken.length()>0){
			tokens.add(currentToken.toString());
		}
		
		return tokens;
	}

}
