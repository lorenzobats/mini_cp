package core;

import java.util.HashMap;
import java.util.Map;

public class Assignment {
    private Map<IntVariable, Integer> assignment = new HashMap<>();

    public Map<IntVariable, Integer> getAssignements() {
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

    public boolean isAssigned(IntVariable var) {
        return assignment.containsKey(var);
    }

}
