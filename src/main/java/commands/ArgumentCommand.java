package commands;

public interface ArgumentCommand extends Command{
	Command withArguments(String arguments);

}
