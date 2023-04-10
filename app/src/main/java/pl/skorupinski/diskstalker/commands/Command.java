package pl.skorupinski.diskstalker.commands;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public abstract class Command {

    public final String name;

    private Options options;

    private HelpFormatter help;

    public Command(String name) {
        this.name = name;
        options = new Options();
        help = new HelpFormatter();
    }

    public void addOption(String name, String description, boolean required) {
        Option option = Option.builder(name)
                            .desc(description)
                            .build();
        option.setRequired(required);
        options.addOption(option);
    }

    public void addOption(String name, String description, String argName, boolean required) {
        Option option = Option.builder(name)
                            .hasArg()
                            .argName(argName)
                            .desc(description)
                            .build();
        option.setRequired(required);
        options.addOption(option);
    }

    public void addOption(String name, String description, int argNumber, String[] argNames, boolean required) {
        String args = "";
        for(int i = 0; i < argNames.length; i++) {
            args += argNames[i];
            if(i != argNames.length - 1) {
                args += "> <";
            } 
        }

        Option option = Option.builder(name)
                            .hasArg()
                            .numberOfArgs(argNumber)
                            .argName(args)
                            .desc(description)
                            .build();
        option.setRequired(required);
        options.addOption(option);
    }

    protected abstract void execute(CommandLine cmd);

    public void executeCommand(CommandLineParser parser, String[] args) {
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            help.printHelp(name, options);
        }

        if(cmd != null) {
            execute(cmd);
        }
    }

    public void printHelp() {
        help.printHelp(name, options);
    }
}
