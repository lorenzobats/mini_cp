package constraints;

import core.Assignment;
import core.Constraint;
import core.IntVariable;
import core.Model;

import java.util.List;

public class NotEqual implements Constraint {
    private IntVariable var1;
    private IntVariable var2;

    public NotEqual(IntVariable var1, IntVariable var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    public boolean isSatisfied(Assignment assignment) {
        Integer value1 = assignment.getValue(var1);
        Integer value2 = assignment.getValue(var2);
        if (value1 == null || value2 == null)
            return true;

        return !value1.equals(value2);
    }

    @Override
    public List<IntVariable> getVariables() {
        return List.of();
    }

    @Override
    public boolean isSatisfied() {
        return var1.get;
    }

    @Override
    public boolean propagate(Model model, IntVariable var) {
        return false;
    }
}
