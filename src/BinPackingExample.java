import constraints.BinPacking;
import core.*;
import solver.BacktrackingSolver;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BinPackingExample {
    public static void main(String[] args) {
        int[] capacities = {1, 2, 3};

        // domain are the possible bins, which the item can go into
        List<Integer> weights = List.of(1, 1, 1, 3);

        IntVariable i1 = new IntVariable(new Domain(Set.of(0, 1, 2)), "i1");
        IntVariable i2 = new IntVariable(new Domain(Set.of(0, 1, 2)), "i2");
        IntVariable i3 = new IntVariable(new Domain(Set.of(0, 1, 2)), "i3");
        IntVariable i4 = new IntVariable(new Domain(Set.of(0, 1, 2)), "i4");

        Constraint c1 = new BinPacking(List.of(i1, i2, i3, i4), weights, capacities);

        Model model = new Model();
        model.addVariable(i1);
        model.addVariable(i2);
        model.addVariable(i3);
        model.addVariable(i4);

        model.addConstraint(c1);

        Solver solver = new BacktrackingSolver();
        Assignment assignment = new Assignment();
        boolean solution = solver.solve(model, assignment);

        if (solution) {
            for (Map.Entry<IntVariable, Integer> a : assignment.getAssignements().entrySet()) {
                System.out.println(a.getKey().getName() + " " + a.getValue());
            }
        } else {
            System.out.println("No solution found!");
        }
    }

}
