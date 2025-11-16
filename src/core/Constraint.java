package core;

import java.util.List;
import java.util.Map;

public interface Constraint {
    List<IntVariable> getVariables();

    boolean isSatisfied(Assignment assignment);

    boolean propagate(Assignment assignment, Map<IntVariable, Domain> domains);

}
