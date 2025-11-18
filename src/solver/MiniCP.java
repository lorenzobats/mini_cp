package solver;

import core.Constraint;
import core.InconsistencyException;
import core.Procedure;
import core.Solver;
import state.StateManager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MiniCP implements Solver {
    private final Queue<Constraint> propagationQueue = new ArrayDeque<>();
    private final StateManager stateManager;
    private final List<Procedure> fixPointListener = new ArrayList<>();

    public MiniCP(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void schedule(Constraint constraint) {
        if (constraint.isActive() && !constraint.isScheduled()) {
            constraint.setScheduled(true);
            propagationQueue.add(constraint);
        }
    }

    public void propagate(Constraint constraint) {
        constraint.setScheduled(false);
        if (constraint.isActive()) {
            constraint.propagate();
        }
    }

    @Override
    public void fixPoint() {
        try {
            while (!propagationQueue.isEmpty()) {
                propagate(propagationQueue.remove());
            }

            for (Procedure p : fixPointListener) {
                p.call();
            }
        } catch (InconsistencyException e) {
            while (!propagationQueue.isEmpty()) {
                propagationQueue.remove().setScheduled(false);
            }
            throw e;
        }

    }

    @Override
    public StateManager getStateManager() {
        return this.stateManager;
    }

    @Override
    public void post(Constraint c) {
        post(c, true);
    }

    @Override
    public void post(Constraint c, boolean propagate) {
        c.post();
        if (propagate) {
            fixPoint();
        }
    }

    public void pushState() {
        stateManager.saveState();
    }

    public void restoreState() {
        stateManager.restoreState();
    }
}
