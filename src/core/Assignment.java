package core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assignment {
    private Map<IntVariable, Integer> assignment = new HashMap<>();

    public Map<IntVariable, Integer> getAssignment() {
        return assignment;
    }

    public void setValue(IntVariable var, int value) {
        assignment.put(var, value);
    }

    public Integer getValue(IntVariable var) {
        return assignment.get(var);
    }

    public void remove(IntVariable var) {
        assignment.remove(var);
    }

    public boolean isComplete(List<IntVariable> vars) {
        return assignment.size() == vars.size();
    }

    public boolean isConsistent(List<Constraint> constraints) {
        for (Constraint c : constraints) {
            if (!c.isSatisfied()) {
                return false;
            }
        }
        return true;
    }

    public Assignment copy() {
        Assignment clone = new Assignment();
        clone.assignment.putAll(this.assignment);
        return clone;
    }

}
