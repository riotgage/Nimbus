package commands;

import java.util.List;

public interface ArgumentCommand extends Command{
	Command withArguments(List<String> arguments);
	String validateArguments(List<String> arguments);
}
