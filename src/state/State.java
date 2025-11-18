package state;

public interface State<T> {
    void setValue(T value);

    T value();
}
