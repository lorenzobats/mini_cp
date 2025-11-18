package core;

public interface DomainListener {
    void empty();

    void fix();

    void change();

    void minChanged();

    void maxChanged();
}
