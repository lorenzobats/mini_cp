package solver;

import core.*;

import java.util.List;

public class BacktrackingSolver implements Solver {
    private boolean enableForwardChecking;

    @Override
    public boolean solve(Model model, Assignment assignment) {
        if (isComplete(model.getVariables(), assignment)) {
            return true;
        }

        IntVariable var = selectUnassigned(model.getVariables(), assignment);

        for (int value : var.getDomain().getValues()) {
            assignment.setValue(var, value);
            if (isConsistent(assignment, var, model.getConstraints())) {
                if (solve(model, assignment)) {
                    return true;
                }
            }
            assignment.remove(var);
        }
        return false;
    }

    public boolean isConsistent(Assignment assignment, IntVariable var, List<Constraint> constraints) {
        for (Constraint constraint : constraints) {
            if (constraint.getVariables().contains(var) && !constraint.isSatisfied(assignment)) {
                return false;
            }
        }
        return true;
    }

    public boolean isComplete(List<IntVariable> vars, Assignment assignment) {
        for (IntVariable var : vars) {
            if (!assignment.isAssigned(var)) {
                return false;
            }
        }
        return true;
    }

    public IntVariable selectUnassigned(List<IntVariable> variables, Assignment assignment) {
        for (IntVariable var : variables) {
            if (!assignment.isAssigned(var)) {
                return var;
            }
        }
        return null;
    }
}
