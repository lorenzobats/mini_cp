package core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    List<IntVariable> variables = new ArrayList<>();
    List<Constraint> constraints = new ArrayList<>();

    public Model() {
    }

    public Model(Model model) {
        this.variables = new ArrayList<>(model.variables);
        this.constraints = new ArrayList<>(model.constraints);
    }

    public void addConstraint(Constraint constraint) {
        this.constraints.add(constraint);
    }

    public void addVariable(IntVariable variable) {
        this.variables.add(variable);
    }

    public List<IntVariable> getVariables() {
        return variables;
    }

    public List<Constraint> getConstraints() {
        return constraints;
    }

    public List<Constraint> getConstraints(IntVariable variable) {
        return constraints.stream().filter(constraint -> constraint.getVariables().contains(variable)).collect(Collectors.toList());
    }

    public Model copy() {
        return new Model(this);
    }
}
