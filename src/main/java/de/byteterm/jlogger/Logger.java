package de.byteterm.jlogger;

import de.byteterm.jlogger.level.LogLevel;
import de.byteterm.jlogger.template.DefaultLogger;
import de.byteterm.jlogger.model.LogObject;
import de.byteterm.jlogger.util.ConsoleColor;

import java.util.List;

/**
 * This interface is the most important class in this library.
 * Here it is stored which logger is used ({@link DefaultLogger}) and
 * whether debug messages should be displayed or not. If you decide
 * to write your own logger handler, you can easily do so by implementing
 * this class. To set the new logger you have to pass the new class with
 * {@code Logger.setLogger(CLASS)}. Note that if you have used {@code Logger.getLogger()} before,
 * you will have to execute this method again. To get the logger, first create
 * the variable {@code private static final Logger logger = Logger.getLogger();}.
 * After that you can use {@code logger.info("Test Message");} as usual.
 *
 * @see DefaultLogger for some examples
 *
 * @since 1.0
 * @author Niklas Tat
 */
@SuppressWarnings("unused")
public interface Logger {

     /*
    Print
     */

    /**
     * This is the default log method. For this you need to create
     * the LogObject and fill all variables. You don't need to use this
     * method. Instand of this use the debug, info, fatal,... methods.
     * It's easier for you ;)
     * Note: Please use this method only if you know what you do!
     *
     * @see LogLevel
     *
     * @param logObject the created log object with all variables filled
     */
    void print(LogObject logObject);

    /*
    Debug
     */

    /**
     * This is the default debug log method. If you use this the LogLevel
     * is automatically debug and the message will be only display if Debug
     * Messages is enabled.
     * For this you need to create the LogObject and to fill all variables.
     * Instand of this method please use the {@code debug(String) or debug(List)} method.
     * Note: Please use this method only if you know what you do!
     *
     * @see LogLevel
     *
     * @param logObject the created LogObject with all variables filled
     */
    void debug(LogObject logObject);

    /**
     * With this method you log a single Debug message. If you want to
     * print multiple debug messages use instand of the String a List<>.
     * Notice: The log will only be displayed once you have activated
     * the debug mode with {@code Logger.enableDebug(true)}
     *
     * @see LogLevel
     *
     * @param message the single message you want to log
     */
    void debug(String message);

    /**
     * With this method you log multiple Debug messages. If you want to
     * print a single debug message use instand of List<> a String.
     * Notice: The log will only be displayed once you have activated
     * the debug mode with {@code Logger.enableDebug(true)}
     *
     * @see LogLevel
     *
     * @param messageList the messages you want to log
     */
    void debug(List<String> messageList);

    /*
    Info
     */

    /**
     * This is the default info log method. If you use this the LogLevel
     * is automatically info and the message will be displayed as info log.
     * For more Information about the level see {@link LogLevel}
     * For this you need to create the LogObject and to fill all variables.
     * Instand of this method please use the {@code info(String) or info(List)} method.
     * Note: Please use this method only if you know what you do!
     *
     * @see LogLevel
     *
     * @param logObject the created LogObject with all variables filled
     */
    void info(LogObject logObject);

    /**
     * With this method you log a single Info message. If you want to
     * print multiple info messages use instand of the String a List<>.
     *
     * @see LogLevel
     *
     * @param message the single message you want to log
     */
    void info(String message);

    /**
     * With this method you log multiple info messages. If you want to
     * print a single info message use instand of List<> a String.
     *
     * @see LogLevel
     *
     * @param messageList the messages you want to log
     */
    void info(List<String> messageList);

    /*
    WARN
     */

    /**
     * This is the default warn log method. If you use this the LogLevel
     * is automatically warn and the message will be displayed as warn log.
     * For more Information about the level see {@link LogLevel}
     * For this you need to create the LogObject and to fill all variables.
     * Instand of this method please use the {@code warn(String) or warn(List)} method.
     * Note: Please use this method only if you know what you do!
     *
     * @see LogLevel
     *
     * @param logObject the created LogObject with all variables filled
     */
    void warn(LogObject logObject);

