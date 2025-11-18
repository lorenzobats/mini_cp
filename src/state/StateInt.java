package state;

import java.util.Objects;

public class StateInt implements State<Integer> {
    private final StateManager stateManager;
    private Integer value;


    public StateInt(StateManager stateManager, int value) {
        this.value = value;
        this.stateManager = stateManager;
    }

    public void increment() {
        value++;
    }

    public void decrement() {
        value--;
    }

    @Override
    public void setValue(Integer newValue) {
        if (!Objects.equals(newValue, this.value)) {
            int old = this.value;
            stateManager.record(() -> this.value = old);
            this.value = newValue;
        }
    }

    @Override
    public Integer value() {
        return value;
    }
}
