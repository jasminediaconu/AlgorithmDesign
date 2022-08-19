package main.EndtermPreparation.NetworkFlow.ResidualGraph;

import java.util.Objects;

public class Edge {

    private int capacity;

    private Node from;

    private Node to;

    protected Edge(int capacity, Node from, Node to) {
        this.capacity = capacity;
        this.from = from;
        this.to = to;
    }

    int getCapacity() {
        return capacity;
    }

    Node getFrom() {
        return from;
    }

    Node getTo() {
        return to;
    }

    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean equals(Object other) {
        if (other instanceof Edge) {
            Edge that = (Edge) other;
            return this.capacity == that.capacity && this.from.getId() == that.getFrom().getId() && this.to.getId() == that.getTo().getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, from.getId(), to.getId());
    }
}