    /**
     * With this method you log a single warn message. If you want to
     * print multiple warn messages use instand of the String a List<>.
     *
     * @see LogLevel
     *
     * @param message the single message you want to log
     */
    void warn(String message);

    /**
     * With this method you log multiple warn messages. If you want to
     * print a single warn message use instand of List<> a String.
     *
     * @see LogLevel
     *
     * @param messageList the messages you want to log
     */
    void warn(List<String> messageList);

    /*
    ERROR
     */

    /**
     * This is the default error log method. If you use this the LogLevel
     * is automatically error and the message will be displayed as error log.
     * For more Information about the level see {@link LogLevel}
     * For this you need to create the LogObject and to fill all variables.
     * Instand of this method please use the {@code error(String) error(List) or error(Throwable)} method.
     * Note: Please use this method only if you know what you do!
     *
     * @see LogLevel
     *
     * @param logObject the created LogObject with all variables filled
     */
    void error(LogObject logObject);

    /**
     * With this method you log a single error message. If you want to
     * print multiple error messages use instand of the String a List<>.
     * If you want to log an exception use {@code error(Throwable)}.
     *
     * @see LogLevel
     *
     * @param message the single message you want to log
     */
    void error(String message);

    /**
     * With this method you log multiple error messages. If you want to
     * print a single error message use instand of List<> a String.
     * If you want to log an exception use {@code error(Throwable)}
     *
     * @see LogLevel
     *
     * @param messageList the messages you want to log
     */
    void error(List<String> messageList);

    /**
     * With this message you log a Throwable as an error Message.
     * If you want to print instand of this a single message or
     * multiple message use {@code error(String)} or {@code error(List)}
     *
     * @see LogLevel
     *
     * @param throwable the messages you want to log
     */
    void error(Throwable throwable);

    /*
    FATAL
     */

    /**
     * This is the default fatal log method. If you use this the LogLevel
     * is automatically fatal and the message will be displayed as fatal log.
     * For more Information about the level see {@link LogLevel}
     * For this you need to create the LogObject and to fill all variables.
     * Instand of this method please use the {@code fatal(String) fatal(List) or fatal(Throwable)} method.
     * Note: Please use this method only if you know what you do!
     *
     * @see LogLevel
     *
     * @param logObject the created LogObject with all variables filled
     */
    void fatal(LogObject logObject);

    /**
     * With this method you log a single fatal message. If you want to
     * print multiple fatal messages use instand of the String a List<>.
     * If you want to log an exception use {@code fatal(Throwable)}.
     *
     * @see LogLevel
     *
     * @param message the single message you want to log
     */
    void fatal(String message);

    /**
     * With this method you log multiple fatal messages. If you want to
     * print a single fatal message use instand of List<> a String.
     * If you want to log an exception use {@code fatal(Throwable)}
     *
     * @see LogLevel
     *
     * @param messageList the messages you want to log
     */
    void fatal(List<String> messageList);

    /**
     * With this message you log a Throwable as a fatal Message.
     * If you want to print instand of this a single message or
     * multiple message use {@code fatal(String)} or {@code fatal(List)}
     *
     * @see LogLevel
     *
     * @param throwable the messages you want to log
     */
    void fatal(Throwable throwable);

    /*
    EMPTY
     */

    /**
     * This is the default empty log method. If you use this the LogLevel
     * is automatically empty and the message will be displayed as empty log.
     * For more Information about the level see {@link LogLevel}
     * For this you need to create the LogObject and to fill all variables.
     * Instand of this method please use the {@code empty(String) or empty(List)} method.
     * Note: Please use this method only if you know what you do!
     *
     * @see LogLevel
     *
     * @param logObject the created LogObject with all variables filled
     */
    void empty(LogObject logObject);

    /**
     * With this method you log a single empty message. If you want to
     * print multiple empty messages use instand of the String a List<>.
     *
     * @see LogLevel
     *
     * @param message the single message you want to log
     */
    void empty(String message);

    /**
     * With this method you log multiple empty messages. If you want to
     * print a single empty message use instand of List<> a String.
     *
     * @see LogLevel
     *
     * @param messageList the messages you want to log
     */
    void empty(List<String> messageList);

    /*
    List
     */

