package main.EndtermPreparation.NetworkFlow.FordFulkerson;

public class Edge {
    protected int capacity;

    protected int flow;

    protected Node from;

    protected Node to;

    protected Edge backwards;

    private Edge(Edge e) {
        this.flow = e.getCapacity();
        this.capacity = e.getCapacity();
        this.from = e.getTo();
        this.to = e.getFrom();
        this.backwards = e;
    }

    public Edge(int capacity, Node from, Node to) {
        this.capacity = capacity;
        this.from = from;
        this.to = to;
        this.flow = 0;
        this.backwards = new Edge(this);
    }

    public Edge getBackwards() {
        return backwards;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public void setFlow(int f) {
        assert (f <= capacity);
        this.flow = f;
    }

    public boolean equals(Object other) {
        if (other instanceof Edge) {
            Edge that = (Edge) other;
            return this.capacity == that.capacity && this.flow == that.flow && this.from.getId() == that.getFrom().getId() && this.to.getId() == that.getTo().getId();
        }
        return false;
    }
}
