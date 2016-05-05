package io.github.dynamicTrigger.dynamicTriggerCore.variable;

import io.github.dynamicTrigger.dynamicTriggerCore.variable.impl.VariableData;

/**
 * You can access to variable by this class.
 * This class can't be created directly, use {@code VariableManager}
 *
 * @see VariableManager
 * @see TempVariable
 */
public abstract class StoreVariable implements Variable {
    private String parent, child;

    public StoreVariable(String parent, String child) {
        this.parent = parent;
        this.child = child;
    }

    /**
     * Get variable as {@code boolean} Type.
     *
     * @return raw data if variable is {@code boolean}. {@code true} if variable isn't {@code null}. {@code false} if variable is {@code null}.
     */
    @Override
    public boolean getAsBoolean() {
        String data = VariableData.getInstance().get(parent, child);
        if (data.equalsIgnoreCase("true") || data.equalsIgnoreCase("false")) return data.equalsIgnoreCase("true");
        return !(data == null || data.isEmpty());
    }

    /**
     * Get variable as {@code double} Type.
     *
     * @return raw data if variable is {@code double}. length of data otherwise. For example, {@code Abcde} return 5.
     */
    @Override
    public double getAsDouble() {
        String data = VariableData.getInstance().get(parent, child);
        try {
            return Double.parseDouble(data);
        } catch (Exception e) {
            return data.length();
        }
    }

    /**
     * Get variable as {@code String} Type.
     *
     * @return raw data
     */
    @Override
    public String getAsString() {
        String data = VariableData.getInstance().get(parent, child);
        return data;
    }

    /**
     * Get variable as {@code Type t}
     *
     * @param t return type
     * @return data casted to {@code Type t}
     */
    @Override
    public Object get(Variable.Type t) {
        if (t == Variable.Type.BOOLEAN) return getAsBoolean();
        if (t == Variable.Type.DOUBLE) return getAsDouble();
        if (t == Variable.Type.STRING) return getAsString();
        return null;
    }

    /**
     * Set {@code double} variable
     *
     * @param data data
     */
    @Override
    public void set(boolean data) {
        VariableData.getInstance().set(parent, child, String.valueOf(data));
    }

    /**
     * Set {@code boolean} variable
     *
     * @param data data
     */
    @Override
    public void set(double data) {
        VariableData.getInstance().set(parent, child, String.valueOf(data));
    }

    /**
     * Set {@code String} variable
     *
     * @param data data
     */
    @Override
    public void set(String data) {
        if (data == null) data = "";
        VariableData.getInstance().set(parent, child, data);
    }

    /**
     * Get {@code Type} of variable
     *
     * @return {@code Type}
     */
    @Override
    public Variable.Type getType() {
        String data = VariableData.getInstance().get(parent, child);
        if (data.equalsIgnoreCase("true") || data.equalsIgnoreCase("false")) return Variable.Type.BOOLEAN;
        char[] c = data.toCharArray();
        boolean isDotted = false;
        for (int i = 0; i < data.length(); i++) {
            if (c[i] == '.') {
                if (isDotted) {
                    return Variable.Type.STRING;
                }
                isDotted = true;
                continue;
            }
            if (c[i] != '0' && c[i] != '1' && c[i] != '2' && c[i] != '3' && c[i] != '4' && c[i] != '5' && c[i] != '6' && c[i] != '7' && c[i] != '8' && c[i] != '9')
                return Variable.Type.STRING;
        }
        return Variable.Type.DOUBLE;
    }
}
