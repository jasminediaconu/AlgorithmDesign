package main.MidtermPreparation.GreedyAlgorithms.Maze;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public List<Node> outgoingEdges;

    boolean marked;

    public Node() {
        this.outgoingEdges = new ArrayList<>();
        this.marked = false;
    }
}
