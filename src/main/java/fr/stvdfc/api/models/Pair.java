package fr.stvdfc.api.models;

public class Pair<F, S> {

    private F first;
    private S second;

    /**
     * @param first The first element
     * @param second The second element
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }
}
