package io.github.dynamicTrigger.DynamicTriggerCore.variable;

import io.github.dynamicTrigger.dynamicTriggerCore.variable.TempVariable;
import io.github.dynamicTrigger.dynamicTriggerCore.variable.Variable;
import io.github.dynamicTrigger.dynamicTriggerCore.variable.VariableManager;
import org.junit.Test;

import java.io.File;

/**
 * Variable System test code
 */
public class VariableTest {
    @Test
    public void StoreVariableTest() throws Exception {
        Variable var = VariableManager.getInstance().getVariable("test", "ing");
        var.set(false);
        Variable svar = VariableManager.getInstance().getVariable("tes", "ting");
        svar.set("aaa");
        File file = File.createTempFile("aaa", "bbb");
        VariableManager.getInstance().save(file);
        VariableManager.getInstance().load(file);
        assert !var.getAsBoolean();
        assert svar.getAsString().equals("aaa");
        svar.set("vbb");
        assert svar.getAsString().equals("vbb");
    }

    @Test
    public void tempVariableTest() {
        Variable var = new TempVariable("c");
        assert var.getAsBoolean();
        assert var.getAsDouble() == 1D;
        assert var.getAsString().equals("c");
        var.set(10.1D);
        assert var.getAsBoolean();
        assert var.getAsString().equals("10.1");
        assert var.getAsDouble() == 10.1D;
        var.set(false);
        assert !var.getAsBoolean();
        assert var.getAsString().equals("false");
        assert var.getAsDouble() == 5D;
        var = new TempVariable(null);
        assert !var.getAsBoolean();
        assert var.getAsString().equals("");
        assert var.getAsDouble() == 0;
    }
}
