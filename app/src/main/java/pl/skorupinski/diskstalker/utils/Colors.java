package pl.skorupinski.diskstalker.utils;

public enum Colors {
    BLACK("\033[0;30m"),
    RED("\033[0;31m"),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    BLUE("\033[0;34m"),
    PURPLE("\033[0;35m"),
    CYAN("\033[0;36m"),
    WHITE("\033[0;37m");

    private static final String RESET = "\033[0m";

    private final String code;

    private Colors(String code) {
        this.code = code;
    }

    public static String color(String text, Colors color) {
        return color.code + text + RESET;
    }

    public static String color(String text, int begin, int length, Colors color) {
        String firstPart = text.substring(0, begin);
        String toColor = text.substring(begin, begin + length);
        String lastPart = text.substring(begin + length);

        return firstPart + color.code + toColor + RESET + lastPart;
    }
}
