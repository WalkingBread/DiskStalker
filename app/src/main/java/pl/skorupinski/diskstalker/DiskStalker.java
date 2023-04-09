package pl.skorupinski.diskstalker;

import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.cli.*;

import pl.skorupinski.diskstalker.commands.Command;
import pl.skorupinski.diskstalker.commands.Find;
import pl.skorupinski.diskstalker.core.FileSystem;
import pl.skorupinski.diskstalker.utils.ProgressBar;

public class DiskStalker {

    public CommandLineParser parser;

    public HashMap<String, Command> commands;

    public DiskStalker() {
        parser = new DefaultParser();
        commands = new HashMap<>();
    }

    public void addCommand(Command command) {
        commands.put(command.name, command);
    }

    public void parseCommand(String[] args) {
        String cmdName = args[0];
        if(commands.containsKey(cmdName)) {
            Command command = commands.get(cmdName);
            command.executeCommand(parser, Arrays.copyOfRange(args, 1, args.length));
        }
    }

    public static void main(String[] args) {
        DiskStalker stalker = new DiskStalker();
        stalker.addCommand(new Find());

        String[] x = {"find", "-name", "filestalker", "-file", "-path", "C:/"}; 
        stalker.parseCommand(x);
    }
}
