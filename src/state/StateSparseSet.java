package state;

public class StateSparseSet {
    private final int[] values, indices;
    private final StateInt size, min, max;
    private final int offset, n;

    public StateSparseSet(StateManager sm, int n, int offset) {
        this.n = n;
        this.offset = offset;
        size = sm.makeStateInt(n);
        min = sm.makeStateInt(0);
        max = sm.makeStateInt(n - 1);
        values = new int[n];
        indices = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = i;
            indices[i] = i;
        }
    }

    public void exchange(int v1, int v2) {
        int i1 = indices[v1];
        int i2 = indices[v2];
        values[i1] = v2;
        values[i2] = v1;
        indices[v1] = i2;
        indices[v2] = i1;
    }

    public boolean isEmpty() {
        return size.value() == 0;
    }

    public int size() {
        return size.value();
    }

    public boolean contains(int i) {
        i -= offset;
        if (i < 0 || i >= n) {
            return false;
        } else {
            return indices[i] < size();
        }
    }

    public boolean remove(int i) {
        if (!contains(i)) return false;
        i -= offset;
        int s = size();
        exchange(i, values[s - 1]);
        size.setValue(s - 1);
        updateBounds();
        return true;
    }

    private void updateBounds() {
        if (size() == 0) {
            min.setValue(0);
            max.setValue(-1);
            return;
        }
        int m = values[0];
        int M = values[0];
        for (int j = 1; j < size(); j++) {
            if (values[j] < m) m = values[j];
            if (values[j] > M) M = values[j];
        }
        min.setValue(m);
        max.setValue(M);
    }

    public int min() {
        return min.value() + offset;
    }

    public int max() {
        return max.value() + offset;
    }

}
