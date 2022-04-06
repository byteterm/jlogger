package de.byteterm.jlogger.model;

import de.byteterm.jlogger.level.LogLevel;

import java.io.Serializable;
import java.util.List;

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

    public LogObject(String message) {
        this(message, LogLevel.INFO);
    }

    public LogObject(String message, LogLevel logLevel) {
        this(message, null, logLevel);
    }

    public LogObject(List<String> messageList) {
        this(messageList, LogLevel.INFO);
    }

    public LogObject(List<String> messageList, LogLevel logLevel) {
        this(null, messageList, logLevel);
    }

    public String getMessage() {
        return message;
    }

    public String getSourceClassName() {
        return sourceClassName;
    }

    public void setSourceClassName(String sourceClassName) {
        this.sourceClassName = sourceClassName;
    }

    public String getSourceMethodName() {
        return sourceMethodName;
    }

    public void setSourceMethodName(String sourceMethodName) {
        this.sourceMethodName = sourceMethodName;
    }

    public List<String> getMessageList() {
        return messageList;
    }

    public long getMillis() {
        return millis;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public LogLevel getLevel() {
        return logLevel;
    }

    public void setLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
}
