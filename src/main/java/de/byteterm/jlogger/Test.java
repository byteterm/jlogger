package de.byteterm.jlogger;

public class Test {

    private static final Logger log = Logger.getLogger();

    public static void main(String[] args) {
        Logger.enableDebug(true);
        log.debug("This is an Logger test...");
    }
}