    /**
     * This is the default list method. For this you need to create
     * the LogObject with the headline. If you use the {@link DefaultLogger}
     * the list will be generated automatically with the default border char '*'.
     * With this method, several messages are provided with a border and are
     * thus better recognizable. Please don't use this method with the LogObject if
     * you don't know what you're doing. Instand of the {@link LogObject} use a
     * {@link List} wich all messages contains. You can change the color and the border Char's
     * with the other methods. In addition, the other methods calculate everything that
     * is still important so that the log can be displayed properly.
     *
     * @see LogObject
     *
     * @param logObject the created log Object with all variables filled
     * @param headline the headline of the message list
     */
    void list(LogObject logObject, String headline);

    /**
     * With this method you print a list with the default LogLevel info,
     * the default lineChar * and the default {@link ConsoleColor} white.
     * The size of the list will be generated automatically by the system
     * and the {@link ConsoleColor is the color of the border}. If you
     * want to change there default elements you can use the other list method's
     * instand.
     *
     * @param messageList the messages you want to log
     * @param headline the headline for better overview
     */
    void list(List<String> messageList, String headline);

    /**
     * With this method you print a list with the default
     * lineChar * and the default {@link ConsoleColor} white.
     * The size of the list will be generated automatically by the system
     * and the {@link ConsoleColor is the color of the border}. If you
     * want to change there default elements you can use the other list method's
     * instand. Here you set the Headline for a better overview and the LogLevel
     * to print as warn, debug or something else. If you want to print an info log
     * you can use the method {@code list(List<String> messageList, String headline)}
     *
     * @see LogLevel
     *
     * @param messageList the messages you want to log
     * @param headline the headline for better overview
     * @param logLevel the level (INFO, WARN, FATAL...)
     */
    void list(List<String> messageList, String headline, LogLevel logLevel);

    /**
     * With this method you print a list with the default LogLevel Info
     * and the default lineChar *. The size of the list will be generated
     * automatically by the system. If you want to change the LogLevel, or
     * the lineChar please use another method. Here you set the headline
     * for a better overview and the Color of the border elements.
     *
     * @see ConsoleColor
     *
     * @param messageList the messages you want to log
     * @param headline the headline for better overview
     * @param consoleColor the Color of the border
     */
    void list(List<String> messageList, String headline, ConsoleColor consoleColor);

    /**
     * With this method you print a list with the default lineChar *.
     * The size of the list will be generated automatically by the
     * system. If you want to change the lineChar please use another
     * method. With this method you set the Headline for a better overview,
     * The color of the border elements and the Level of the log like warn,
     * info, debug or something else
     *
     * @see ConsoleColor
     *
     * @param messageList the messages you want to log
     * @param headline the headline for better overview
     * @param consoleColor The color of the border elements
     * @param logLevel the level of the log (info, debug, warn,...)
     */
    void list(List<String> messageList, String headline, ConsoleColor consoleColor, LogLevel logLevel);

    /**
     * With this method you print a list with the default lineChar *.
     * If you want to change their elements please use another
     * method. For this method you need the logObject
     * with all filled variables, the headline and the {@linkplain ConsoleColor color} of the border
     * elements. Please use this method only if you know what you're doing.
     * You can use instant of the logObject a String for an easier way.
     *
     * @see LogObject
     * @see ConsoleColor
     *
     * @param logObject the logObject with all filled variables
     * @param headline the headline for better overview
     * @param consoleColor the color of the border elements
     */
    void list(LogObject logObject, String headline, ConsoleColor consoleColor);

    /**
     * With this method you print a list with default {@link ConsoleColor} white.
     * This means that the border elements are white. If you want to change this please
     * use another method. With this method you set the Headline for a better overview,
     * the char for the border and the {@linkplain LogLevel Level} of the Log.
     *
     * @see LogLevel
     * @see ConsoleColor
     *
     * @param messageList the messages you want to print
     * @param headline the headline for better overview
     * @param lineChar the char of the border elements
     * @param logLevel the level of the log (warn, info, error,...)
     */
    void list(List<String> messageList, String headline, char lineChar, LogLevel logLevel);

