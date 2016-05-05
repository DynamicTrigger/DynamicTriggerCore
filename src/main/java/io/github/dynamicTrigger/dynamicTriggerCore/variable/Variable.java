package io.github.dynamicTrigger.dynamicTriggerCore.variable;

/**
 * Variable construct.
 */
public interface Variable {
    /**
     * Get variable as {@code boolean} Type.
     *
     * @return raw data if variable is {@code boolean}. {@code true} if variable isn't {@code null}. {@code false} if variable is {@code null}.
     */
    boolean getAsBoolean();

    /**
     * Get variable as {@code double} Type.
     *
     * @return raw data if variable is {@code double}. length of data otherwise. For example, {@code Abcde} return 5.
     */
    double getAsDouble();

    /**
     * Get variable as {@code String} Type.
     *
     * @return raw data
     */
    String getAsString();

    /**
     * Get variable as {@code Type t}
     *
     * @param t return type
     * @return data casted to {@code Type t}
     */
    Object get(Type t);

    /**
     * Set {@code double} variable
     *
     * @param data data
     */
    void set(double data);

    /**
     * Set {@code boolean} variable
     *
     * @param data data
     */
    void set(boolean data);

    /**
     * Set {@code String} variable
     *
     * @param data data
     */
    void set(String data);

    /**
     * Get {@code Type} of variable
     *
     * @return {@code Type}
     */
    Type getType();

    enum Type {
        STRING, DOUBLE, BOOLEAN
    }
}
