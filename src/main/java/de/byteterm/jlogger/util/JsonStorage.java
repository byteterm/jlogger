package de.byteterm.jlogger.util;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public record JsonStorage(String name, Map<String, Object> objects) {

    public JsonStorage(@NotNull String name, @NotNull Map<String, Object> objects) {
        this.name = name;
        this.objects = objects;
    }
}
