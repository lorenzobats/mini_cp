package constraints;

import core.Constraint;
import core.IntVariable;
import core.Solver;
import state.State;

import java.util.List;

public abstract class AbstractConstraint implements Constraint {
    private final State<Boolean> active;
    private final Solver solver;
    private boolean scheduled = false;

    public AbstractConstraint(Solver solver) {
        this.solver = solver;
        active = solver.getStateManager().makeStateBoolean(true);
    }

    public Solver getSolver() {
        return solver;
    }

    @Override
    public void post() {

    }

    @Override
    public void propagate() {
    }

    @Override
    public boolean isScheduled() {
        return this.scheduled;
    }

    @Override
    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;

    }

    @Override
    public boolean isActive() {
        return active.value();
    }

    @Override
    public void setActive(boolean active) {
        this.active.setValue(active);
    }

    @Override
    public abstract List<IntVariable> getVariables();

}
