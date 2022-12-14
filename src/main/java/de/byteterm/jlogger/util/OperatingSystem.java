package de.byteterm.jlogger.util;

@SuppressWarnings("unused")
public enum OperatingSystem {

    WINDOWS(new String[]{"windows", "win"}),
    LINUX(new String[]{"linux", "ubuntu", "debian", "unix"}),
    MAC(new String[]{"mac", "macos", "osx", "darwin", "mac os x"}),
    SOLARIS(new String[]{"sunos"});

    private final String[] aliases;

    OperatingSystem(String[] aliases) {
        this.aliases = aliases;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public static OperatingSystem get() {
        String os = System.getProperty("os.name").toLowerCase();
        String formatted = os;
        if(os.contains(" ")) {
            String[] split = os.split(" ");
            formatted = split[0];
        }
        OperatingSystem operatingSystem = null;
        for(OperatingSystem system : values()) {
            for(String alias : system.getAliases()) {
                if (alias.contains(formatted) || alias.equalsIgnoreCase(formatted)) {
                    operatingSystem = system;
                    break;
                }
            }
        }
        return operatingSystem;
    }

    public static boolean isUsing(OperatingSystem system) {
        return get() == system;
    }
}
