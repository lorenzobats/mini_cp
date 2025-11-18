package core;

import state.StateManager;

public interface Solver {
    StateManager getStateManager();

    void post(Constraint c);

    void post(Constraint c, boolean enforceFixPoint);

    void schedule(Constraint c);

    void fixPoint();
}
