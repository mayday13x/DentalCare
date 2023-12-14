package com.example.dentalcare.colors;

public class Colors {

    //font-color
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    //bg-color

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void printRed(String msg){
        System.out.println(ANSI_RED + msg + ANSI_RESET);
    }
    public static void printGreen(String msg){
        System.out.println(ANSI_GREEN + msg + ANSI_RESET);
    }

    public static void printYellow(String msg) {
        System.out.println(ANSI_YELLOW + msg + ANSI_RESET);
    }

    public static void printBlue(String msg) {
        System.out.println(ANSI_BLUE + msg + ANSI_RESET);
    }

    public static void printPurple(String msg) {
        System.out.println(ANSI_PURPLE + msg + ANSI_RESET);
    }

    public static void printCyan(String msg) {
        System.out.println(ANSI_CYAN + msg + ANSI_RESET);
    }

    //bg-colors

    public static void printRedBackground(String msg) {
        System.out.println(ANSI_RED_BACKGROUND + ANSI_WHITE + msg + ANSI_RESET);
    }

    public static void printGreenBackground(String msg) {
        System.out.println(ANSI_GREEN_BACKGROUND + ANSI_WHITE + msg + ANSI_RESET);
    }

    public static void printYellowBackground(String msg) {
        System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_WHITE + msg + ANSI_RESET);
    }

    public static void printBlueBackground(String msg) {
        System.out.println(ANSI_BLUE_BACKGROUND + ANSI_WHITE + msg + ANSI_RESET);
    }

    public static void printPurpleBackground(String msg) {
        System.out.println(ANSI_PURPLE_BACKGROUND + ANSI_WHITE + msg + ANSI_RESET);
    }

    public static void printCyanBackground(String msg) {
        System.out.println(ANSI_CYAN_BACKGROUND + ANSI_WHITE + msg + ANSI_RESET);
    }


}
