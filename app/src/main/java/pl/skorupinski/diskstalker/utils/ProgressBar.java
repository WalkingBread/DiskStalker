package pl.skorupinski.diskstalker.utils;

import java.text.DecimalFormat;

public class ProgressBar {

    private int maxValue = 1;

    private int currentValue = 0;
    
    private int charLength;

    private String leftSideMessage = "";

    private String rightSideMessage = "";

    private static final char EMPTY = '-';

    private static final char FULL = '#';

    private static final String BOUNDS = "[]";
    
    public ProgressBar(int charLength) {
        this.charLength = charLength;
    }

    public void setProgress(int value) {
        currentValue = value;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void addProgress(int value) {
        currentValue += value;
    }

    public void setLeftSideMessage(String message) {
        leftSideMessage = message;
    }

    public void setRightSideMessage(String message) {
        rightSideMessage = message;
    }

    public void printProgressBar(boolean showPercent) {
        String bar = "\r" + leftSideMessage + " ";
        float progress = 1;
        if(maxValue != 0) {
            progress = (float) currentValue / (float) maxValue;
        }
        int fullCells = (int) Math.ceil(progress * charLength); 
        int emptyCells = charLength - fullCells;
        if(showPercent) {
            DecimalFormat f = new DecimalFormat("##.00");
            bar += String.valueOf(f.format(progress * 100)) + "% ";
        }
        bar += BOUNDS.charAt(0);
        for(int i = 0; i < fullCells; i++) {
            bar += FULL;
        }
        for(int i = 0; i < emptyCells; i++) {
            bar += EMPTY;
        }
        bar += BOUNDS.charAt(1);
        bar += " " + rightSideMessage;
        if(currentValue >= maxValue) {
            System.out.print("\r\n");
        } else {
            System.out.print(bar);
        }
    }

}
