package solver;

import core.Assignment;
import core.IntVariable;
import core.Model;
import core.Solver;

public class BacktrackingSolver implements Solver {
    private boolean enableForwardChecking;


    public boolean isConsistent(Assignment assignment, IntVariable var, int value, Model model) {
        return false;
    }

    public IntVariable selectUnassigned(Model model, Assignment assignment) {
        return null;
    }


    public Assignment backtrack(Assignment assignment, Model model) {
        return null;
    }

    @Override
    public Assignment solve(Model model) {
        return null;
    }
}
