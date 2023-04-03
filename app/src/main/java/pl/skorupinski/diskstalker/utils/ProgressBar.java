package pl.skorupinski.diskstalker.utils;

public class ProgressBar {

    private final int maxValue;

    private int currentValue = 0;
    
    public ProgressBar(int maxValue) {
        this.maxValue = maxValue;
    }
}
