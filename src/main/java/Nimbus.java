import commands.CommandFactory;
import io.ConsoleInputHandler;
import io.ConsoleOutputHandler;

public class Nimbus {
    
    private final CommandProcessor commandProcessor;
    
    private final ConsoleInputHandler inputHandler;
    private final ConsoleOutputHandler outputHandler;
    private final String exitCommand;
    
    public void start() {
        while (true) {
            String input = inputHandler.readInput();
            if (exitCommand.equalsIgnoreCase(input)) {
                break;
            }
            try {
                String result = commandProcessor.process(input);
                outputHandler.printOutput(result);
            } catch (IllegalArgumentException e) {
                outputHandler.printOutput("Error: " + e.getMessage());
            }
        }
    }
    
    
    public Nimbus(CommandProcessor commandProcessor, ConsoleInputHandler inputHandler, ConsoleOutputHandler outputHandler,
            String exitCommand) {
        this.commandProcessor = commandProcessor;
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.exitCommand = exitCommand;
    }
    
    public static void main(String[] args){
        CommandFactory commandFactory = new CommandFactory();
        CommandProcessor commandProcessor = new CommandProcessor(commandFactory);
        ConsoleInputHandler inputHandler = new ConsoleInputHandler();
        ConsoleOutputHandler outputHandler = new ConsoleOutputHandler();
    
        Nimbus nimbus = new Nimbus(commandProcessor, inputHandler, outputHandler, "exit");
        nimbus.start();
    }
}
