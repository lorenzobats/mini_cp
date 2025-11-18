package core;

import java.util.List;

public interface Constraint {
    void post();

    void propagate();

    boolean isScheduled();

    void setScheduled(boolean scheduled);

    boolean isActive();

    void setActive(boolean active);

    List<IntVariable> getVariables();

}
