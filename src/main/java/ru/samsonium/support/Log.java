package ru.samsonium.support;

public class Log {
    private static enum Color {
        reset("\033[0m"),
        
        // Regular
        regular_red     ("\033[0;31m"),
        regular_green   ("\033[0;32m"),
        regular_yellow  ("\033[0;33m"),
        regular_blue    ("\033[0;34m"),
        regular_purple  ("\033[0;35m"),
        regular_cyan    ("\033[0;36m"),
        regular_white   ("\033[0;37m"),

        // Bold
        bold_red    ("\033[1;31m"),
        bold_green  ("\033[1;32m"),
        bold_yellow ("\033[1;33m"),
        bold_blue   ("\033[1;34m"),
        bold_purple ("\033[1;35m"),
        bold_cyan   ("\033[1;36m"),
        bold_white  ("\033[1;37m"),

        // High intensity
        hi_red      ("\033[0;91m"),
        hi_green    ("\033[0;92m"),
        hi_yellow   ("\033[0;93m"),
        hi_blue     ("\033[0;94m"),
        hi_purple   ("\033[0;95m"),
        hi_cyan     ("\033[0;96m"),
        hi_white    ("\033[0;97m"),

        // Background
        bg_black    ("\033[40m"),
        bg_red      ("\033[41m"),
        bg_green    ("\033[42m"),
        bg_yellow   ("\033[43m"),
        bg_blue     ("\033[44m"),
        bg_purple   ("\033[45m"),
        bg_cyan     ("\033[46m"),
        bg_white    ("\033[47m");

        private String code;
        private Color(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    public static String colorize(String input) {
        return input.replaceAll("$r", Color.reset.toString())
            .replaceAll("$0", Color.bg_black.toString())
            .replaceAll("$1", Color.bg_red.toString())
            .replaceAll("$2", Color.bg_green.toString())
            .replaceAll("$3", Color.bg_yellow.toString())
            .replaceAll("$4", Color.bg_blue.toString())
            .replaceAll("$5", Color.bg_purple.toString())
            .replaceAll("$6", Color.bg_cyan.toString())
            .replaceAll("$7", Color.bg_white.toString())

            .replaceAll("$a", Color.regular_red.toString())
            .replaceAll("$b", Color.regular_green.toString())
            .replaceAll("$c", Color.regular_yellow.toString())
            .replaceAll("$d", Color.regular_blue.toString())
            .replaceAll("$e", Color.regular_purple.toString())
            .replaceAll("$f", Color.regular_cyan.toString())
            .replaceAll("$g", Color.regular_white.toString())
            
            .replaceAll("$a!", Color.bold_red.toString())
            .replaceAll("$b!", Color.bold_green.toString())
            .replaceAll("$c!", Color.bold_yellow.toString())
            .replaceAll("$d!", Color.bold_blue.toString())
            .replaceAll("$e!", Color.bold_purple.toString())
            .replaceAll("$f!", Color.bold_cyan.toString())
            .replaceAll("$g!", Color.bold_white.toString())

            .replaceAll("$a!!", Color.hi_red.toString())
            .replaceAll("$b!!", Color.hi_green.toString())
            .replaceAll("$c!!", Color.hi_yellow.toString())
            .replaceAll("$d!!", Color.hi_blue.toString())
            .replaceAll("$e!!", Color.hi_purple.toString())
            .replaceAll("$f!!", Color.hi_cyan.toString())
            .replaceAll("$g!!", Color.hi_white.toString());
    }

    public static void println(String message) {
        System.out.println(colorize(message));
    }
}
