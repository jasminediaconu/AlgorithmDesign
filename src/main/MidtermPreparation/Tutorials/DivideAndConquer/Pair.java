package main.MidtermPreparation.Tutorials.DivideAndConquer;


public class Pair<L, R> {

    private L l;

    private R r;

    /**
     * Constructor
     * @param l left element
     * @param r right element
     */
    public Pair(L l, R r) {
        this.l = l;
        this.r = r;
    }

    /**
     * @return the left element
     */
    public L getL() {
        return l;
    }

    /**
     * @return the right element
     */
    public R getR() {
        return r;
    }

    /**
     * @param l left element
     */
    public void setL(L l) {
        this.l = l;
    }

    /**
     * @param r right element
     */
    public void setR(R r) {
        this.r = r;
    }
}