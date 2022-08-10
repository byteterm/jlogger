package de.byteterm.jlogger.model;

import de.byteterm.jlogger.Logger;
import de.byteterm.jlogger.level.LogLevel;
import de.byteterm.jlogger.template.DefaultLogger;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;

/**
 * This Object represents a Message. Here you can set/get all important
 * information about the message you want to print. This includes the thread ID
 * in which the message was sent, from which class the message comes,
 * at which time it was created.... It is also possible to send a
 * single message as well as several messages that belong together at the same time.
 * So that one must not always create the LogObject, so that a message can be sent,
 * there are the methods info, warn... (see {@link Logger}). These methods create the object
 * automatically and set the not filled but needed fields.
 *
 * @see Logger
 * @see DefaultLogger for some examples
 *
 * @since 1.0
 * @author Niklas Tat
 */
public class LogObject implements Serializable {

    private final String message;
    private String sourceClassName;
    private String sourceMethodName;

    private final List<String> messageList;

    private final long millis;
    private long threadId;

    private LogLevel logLevel;

    public LogObject(String message, List<String> messageList, LogLevel logLevel) {
        this.message = message;
        this.sourceClassName = null;
        this.sourceMethodName = null;
        this.messageList = messageList;
        this.millis = System.currentTimeMillis();
        this.threadId = 0L;
        this.logLevel = logLevel;
    }

    /**
     * If you use this constructor you set a single message
     * and the LogLevel Info. You can change the loglevel
     * after the creation but if you want to change it here
     * use {@link LogObject(String, LogLevel)}
     *
     * @see Logger
     * @see DefaultLogger some examples
     * @see LogLevel
     *
     * @param message the single message you want to print/log
     */
    public LogObject(String message) {
        this(message, LogLevel.INFO);
    }

    /**
     * With this constructor you set a single message and the
     * LogLevel. If you want to display just an info message
     * you can use just one parameter (the message only)
     *
     * @see Logger
     * @see DefaultLogger some examples
     * @see LogLevel
     *
     * @param message the single message yoo want to print/log
     * @param logLevel the LogLevel you want to print (INFO, WARN,...)
     */
    public LogObject(String message, LogLevel logLevel) {
        this(message, null, logLevel);
    }

    /**
     * Use this if you want to print multiple message at the same time.
     * For example if you want to print a list with some information etc.
     * Here the Default log Level is info, but you can change it later. If you
     * want to change it directly just use the method {@link LogObject(List, LogLevel)}
     * Use this method only if you want to send multiple messages. If you only want to print one message
     * just use a {@link String} instant of a {@link List<String>}
     *
     * @see Logger
     * @see DefaultLogger some examples
     * @see LogLevel
     * @see List
     *
     * @param messageList all messages you want to print as List
     */
    public LogObject(List<String> messageList) {
        this(messageList, LogLevel.INFO);
    }

    /**
     * If you want to print multiple messages at the same time and want to
     * set the LogLevel directly use this method. For example if you want to
     * print a list with some information etc. Use this method only if you want
     * to send multiple messages. If you only want to print one message
     * just use a {@link String} instant of a {@link List<String>}
     *
     * @see Logger
     * @see DefaultLogger for some examples
     * @see LogLevel
     * @see List
     *
     * @param messageList all messages you want to print as List
     * @param logLevel the Level ( INFO, WARN, DEBUG...)
     */
    public LogObject(List<String> messageList, LogLevel logLevel) {
        this(null, messageList, logLevel);
    }

    /**
     * With this method you get the single Message.
     * If you want to get multiple messages use
     * the {@code getMessageList()} method.
     *
     * @return the single message as String
     */
    public String getMessage() {
        return message;
    }

    /**
     * With this method you get the class name who execute
     * the log method. With this it is easier to find
     * the problem or to identify why this message is send.
     *
     * @see Logger
     * @see DefaultLogger for some examples
     *
     * @return the class path who execute the log
     */
    public String getSourceClassName() {
        return sourceClassName;
    }

