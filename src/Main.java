import constraints.NotEqual;
import core.*;
import solver.BacktrackingSolver;

import java.util.Map;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Model model = new Model();

        Domain domain = new Domain(Set.of(1, 2, 3));
        IntVariable wa = new IntVariable(domain, "WA");
        IntVariable nt = new IntVariable(domain, "NT");
        IntVariable sa = new IntVariable(domain, "SA");
        IntVariable q = new IntVariable(domain, "Q");
        IntVariable v = new IntVariable(domain, "V");
        IntVariable ta = new IntVariable(domain, "TA");
        IntVariable nsw = new IntVariable(domain, "NSW");

        model.addVariable(wa);
        model.addVariable(nt);
        model.addVariable(sa);
        model.addVariable(q);
        model.addVariable(v);
        model.addVariable(ta);
        model.addVariable(nsw);

        Constraint c1 = new NotEqual(wa, nt);
        Constraint c2 = new NotEqual(wa, sa);
        Constraint c3 = new NotEqual(q, nt);
        Constraint c4 = new NotEqual(q, nsw);
        Constraint c5 = new NotEqual(q, sa);
        Constraint c6 = new NotEqual(v, nsw);
        Constraint c7 = new NotEqual(v, sa);
        Constraint c8 = new NotEqual(nt, sa);
        Constraint c9 = new NotEqual(nsw, sa);

        model.addConstraint(c1);
        model.addConstraint(c2);
        model.addConstraint(c3);
        model.addConstraint(c4);
        model.addConstraint(c5);
        model.addConstraint(c6);
        model.addConstraint(c7);
        model.addConstraint(c8);
        model.addConstraint(c9);

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