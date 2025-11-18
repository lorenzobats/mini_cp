import constraints.NotEqual;
import core.Constraint;
import core.IntVariable;
import solver.MiniCP;
import state.StateManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StateManager stateManager = new StateManager();
        MiniCP solver = new MiniCP(stateManager);

        IntVariable q = new IntVariable("q", solver, 1, 3);
        IntVariable nsw = new IntVariable("nsw", solver, 1, 3);
        IntVariable sa = new IntVariable("sa", solver, 1, 3);
        IntVariable v = new IntVariable("v", solver, 1, 3);
        IntVariable t = new IntVariable("t", solver, 1, 3);
        IntVariable nt = new IntVariable("nt", solver, 1, 3);
        IntVariable wa = new IntVariable("wa", solver, 1, 3);

        NotEqual c1 = new NotEqual(wa, nt);
        NotEqual c2 = new NotEqual(wa, sa);
        NotEqual c3 = new NotEqual(nt, q);
        NotEqual c4 = new NotEqual(nt, sa);
        NotEqual c5 = new NotEqual(q, sa);
        NotEqual c6 = new NotEqual(q, nsw);
        NotEqual c7 = new NotEqual(nsw, sa);
        NotEqual c8 = new NotEqual(nsw, v);
        NotEqual c9 = new NotEqual(sa, v);

        List<Constraint> constraints = List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9);

        for (Constraint c : constraints) {
            solver.post(c);
        }

        // Simple DFS search
        if (dfs(solver, new IntVariable[]{wa, nt, sa, q, nsw, v, t}, 0)) {
            System.out.println("Solution:");
            for (IntVariable var : new IntVariable[]{wa, nt, sa, q, nsw, v, t}) {
                System.out.println(var.getName() + ": " + var.min());
            }
        } else {
            System.out.println("No solution found");
        }

    }

    private static boolean dfs(MiniCP solver, IntVariable[] vars, int index) {
        if (index == vars.length) return true; // all assigned

        IntVariable var = vars[index];

        for (int value = 1; value <= 3; value++) {
            if (!var.contains(value)) continue;

            solver.pushState();
            var.fix(value);
            solver.fixPoint();

            if (dfs(solver, vars, index + 1)) {
                return true;
            }

            solver.restoreState();
        }
        return false;
    }

}