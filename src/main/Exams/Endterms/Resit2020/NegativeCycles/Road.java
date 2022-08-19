package main.Exams.Endterms.Resit2020.NegativeCycles;

import java.util.Objects;

public class Road {
    protected int cost;

    protected Town from;

    protected Town to;

    public Road(Town from, Town to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public Town getFrom() {
        return from;
    }

    public Town getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Road))
            return false;
        Road road = (Road) o;
        return cost == road.cost && Objects.equals(from, road.from) && Objects.equals(to, road.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, from, to);
    }
}
