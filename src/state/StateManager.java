package state;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class StateManager {
    private final Deque<List<Runnable>> trail = new ArrayDeque<>();
    private int level = 0;

    public StateManager() {
        trail.push(new ArrayList<>());
    }

    public StateInt makeStateInt(int value) {
        return new StateInt(this, value);
    }

    public StateBoolean makeStateBoolean(boolean value) {
        return new StateBoolean(this, value);
    }

    public <T> StateStack<T> makeStateStack() {
        return new StateStack<>(this);
    }


    public int getLevel() {
        return level;
    }

    public void record(Runnable undo) {
        if (trail.peek() != null) {
            trail.peek().add(undo);
        }
    }

    public void saveState() {
        level++;
        trail.push(new ArrayList<>());
    }

    public void restoreState() {
        List<Runnable> undo = this.trail.pop();
        for (int i = undo.size() - 1; i >= 0; i--) {
            undo.get(i).run();
        }
        level--;
    }
}
