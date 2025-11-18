package state;

public class StateBoolean implements State<Boolean> {
    private final StateManager stateManager;
    private Boolean value;

    public StateBoolean(StateManager stateManager, Boolean value) {
        this.stateManager = stateManager;
        this.value = value;
    }

    @Override
    public void setValue(Boolean newValue) {
        this.value = newValue;
        if (this.value != newValue) {
            boolean old = this.value();
            stateManager.record(() -> this.value = old);
            this.value = newValue;
        }
    }

    @Override
    public Boolean value() {
        return value;
    }
}