    /**
     * Using this method you get the name of the method in which
     * the log method was executed. If you want to get the full
     * class name use the {@code getSourceClassName()} method.
     *
     * @see Logger
     * @see DefaultLogger for some examples
     *
     * @return the method name in which the log was executed
     */
    public String getSourceMethodName() {
        return sourceMethodName;
    }

    /**
     * This method is to get a List of messages. For example if you
     * want to log some information about the PC-Component it is
     * better to create a Message list because of performance and
     * overview. If you only want to log a single message just use
     * the {@code getMessage()} method.
     *
     * @see Logger
     * @see DefaultLogger for some examples
     *
     * @return multiple messages as List
     */
    public List<String> getMessageList() {
        return messageList;
    }

    /**
     * With this method you get the time in which the
     * message was created as time millis. This is useful
     * to find out when something happened.
     *
     * @see Logger
     * @see DefaultLogger for some examples
     *
     * @return creation time of the message as long
     */
    public long getMillis() {
        return millis;
    }

    /**
     * If you want to get the Thread id use this.
     * With the help of this ID you can get more
     * Information about the Thread who execute the log
     * method (like name...). If you want to get the name
     * use {@code ManagementFactory.getThreadMXBean().getThreadInfo(HERE_THREAD_ID).getThreadName()}
     *
     * @see Logger
     * @see DefaultLogger for some examples
     * @see ManagementFactory
     * @see Thread
     * @see ThreadMXBean
     *
     * @return the thread id who execute the log as long
     */
    public long getThreadId() {
        return threadId;
    }

    /**
     * Use this if you want to get the Level of the log.
     * The Level is for example FATAL, INFO, WARN and defines
     * what kind of log it is. This is just to have a better overview
     * if a log is just an info message or a fatal message for example
     * the database authentication does not work.
     *
     * @see LogLevel
     * @see Logger
     * @see DefaultLogger for some examples
     *
     * @return the level of the log
     */
    public LogLevel getLevel() {
        return logLevel;
    }

    /**
     * With this method you set the class name who execute the
     * log method. If you use the {@link DefaultLogger} please don't
     * use this method. The classname is set automatically by the {@link DefaultLogger}
     * Note: Use this method only if you know what you're doing!
     *
     * @see Logger
     * @see DefaultLogger for some examples
     *
     * @param sourceClassName the full source class name who execute the log method
     */
    public void setSourceClassName(String sourceClassName) {
        this.sourceClassName = sourceClassName;
    }

    /**
     * With this method you set the method name who execute the
     * log method. If you use the {@link DefaultLogger} please don't
     * use this method. The method name is set automatically by the {@link DefaultLogger}
     * Note: Use this method only if you know what you're doing!
     *
     * @see Logger
     * @see DefaultLogger for some examples
     *
     * @param sourceMethodName the method name who execute the log method
     */
    public void setSourceMethodName(String sourceMethodName) {
        this.sourceMethodName = sourceMethodName;
    }

    /**
     * With this method you set the Thread-Id who execute the log method.
     * log method. If you use the {@link DefaultLogger} please don't
     * use this method. The Thread-Id is set automatically by the {@link DefaultLogger}
     * Note: Use this method only if you know what you're doing!
     *
     * @see Logger
     * @see DefaultLogger for some examples
     *
     * @param threadId the Thread-ID in which the log method was executed
     */
    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    /**
     * This method set the logLevel of the log message. This helps to
     * have a better overview and find faster the difference of an info message
     * or a fatal message like no database connection. If you want to know which
     * LogLevel is possible please show the {@link LogLevel} class. Instand of setting
     * the logLevel you can use log.info, log.warn, log.fatal. With this the
     * Level is set automatically.
     *
     * @see Logger
     * @see LogLevel
     * @see DefaultLogger for some examples
     *
     * @param logLevel the level of the log for better overview
     */
    public void setLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
}
