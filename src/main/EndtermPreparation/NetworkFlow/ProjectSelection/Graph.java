package main.EndtermPreparation.NetworkFlow.ProjectSelection;

import java.util.Collection;

public class Graph {

    private Collection<Node> nodes;

    private Node source;

    private Node sink;

    public Graph(Collection<Node> nodes, Node source, Node sink) {
        this.nodes = nodes;
        this.source = source;
        this.sink = sink;
    }

    public Node getSink() {
        return sink;
    }

    public Node getSource() {
        return source;
    }

    public Collection<Node> getNodes() {
        return nodes;
    }

    public void maximizeFlow() {
        MaxFlow.maximizeFlow(this);
    }

    public boolean equals(Object other) {
        if (other instanceof Graph) {
            Graph that = (Graph) other;
            return this.nodes.equals(that.nodes);
        }
        return false;
    }
}