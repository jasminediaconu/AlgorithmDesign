package main.Exams.Endterms.Resit2019.NetworkFlow;

import java.util.ArrayList;
import java.util.Collection;

public class Node {

    protected int id;

    protected int d;

    protected Collection<Edge> edges;

    /**
     *  Create a new node
     *  @param id: Id for the node.
     *  @param d: demand for the node. Remember that supply is represented as a negative demand.
     */
    public Node(int id, int d) {
        this.id = id;
        this.d = d;
        this.edges = new ArrayList<Edge>();
    }

    public void addEdge(Node to, int lower, int upper) {
        Edge e = new Edge(lower, upper, this, to);
        edges.add(e);
        to.getEdges().add(e.getBackwards());
    }

    public Collection<Edge> getEdges() {
        return edges;
    }

    public int getId() {
        return id;
    }

    public boolean equals(Object other) {
        if (other instanceof Node) {
            Node that = (Node) other;
            if (id == that.getId())
                return edges.equals(that.getEdges());
        }
        return false;
    }
}