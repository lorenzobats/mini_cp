package constraints;

import core.Assignment;
import core.Constraint;
import core.Domain;
import core.IntVariable;

import java.util.List;
import java.util.Map;

public class BinPacking implements Constraint {
    private List<IntVariable> items;
    private List<Integer> weights;
    private int[] capacity;

    public BinPacking(List<IntVariable> items, List<Integer> weights, int[] capacity) {
        this.items = items;
        this.weights = weights;
        this.capacity = capacity;
    }

    @Override
    public List<IntVariable> getVariables() {
        return items;
    }

    @Override
    public boolean isSatisfied(Assignment assignment) {
        int numBins = capacity.length;
        int[] load = new int[numBins];

        for (int i = 0; i < items.size(); i++) {
            IntVariable var = items.get(i);
            if (assignment.isAssigned(var)) {
                int bin = assignment.getValue(var);
                load[bin] += weights.get(i);

                if (load[bin] > capacity[bin]) return false;
            }
        }
        return true;
    }

    @Override
    public boolean propagate(Assignment assignment, Map<IntVariable, Domain> domains) {
        return false;
    }
}
