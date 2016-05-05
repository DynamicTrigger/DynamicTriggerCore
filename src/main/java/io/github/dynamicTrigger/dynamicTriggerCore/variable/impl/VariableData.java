package io.github.dynamicTrigger.dynamicTriggerCore.variable.impl;

import java.io.File;

/**
 * Saving the Variable Data. DO NOT access to this class directly.
 * Use {@code VariableManager} and {@code StoreVariable} instead
 *
 * @see io.github.dynamicTrigger.dynamicTriggerCore.variable.VariableManager
 * @see io.github.dynamicTrigger.dynamicTriggerCore.variable.StoreVariable
 */
public class VariableData {
    private static final VariableData INSTANCE = new VariableData();
    private VariableMap<String, String, String> data = new VariableMap<>();

    public static VariableData getInstance() {
        return INSTANCE;
    }

    public String get(String parent, String child) {
        String d = data.get(parent, child);
        if (d == null) return "";
        return d;
    }

    public void set(String parent, String child, String data) {
        this.data.add(parent, child, data);
    }

    public void save(File file) {
        data.save(file);
    }

    public void load(File file) {
        data = new VariableMap<>(file);
    }
}
