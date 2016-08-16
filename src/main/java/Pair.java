package main.java;

/**
 * Created by Jeffrey Lima on 8/2/16.
 * Generic Pair class
 */
public class Pair<E1, E2> {
    private E1 e1;
    private E2 e2;

    public Pair(E1 e1, E2 e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public E1 getE1() {
        return e1;
    }

    public E2 getE2() {
        return e2;
    }

    public void setE1(E1 e1) {
        this.e1 = e1;
    }

    public void setE2(E2 e2) {
        this.e2 = e2;
    }

    @Override
    public String toString() {
        return "{" + e1 + ", " + e2 + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (e1 != null ? !e1.equals(pair.e1) : pair.e1 != null) return false;
        return e2 != null ? e2.equals(pair.e2) : pair.e2 == null;

    }

    @Override
    public int hashCode() {
        int result = e1 != null ? e1.hashCode() : 0;
        result = 31 * result + (e2 != null ? e2.hashCode() : 0);
        return result;
    }
}
