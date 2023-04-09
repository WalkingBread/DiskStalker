package pl.skorupinski.diskstalker.commands;

import java.util.List;

import org.apache.commons.cli.CommandLine;

import pl.skorupinski.diskstalker.core.FileSystem;
import pl.skorupinski.diskstalker.utils.Colors;
import pl.skorupinski.diskstalker.utils.ProgressBar;

public class Find extends Command {

    public Find() {
        super("find");
        addOption("name", "Searches for a file or directory by name.", "filename", true);
        addOption("file", "Indicates searching only for a file.", false);
        addOption("dir", "Indicates searching only for a directory.", false);
        addOption("ext", "Specifies the extension of the searched file.", "extension", false);
        addOption("path", "Searches in specified directory.", "path", false);
    }

    @Override
    protected void execute(CommandLine cmd) {
        String name = cmd.getOptionValue("name");

        boolean searchingForFile = true;
        boolean searchingForDirectory = true;

        if(cmd.hasOption("file") && !cmd.hasOption("dir")) {
            searchingForDirectory = false;
        } else if(cmd.hasOption("dir") && !cmd.hasOption("file")) {
            searchingForFile = false;
        }

        String extension = "";
        if(cmd.hasOption("ext")) {
            extension = "." + cmd.getOptionValue("ext");
        }
        name += extension;

        List<String> results = null;

        ProgressBar progress = new ProgressBar(20);

        if(cmd.hasOption("path")) {
            String directory = cmd.getOptionValue("path");
            results = FileSystem.searchFor(name, directory, searchingForFile, searchingForDirectory, progress);
        } else {
            results = FileSystem.searchEverywhereFor(name, searchingForFile, searchingForDirectory, progress);
        }
        
        for(String result : results) {
            int colorPosition = result.toLowerCase().lastIndexOf(name.toLowerCase());
            int length = name.length();
            System.out.println(Colors.color(result, colorPosition, length, Colors.GREEN));
        }
    }
    
}
