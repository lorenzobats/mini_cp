package core;

import state.StateManager;
import state.StateSparseSet;

public class SparseSetDomain implements Domain {
    private final StateSparseSet domain;


    public SparseSetDomain(StateManager sm, int min, int max) {
        domain = new StateSparseSet(sm, max - min + 1, min);
    }

    @Override
    public int min() {
        return domain.min();
    }

    @Override
    public int max() {
        return domain.max();
    }

    @Override
    public int size() {
        return domain.size();
    }

    @Override
    public boolean contains(int value) {
        return domain.contains(value);
    }

    @Override
    public boolean isSingleton() {
        return domain.size() == 1;
    }

    @Override
    public void remove(int v, DomainListener l) {
        if (domain.contains(v)) {
            boolean maxChanged = max() == v;
            boolean minChanged = min() == v;
            domain.remove(v);
            if (domain.isEmpty()) {
                l.empty();
            }
            if (maxChanged) {
                l.maxChanged();
            }
            if (minChanged) {
                l.minChanged();
            }
            if (domain.size() == 1) {
                l.fix();
            }
        }

    }

    @Override
    public void fix(int v, DomainListener l) {
        if (!domain.contains(v)) {
            l.empty();
            return;
        }

        int currentMin = min();
        int currentMax = max();

        for (int i = currentMin; i < v; i++) {
            remove(i, l);
            if (domain.size() == 1) return;
        }

        for (int i = currentMax; i > v; i--) {
            remove(i, l);
            if (domain.size() == 1) return;
        }
    }

    @Override
    public void removeBelow(int v, DomainListener l) {
        int currentMin = min();
        for (int i = currentMin; i < v; i++) {
            remove(i, l);
            if (domain.size() == 1 || domain.isEmpty()) return;
        }

    }

    @Override
    public void removeAbove(int v, DomainListener l) {
        int currentMax = max();
        for (int i = currentMax; i > v; i--) {
            remove(i, l);
            if (domain.size() == 1 || domain.isEmpty()) return;
        }
    }
}
