package constraints;

import core.IntVariable;

import java.util.List;

// x != y + c
public class NotEqual extends AbstractConstraint {
    private final IntVariable x;
    private final IntVariable y;
    private final int c;

    public NotEqual(IntVariable x, IntVariable y) {
        super(x.getSolver());
        this.x = x;
        this.y = y;
        this.c = 0;
    }

    public NotEqual(IntVariable x, IntVariable y, int c) {
        super(x.getSolver());
        this.x = x;
        this.y = y;
        this.c = c;
    }

    @Override
    public void post() {
        // initial inference
        if (y.isFixed()) x.remove(y.min() + c);
        else if (x.isFixed()) y.remove(x.min() - c);
        else {
            // initial propagation
            x.propagateOnFix(this);
            y.propagateOnFix(this);
        }
    }

    @Override
    public void propagate() {
        // when fix event is called
        if (y.isFixed()) x.remove(y.min() + c);
        else y.remove(x.min() - c);
        setActive(false);
    }

    @Override
    public List<IntVariable> getVariables() {
        return List.of(x, y);
    }
}
