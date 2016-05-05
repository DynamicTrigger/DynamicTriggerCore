package io.github.dynamicTrigger.dynamicTriggerCore.variable;

/**
 * For transfer Variable between Addons.
 */
public class TempVariable implements Variable {
    private String data;

    public TempVariable(String data) {
        this.data = data;
        if (this.data == null) this.data = "";
    }

    /**
     * Get variable as {@code boolean} Type.
     *
     * @return raw data if variable is {@code boolean}. {@code true} if variable isn't {@code null}. {@code false} if variable is {@code null}.
     */
    @Override
    public boolean getAsBoolean() {
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
        this.data = String.valueOf(data);
    }

    /**
     * Set {@code boolean} variable
     *
     * @param data data
     */
    @Override
    public void set(double data) {
        this.data = String.valueOf(data);
    }

    /**
     * Set {@code String} variable
     *
     * @param data data
     */
    @Override
    public void set(String data) {
        this.data = data;
        if (this.data == null) this.data = "";
    }

    /**
     * Get {@code Type} of variable
     *
     * @return {@code Type}
     */
    @Override
    public Variable.Type getType() {
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