    /**
     * With this method you print a list with the default {@link LogLevel} info
     * and the default {@linkplain ConsoleColor color} of the border elements
     * white. If you want to change this please use another method. With this
     * method you set the headline of better overview and the char of the border
     * elements
     *
     * @see ConsoleColor
     * @see LogLevel
     *
     * @param messageList the messages you want to log
     * @param headline the headline for better overview
     * @param lineChar the char of the border elements
     */
    void list(List<String> messageList, String headline, char lineChar);

    /**
     * With this method you print a list with the default {@linkplain ConsoleColor color}
     * of the border elements white. You need for this to create a LogObject with all filled
     * variables. Please use this method only if you know what you're doing. Instant of the
     * {@link LogObject} use a {@link List} wich all messages contains. It's easier and
     * the system generated all missing elements.
     *
     * @see LogObject
     * @see ConsoleColor
     * @see List
     *
     * @param logObject the LogObject with all filled variables
     * @param headline for better overview
     * @param lineChar the char of the border elements
     */
    void list(LogObject logObject, String headline, char lineChar);

    /**
     * With this method you print a list and can configure everything
     * of the list. You can change  the headline, the char of the border
     * elements, the {@linkplain ConsoleColor color} of the border elements
     * and the {@linkplain LogLevel Level} of the Log. The size of the list
     * will be generated automatically.
     *
     * @see LogLevel
     * @see ConsoleColor
     *
     * @param messageList the messages you want to log
     * @param headline the headline for better overview
     * @param lineChar the char of the border elements
     * @param consoleColor the color of the border elements
     * @param logLevel the level of the log (warn, info, fatal...)
     */
    void list(List<String> messageList, String headline, char lineChar, ConsoleColor consoleColor, LogLevel logLevel);

    /**
     * With this method you print a list with the default {@link LogLevel} info.
     * If you want to change this please use the {@code list(List<String> messageList, String headline, char lineChar, ConsoleColor consoleColor, LogLevel logLevel)}
     * method. Here you set the Headline for better overview, the char of the border elements and the
     * {@linkplain ConsoleColor color} of the border elements
     *
     * @see ConsoleColor
     * @see LogLevel
     *
     * @param messageList the messages you want to log
     * @param headline the headline for better overview
     * @param lineChar the char of the border elements
     * @param consoleColor the color of the border elements
     */
    void list(List<String> messageList, String headline, char lineChar, ConsoleColor consoleColor);

    /**
     * With this method you print a list. For this method you need
     * to create a {@link LogObject} and fill out all variables.
     * Please use this method only if you know what you're doing.
     * Instant of this change the {@link LogObject} to a {@link List}
     * wich all messages contains.
     *
     * @see ConsoleColor
     * @see LogObject
     *
     * @param logObject with all variables filled
     * @param headline the headline for better overview
     * @param lineChar the char of the border elements
     * @param consoleColor the color of the border elements
     */
    void list(LogObject logObject, String headline, char lineChar, ConsoleColor consoleColor);

    /**
     * With this method you set the path where the logs should be stored.
     *
     * @param path where the logs should be stored
     */
    void setLogPath(String path);

    /**
     * @return the path where the logs should be stored
     */
    String getLogPath();

    /**
     * @return the Logger Handler
     */
    static Logger getLogger() {
        return Logger.Holder.logger;
    }

    /**
     * With this you set which class handles all incoming logs
     * @param logger the logger handler
     */
    static void setLogger(Logger logger) {
        Logger.Holder.logger = logger;
    }

    /**
     * @param debugMode whether to display debug messages
     */
    static void enableDebug(boolean debugMode) {
        Holder.debug = debugMode;
    }

    /**
     * @return whether to display debug messages
     */
    static Boolean isDebugEnabled() {
        return Holder.debug;
    }

    /**
     * @return whether the messages should be saved in a file or not
     */
    static boolean isLogging() {
        return Holder.log;
    }

    /**
     * @param log whether the messages should be saved in a file or not
     */
    static void setLogging(boolean log) {
        Holder.log = log;
    }

    class Holder {

        static {
            logger = new DefaultLogger();
            debug = false;
            log = true;
        }

        private static Logger logger;

        private static boolean debug;

        private static boolean log;
    }
}
