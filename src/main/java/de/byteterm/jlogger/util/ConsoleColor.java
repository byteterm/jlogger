package de.byteterm.jlogger.util;

import de.byteterm.jlogger.Logger;
import de.byteterm.jlogger.template.DefaultLogger;

/**
 * With the help of this class you can get the ansi code for the desired color.
 * This is necessary so that in the console not everything is displayed in one color,
 * but you can deposit the whole thing with color. In this class you can find
 * pretty much all colors that are supported by the console by default.
 *
 * @see Logger
 * @see DefaultLogger
 *
 * @since 1.0
 * @author Niklas Tat
 */
@SuppressWarnings("unused")
public enum ConsoleColor {

    BLACK("\u001B[30m"),
    WHITE("\u001B[37m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    BLUE("\u001B[34m"),
    YELLOW("\u001B[33m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),

    RESET("\u001B[0m"),

    MARK_BLACK("\u001B[40m"),
    MARK_WHITE("\u001B[47m"),
    MARK_RED("\u001B[41m"),
    MARK_GREEN("\u001B[42m"),
    MARK_BLUE("\u001B[44m"),
    MARK_YELLOW("\u001B[43m"),
    MARK_PURPLE("\u001B[45m"),
    MARK_CYAN("\u001B[46m"),

    BLACK_BOLD("\033[1;30m"),
    WHITE_BOLD("\033[1;37m"),
    RED_BOLD("\033[1;31m"),
    GREEN_BOLD("\033[1;32m"),
    BLUE_BOLD("\033[1;34m"),
    YELLOW_BOLD("\033[1;33m"),
    PURPLE_BOLD("\033[1;35m"),
    CYAN_BOLD("\033[1;36m");

    private final String ansiCode;

    ConsoleColor(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    @Override
    public String toString() {
        return ansiCode;
    }
}
