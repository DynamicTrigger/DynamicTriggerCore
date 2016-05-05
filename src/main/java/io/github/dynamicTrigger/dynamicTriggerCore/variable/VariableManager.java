package io.github.dynamicTrigger.dynamicTriggerCore.variable;

import io.github.dynamicTrigger.dynamicTriggerCore.variable.impl.VariableData;

import java.io.File;

/**
 * Created by jun26 on 2016-05-05.
 */
public class VariableManager {
    private static final VariableManager INSTANCE = new VariableManager();

    private VariableManager() {
    }

    public static VariableManager getInstance() {
        return INSTANCE;
    }

    /**
     * Get {@code StoreVariable}
     *
     * @param parent $parent.code
     * @param child  $parent.code
     * @return StoreVariable
     */
    public Variable getVariable(String parent, String child) {
        return new StoreVariable(parent, child) {
        };
    }

    /**
     * Load variable from file(json type)
     *
     * @param file file
     */
    public void load(File file) {
        VariableData.getInstance().load(file);
    }

    /**
     * Save variable data to file(json type)
     *
     * @param file file
     */
    public void save(File file) {
        VariableData.getInstance().save(file);
    }
}
