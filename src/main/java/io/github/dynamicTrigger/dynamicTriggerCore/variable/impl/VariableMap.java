package io.github.dynamicTrigger.dynamicTriggerCore.variable.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import io.github.dynamicTrigger.dynamicTriggerCore.DynamicTrigger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Collection class that design for DynamicTrigger's variable system.
 * DO NOT use this class directly. Use {@code VariableManager} or {@code ArrayManager} instead
 *
 * @see io.github.dynamicTrigger.dynamicTriggerCore.variable.VariableManager
 */
class VariableMap<P, C, D> {
    private ConcurrentHashMap<P, ConcurrentHashMap<C, D>> data = new ConcurrentHashMap<>();

    VariableMap(File file) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            Gson gson = new Gson();
            Type type = new TypeToken<ConcurrentHashMap<P, ConcurrentHashMap<C, D>>>() {
            }.getType();
            data = gson.fromJson(reader, type);
        } catch (IOException e) {
            DynamicTrigger.out.println("Error occured while loading variable data");
        }
    }

    VariableMap() {

    }

    void add(P parent, C child, D data) {
        ConcurrentHashMap<C, D> node = getNode(parent);
        node.put(child, data);
    }

    void remove(P parent, C child) {
        ConcurrentHashMap<C, D> node = data.get(parent);
        if (node == null) return;
        node.remove(child);
    }

    D get(P parent, C child) {
        ConcurrentHashMap<C, D> node = getNode(parent);
        D data = node.get(child);
        if (data == null) return null;
        return data;
    }

    private ConcurrentHashMap<C, D> getNode(P parent) {
        ConcurrentHashMap<C, D> node = this.data.get(parent);
        if (node == null) {
            node = new ConcurrentHashMap<>();
            this.data.put(parent, node);
        }
        return node;
    }

    void save(File file) {
        try {
            boolean result = false;
            if (!file.exists()) result = file.createNewFile();
            if (!result) {
                throw new IOException();
            }
        } catch (IOException e) {
            DynamicTrigger.out.println("Cannot create new file");
            e.printStackTrace(DynamicTrigger.out);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(data);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            DynamicTrigger.out.println("Error occured while saving variable data");
            e.printStackTrace(DynamicTrigger.out);
        }
    }
}
