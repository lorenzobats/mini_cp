package state;

import java.util.ArrayList;

public class StateStack<T> {
    private final StateInt size;
    private final ArrayList<T> stack = new ArrayList<>();

    public StateStack(StateManager sm) {
        this.size = sm.makeStateInt(0);
    }


    public void push(T element) {
        int s = size.value();
        if (stack.size() > s) {
            stack.set(s, element);
        } else {
            stack.add(element);
        }
        size.increment();
    }

    public int size() {
        return size.value();
    }

    public T get(int i) {
        return stack.get(i);
    }


}
