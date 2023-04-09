package pl.skorupinski.diskstalker.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pl.skorupinski.diskstalker.utils.ProgressBar;

public class FileSystem {
    
    private static List<String> search(String name, String directory, boolean files, boolean dirs, ProgressBar bar) {
        if(bar != null) {
            bar.addProgress(1);
            bar.printProgressBar(true);
        }
        List<String> results = new ArrayList<>();
        String[] elements = new File(directory).list();
        if(elements == null) {
            return results;
        }
        for(String e : elements) {
            File element = new File(directory + "/" + e);
            if(e.toUpperCase().contains(name.toUpperCase())) {
                if((element.isDirectory() && dirs) || (element.isFile() && files)) {
                    results.add(element.getAbsolutePath());
                } 
            }
            if(element.isDirectory()) {
                results.addAll(search(name, element.getAbsolutePath(), files, dirs, bar));
            }
        }
        return results;
    }

    public static List<String> searchFor(String name, String directory, boolean files, boolean dirs, ProgressBar bar) {
        if(bar != null) {
            bar.setMaxValue(getNumberOfSubdirectories(directory));
            bar.setLeftSideMessage("Searching...");
        }
        return search(name, directory, files, dirs, bar);
    }

    public static List<String> searchEverywhereFor(String name, boolean files, boolean dirs, ProgressBar bar) {
        if(bar != null) {
            bar.setMaxValue(getNumberOfSubdirectories());
            bar.setLeftSideMessage("Searching...");
        }
        List<String> results = new ArrayList<>();
        File[] roots = File.listRoots();
        for(File drive : roots) {
            String directory = drive.getAbsolutePath();
            results.addAll(search(name, directory, files, dirs, bar));
        }
        return results;
    }

    public static int getNumberOfSubdirectories() {
        int numberOfSubdirs = 0;
        File[] roots = File.listRoots();
        for(File drive : roots) {
            String directory = drive.getAbsolutePath();
            numberOfSubdirs += getNumberOfSubdirectories(directory);
        }
        return numberOfSubdirs;
    }

    public static int getNumberOfSubdirectories(String directory) {
        File dir = new File(directory);
        String[] elements = dir.list();
        int numberOfSubdirs = 0;
        if(elements == null) {
            return 0;
        }
        for(String e : elements) {
            String path = directory + "/" + e;
            File element = new File(path);
            if(element.isDirectory()) {
                numberOfSubdirs++;
                numberOfSubdirs += getNumberOfSubdirectories(path);
            }
        }
        return numberOfSubdirs;
    }

}
