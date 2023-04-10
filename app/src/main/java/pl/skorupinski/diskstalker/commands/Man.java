package pl.skorupinski.diskstalker.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;

import pl.skorupinski.diskstalker.utils.Colors;

public class Man extends Command {
    
    private String description;
    
    private List<Command> commands;

    public Man(String description, List<Command> commands) {
        super("man");
        this.description = description;
        this.commands = commands;
    }

    @Override
    protected void execute(CommandLine cmd) {
        System.out.println("This is the manual for DiskStalker.");
        System.out.println(Colors.color(description, Colors.YELLOW));
        System.out.println("\nList of commands:\n");
        for(Command c : commands) {
            c.printHelp();
            System.out.println("");
        }

    }
    
}
