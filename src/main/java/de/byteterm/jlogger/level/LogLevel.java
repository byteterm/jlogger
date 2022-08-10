package de.byteterm.jlogger.level;

import de.byteterm.jlogger.Logger;
import de.byteterm.jlogger.model.LogObject;
import de.byteterm.jlogger.template.DefaultLogger;
import de.byteterm.jlogger.util.ConsoleColor;

/**
 * All available log levels for the console/log output are listed here.
 * With the help of this list it is possible to see directly
 * what kind of message it is (Just an info Message or an important error/notification). In addition, it is also
 * stored here in which color the prefix is displayed and
 * what the prefix to the selected level is called. The LogLevel
 * is needed for each message and is set either by the system itself in
 * the {@link LogObject} or manually with the {@linkplain Logger log.list(...)} method.
 *
 * @see LogObject
 * @see Logger
 * @see DefaultLogger for some examples
 *
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

    /**
     * This method returns the prefix for the chosen Level
     * to identify in the log files or in the chat what kind of
     * message it displays. (An important or just an info message)
     *
     * @see Logger
     * @see DefaultLogger here you can find some examples
     *
     * @return the prefix for the chosen log Level String
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * With this method you get the Ansi color code to display
     * the message with some color. This helps to identify faster
     * what kind of message is printed.
     *
     * @see Logger
     * @see ConsoleColor
     * @see DefaultLogger for some examples
     *
     * @return the color code as Ansi code
     */
    public ConsoleColor getColor() {
        return consoleColor;
    }
}
