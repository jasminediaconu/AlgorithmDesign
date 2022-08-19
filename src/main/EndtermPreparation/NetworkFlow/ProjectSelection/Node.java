package main.EndtermPreparation.NetworkFlow.ProjectSelection;

import java.util.ArrayList;
import java.util.Collection;

public class Node {

    protected String id;

    protected Collection<Edge> edges;

    public Node(String id) {
        this.id = id;
        this.edges = new ArrayList<Edge>();
    }

    public void addEdge(Node to, int capacity) {
        Edge e = new Edge(capacity, this, to);
        edges.add(e);
        to.getEdges().add(e.getBackwards());
    }

    public Collection<Edge> getEdges() {
        return edges;
    }

    public String getId() {
        return id;
    }

    public boolean equals(Object other) {
        if (other instanceof Node) {
            Node that = (Node) other;
            if (id.equals(that.getId()))
                return edges.equals(that.getEdges());
        }
        return false;
    }
}
