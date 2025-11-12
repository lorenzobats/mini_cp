package solver;

import core.IntVariable;
import core.Model;

public interface Propagation {
    boolean apply(Model model, IntVariable variable);
}
