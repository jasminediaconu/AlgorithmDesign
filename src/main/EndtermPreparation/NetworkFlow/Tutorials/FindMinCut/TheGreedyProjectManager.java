package main.EndtermPreparation.NetworkFlow.Tutorials.FindMinCut;

import java.util.*;

public class TheGreedyProjectManager {
    /**
     *   You should implement the method below.
     *   BFS VERSION.
     */
    public static Set<Integer> outputSelection(int n, int[] benefits, int[] costs, int[][] dependencies) {
        Graph g = buildGraph(n, benefits, costs, dependencies);
        MaxFlow.maximizeFlow(g);

        // You may ignore all the inputs (n, benefits, costs, dependencies, etc).
        // You have the graph g, whose flow has been maximised. Now you should return the set of node ids from the set containing s of the minimum (s,t)-cut.
        // You can get the node s with g.getSource();

        HashSet<Integer> optimalSet = new HashSet<>();

        Node source = g.getSource();

        Queue<Node> bfs = new LinkedList<>();
        bfs.add(source);

        while (!bfs.isEmpty()) {
            Node first = bfs.remove();
            for(Edge e : first.getEdges()) {
                Node to = e.getTo();
                if(e.getFlow() < e.getCapacity() && !to.equals(source)
                        && !optimalSet.contains(to.getId())) {
                    optimalSet.add(to.getId());
                    bfs.add(to);
                }
            }
        }

        optimalSet.add(source.getId());

        return optimalSet;

    }

    /**
     *   You should implement the method below.
     *   DFS VERSION.
     */
    public static Set<Integer> outputSelectionDFS(int n, int[] benefits, int[] costs, int[][] dependencies) {
        Graph g = buildGraph(n, benefits, costs, dependencies);
        MaxFlow.maximizeFlow(g);

        // You may ignore all the inputs (n, benefits, costs, dependencies, etc).
        // You have the graph g, whose flow has been maximised. Now you should return the set of node ids from the set containing s of the minimum (s,t)-cut.
        // You can get the node s with g.getSource();

        HashSet<Integer> optimalSet = new HashSet<>();

        Node source = g.getSource();

        Stack<Node> dfs = new Stack<>();
        dfs.add(source);

        while (!dfs.isEmpty()) {
            Node first = dfs.pop();
            for(Edge e : first.getEdges()) {
                Node to = e.getTo();
                if(e.getFlow() < e.getCapacity() && !to.equals(source)
                        && !optimalSet.contains(to.getId())) {
                    optimalSet.add(to.getId());
                    dfs.add(to);
                }
            }
        }

        optimalSet.add(source.getId());

        return optimalSet;

    }

    private static Graph buildGraph(int n, int[] benefits, int[] costs, int[][] dependencies) {
        Node source = new Node(0);
        Node sink = new Node(n + 1);
        ArrayList<Node> nodes = new ArrayList<>(n + 2);
        nodes.add(source);
        for (int i = 1; i <= n; i++) {
            Node newNode = new Node(i);
            nodes.add(newNode);
        }
        nodes.add(sink);
        for (int i = 1; i <= n; i++) {
            source.addEdge(nodes.get(i), benefits[i]);
            nodes.get(i).addEdge(sink, costs[i]);
            for (int j = 1; j <= n; j++) {
                if (dependencies[i][j] > 0) {
                    nodes.get(i).addEdge(nodes.get(j), Integer.MAX_VALUE / 2);
                }
            }
        }
        return new Graph(nodes, source, sink);
    }
}
