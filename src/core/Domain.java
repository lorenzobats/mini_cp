package core;

import java.util.Set;

public class Domain {
    Set<Integer> values;

    public Domain(Set<Integer> values) {
        this.values = values;
    }

    public boolean isBound() {
        return values.size() == 1;
    }

    public boolean contains(int value) {
        return values.contains(value);
    }

    public boolean remove(int value) {
        return values.remove(value);
    }

    public Set<Integer> getValues() {
        return values;
    }
}
