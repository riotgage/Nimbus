package commands;

import utils.ShellContext;

public class PwdCommand implements Command{
	
	@Override
	public String execute() {
		return ShellContext.getINSTANCE().getCurrentDirectory().toString();
	}
}
