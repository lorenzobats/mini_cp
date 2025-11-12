package solver;

import core.IntVariable;
import core.Model;

public class ForwardChecking implements Propagation {
    @Override
    public boolean apply(Model model, IntVariable variable) {
        return false;
    }
}
