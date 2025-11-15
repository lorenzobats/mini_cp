package core;

import java.util.List;

public interface Constraint {
    List<IntVariable> getVariables();

    boolean isSatisfied(Assignment assignment);

    boolean propagate(Model mode, IntVariable var);

}
