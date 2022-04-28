package de.byteterm.jlogger.util;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public record FileStorage(String name, Map<String, Object> objects) {

    public FileStorage(@NotNull String name, @NotNull Map<String, Object> objects) {
        this.name = name;
        this.objects = objects;
    }
}
