package main.Exams.Endterms.Endterm2020.Quest;

import java.util.Objects;

public class Edge {

    protected int cost;

    protected Node from;

    protected Node to;

    public Edge(Node from, Node to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Edge edge = (Edge) o;
        return cost == edge.cost && Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, from, to);
    }
}
