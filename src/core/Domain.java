package core;

public interface Domain {
    int min();

    int max();

    int size();

    boolean contains(int value);

    boolean isSingleton();

    void remove(int v, DomainListener l);

    void fix(int v, DomainListener l);

    void removeBelow(int v, DomainListener l);

    void removeAbove(int v, DomainListener l);
}