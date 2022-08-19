package main.EndtermPreparation.NetworkFlow.MaxEdgeDisjointPaths;

import java.util.*;

/**
 * Given a graph, find the max edge-disjoint paths.
 *
 * To find the amount of paths:
 * 1. Assign unit capacity to each edge.
 * 2. Run Ford-Fulkerson on the algorithm to find the max flow from source to sink.
 * 3. The maximum flow is equal to the number of edge disjoint paths.
 *
 * To find the paths:
 * 1. Run a DFS from source, ignore the edges that have a zero or a negative flow. Take only the ones with
 *    flow = 1.
 * 2. Once you reach the sink, subtract one from the flow through all edges on the path from source to sink.
 * 3. Keep doing this as long as the sink is reachable.
 * 
 */
public class MaxEdgeDisjointPaths {

    /**
     * Building graph with all edges with capacities 1.
     * @param n
     * @return
     */
    public static Graph buildGraph(int n, int m) {
        Node source = new Node(0);
        Node sink = new Node(n + m + 1);

        ArrayList<Node> nodes = new ArrayList<>(n + 2);

        nodes.add(source);

        for (int i = 1; i <= n; i++) nodes.add(new Node(i));
        for (int i = 1; i <= m; i++) nodes.add(new Node(i+n));

        nodes.add(sink);

        for (int i = 1; i <= n; i++) source.addEdge(nodes.get(i), 1);

        for (int i = 1; i <= m; i++) nodes.get(n+i).addEdge(sink, 1);

        nodes.get(1).addEdge(nodes.get(2), 1);
        nodes.get(2).addEdge(nodes.get(6), 1);
        nodes.get(2).addEdge(nodes.get(3), 1);
        nodes.get(3).addEdge(nodes.get(6), 1);
        nodes.get(6).addEdge(nodes.get(5), 1);
        nodes.get(5).addEdge(nodes.get(4), 1);

        return new Graph(nodes, source, sink);
    }

    /**
     * Look for paths in the graph from s to t
     * @param g
     * @param start
     * @param end
     * @return
     */
    private static List<Edge> findPath(Graph g, Node start, Node end) {
        Map<Node, Edge> mapPath = new HashMap<Node, Edge>();
        Stack<Node> stack = new Stack<>();

        Node currentNode = start;

        stack.add(currentNode);

        while (!stack.isEmpty() && currentNode != end) {
            currentNode = stack.pop();

            for (Edge e : currentNode.getEdges()) {
                Node to = e.getTo();

                if (to != start && mapPath.get(to) == null && e.getResidual() > 0) {
                    stack.add(e.getTo());
                    mapPath.put(to, e);
                }
            }
        }
        if (stack.isEmpty() && currentNode != end) return null;

        // Building up the path
        LinkedList<Edge> path = new LinkedList<Edge>();
        Node current = end;

        while (mapPath.get(current) != null) {
            Edge e = mapPath.get(current);
            path.addFirst(e);
            current = e.getFrom();
        }

        return path;
    }
    
    public static int maximizeFlow(Graph g) {
        int f = 0;
        Node sink = g.getSink();
        Node source = g.getSource();
        List<Edge> path;

        List<List<Edge>> paths = new ArrayList<>();

        // While there is a path from source to sink, increase the flow through the edges
        while ((path = findPath(g, source, sink)) != null) {

            paths.add(path);

            for (Edge e : path) e.augmentFlow(1);
            f++;

        }

        return f;
    }
}
