package de.byteterm.jlogger;

import de.byteterm.jlogger.level.LogLevel;
import de.byteterm.jlogger.template.DefaultLogger;
import de.byteterm.jlogger.model.LogObject;
import de.byteterm.jlogger.util.ConsoleColor;

import java.util.List;

/**
 * Todo: commit what this interface doing...
 * @since 1.0
 * @author Niklas Tat
 */
public interface Logger {

     /*
    Print
     */

    void print(LogObject logObject);

    /*
    Debug
     */

    void debug(LogObject logObject);

    void debug(String message);

    void debug(List<String> messageList);

    /*
    Info
     */

    void info(LogObject logObject);

    void info(String message);

    void info(List<String> messageList);

    /*
    WARN
     */

    void warn(LogObject logObject);

    void warn(String message);

    void warn(List<String> messageList);

    /*
    ERROR
     */

    void error(LogObject logObject);

    void error(String message);

    void error(List<String> messageList);

    void error(Throwable throwable);

    /*
    FATAL
     */

    void fatal(LogObject logObject);

    void fatal(String message);

    void fatal(List<String> messageList);

    void fatal(Throwable throwable);

    /*
    EMPTY
     */

    void empty(LogObject logObject);

    void empty(String message);

    void empty(List<String> messageList);

    /*
    List
     */

    void list(List<String> messageList, String headline);

    void list(List<String> messageList, String headline, LogLevel logLevel);

    void list(List<String> messageList, String headline, ConsoleColor consoleColor);

    void list(List<String> messageList, String headline, ConsoleColor consoleColor, LogLevel logLevel);

    void list(LogObject logObject, String headline, ConsoleColor consoleColor);

    void list(LogObject logObject, String headline);

    void list(List<String> messageList, String headline, char lineChar, LogLevel logLevel);

    void list(List<String> messageList, String headline, char lineChar);

    void list(LogObject logObject, String headline, char lineChar);

    void list(List<String> messageList, String headline, char lineChar, ConsoleColor consoleColor, LogLevel logLevel);

    void list(List<String> messageList, String headline, char lineChar, ConsoleColor consoleColor);

    void list(LogObject logObject, String headline, char lineChar, ConsoleColor consoleColor);

    static Logger getLogger() {
        return Logger.Holder.logger;
    }

    static void setLogger(Logger logger) {
        Logger.Holder.logger = logger;
    }

    static void enableDebug(boolean debugMode) {
        Holder.debug = debugMode;
    }

    static Boolean isDebugEnabled() {
        return Holder.debug;
    }

    class Holder {

        static {
            logger = new DefaultLogger();
            debug = false;
        }

        private static Logger logger;

        private static boolean debug;
    }
}
