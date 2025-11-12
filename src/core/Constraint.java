package core;

import java.util.List;

public interface Constraint {
    List<IntVariable> getVariables();
    boolean isSatisfied();
    boolean propagate(Model mode, IntVariable var);

}
