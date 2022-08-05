package de.byteterm.jlogger;

import de.byteterm.jlogger.exception.TestException;
import de.byteterm.jlogger.level.LogLevel;
import de.byteterm.jlogger.util.ConsoleColor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;

class LoggerTest {

    private static final Logger log = Logger.getLogger();
    private final List<String> messageList = Arrays.asList("Lorem ipsum dolor sit amet", "labore et dolore magna", "Excepteur sint occaecat cupidatat non", "sunt in culpa qui officia");

    @Test
    void testSingleFatalMessage() {
        log.empty(" ");
        log.fatal("Single Message test ( Fatal )");
        log.empty(" ");
    }

    @Test
    void testFatalMessageList() {
        log.empty(" ");
        log.fatal(messageList);
        log.empty(" ");
    }

    @Test
    void testFatalThrowableMessage() {
        log.empty(" ");
        log.fatal(new TestException("This is an generated Exception to test"));
        log.empty(" ");
    }

    @Test
    void testList() {
        log.empty(" ");
        log.list(messageList, "Test List", ConsoleColor.CYAN, LogLevel.WARN);
        log.empty(" ");
    }

    @Test
    void testListSameTime() {
        new Thread(() -> {
            log.empty(" ");
            log.list(messageList, "Thread One", ConsoleColor.CYAN, LogLevel.WARN);
            log.empty(" ");
        }).start();

        new Thread(() -> {
            log.empty(" ");
            log.list(messageList, "Thread Two", ConsoleColor.CYAN, LogLevel.WARN);
            log.empty(" ");
        }).start();
    }
}