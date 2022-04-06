package de.byteterm.jlogger.level;

import de.byteterm.jlogger.util.ConsoleColor;

/**
 * Todo: Commit this enum.
 * @since 1.0
 * @author Niklas Tat
 */
public enum LogLevel {
    DEBUG(ConsoleColor.CYAN_BOLD, "DEBUG"),
    INFO(ConsoleColor.GREEN, "INFO "),
    WARN(ConsoleColor.YELLOW, "WARN "),
    ERROR(ConsoleColor.RED, "ERROR"),
    FATAL(ConsoleColor.RED_BOLD, "FATAL"),
    EMPTY(ConsoleColor.RESET,"");

    private final ConsoleColor consoleColor;
    private final String prefix;

    LogLevel(ConsoleColor consoleColor, String prefix) {
        this.consoleColor = consoleColor;
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public ConsoleColor getColor() {
        return consoleColor;
    }
}
