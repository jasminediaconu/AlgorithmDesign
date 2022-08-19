package main.EndtermPreparation.NetworkFlow.ResidualGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Node {

    private int id;

    private Collection<Edge> edges;

    public Node(int id) {
        this.id = id;
        this.edges = new ArrayList<Edge>();
    }

    void addEdge(Node to, int capacity) {
        Edge e = new Edge(capacity, this, to);
        edges.add(e);
    }

    Collection<Edge> getEdges() {
        return edges;
    }

    int getId() {
        return id;
    }

    public boolean equals(Object other) {
        if (other instanceof Node) {
            Node that = (Node) other;
            if (id == that.getId()) {
                // Edges with capacity 0 should be ignored (you can choose to remove or keep these in the graph)
                Set<Edge> thisEdges = this.getEdges().stream().filter(e -> e.getCapacity() > 0).collect(Collectors.toSet());
                Set<Edge> thatEdges = that.getEdges().stream().filter(e -> e.getCapacity() > 0).collect(Collectors.toSet());
                return thisEdges.equals(thatEdges);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, edges);
    }
}