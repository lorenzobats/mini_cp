package core;

import state.StateStack;

public class IntVariable {
    private final Solver solver;
    private final Domain domain;
    private final String name;
    private final StateStack<Constraint> onDomain;
    private final StateStack<Constraint> onBounds;
    private final StateStack<Constraint> onFix;

    private final DomainListener domainListener = new DomainListener() {
        @Override
        public void empty() {
            throw new InconsistencyException("Empty IntVariable");
        }

        @Override
        public void fix() {
            scheduleAll(onFix);
        }

        @Override
        public void change() {
            scheduleAll(onDomain);
        }

        @Override
        public void minChanged() {
            scheduleAll(onBounds);
        }

        @Override
        public void maxChanged() {
            scheduleAll(onBounds);
        }
    };

    // only interval domains
    public IntVariable(String name, Solver solver, int min, int max) {
        this.solver = solver;
        this.name = name;

        domain = new SparseSetDomain(solver.getStateManager(), min, max);
        onDomain = new StateStack<>(solver.getStateManager());
        onBounds = new StateStack<>(solver.getStateManager());
        onFix = new StateStack<>(solver.getStateManager());
    }

    protected void scheduleAll(StateStack<Constraint> constraints) {
        for (int i = 0; i < constraints.size(); i++) {
            solver.schedule(constraints.get(i));
        }
    }

    public String getName() {
        return name;
    }

    public Solver getSolver() {
        return solver;
    }

    public int min() {
        return domain.min();
    }

    public int max() {
        return domain.max();
    }

    public int size() {
        return domain.size();
    }

    /**
     * Returns true, if there is only one value left in the domain
     */
    public boolean isFixed() {
        return domain.isSingleton();

    }

    public boolean contains(int i) {
        return domain.contains(i);
    }

    public void remove(int i) {
        if (domain.contains(i)) {
            domain.remove(i, domainListener);
        }

    }

    public void fix(int i) {
        if (!domain.contains(i)) {
            throw new InconsistencyException("Cannot fix value" + i);
        }

        if (!domain.isSingleton() || domain.min() != i) {
            domain.fix(i, domainListener);
        }

    }

    public void removeBelow(int i) {
        domain.removeBelow(i, domainListener);
    }

    public void propagateOnDomainChange(Constraint c) {
        onDomain.push(c);
    }

    public void propagateOnBoundChange(Constraint c) {
        onBounds.push(c);
    }

    public void propagateOnFix(Constraint c) {
        onFix.push(c);
    }

}
