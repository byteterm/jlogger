package de.byteterm.jlogger;

import de.byteterm.jlogger.util.FileUtils;

public class Test {

    private static final Logger log = Logger.getLogger();

    public static void main(String[] args) {
        Logger.enableDebug(true);
        log.info("This is an Logger test....");
    }
}
