package main.EndtermPreparation.NetworkFlow.FordFulkerson;

import java.util.*;

public class FordFulkerson {

    /**
     * Find the maximum flow in the given network.
     *
     * @param g Graph representing the network.
     * @return The maximum flow of the network.
     */
    public static int maxFlow(Graph g) {

        Node sink = g.getSink();
        Node source = g.getSource();

        List<Edge> path;

        // Repeat as long as the flow can be increased
        while ((path = findPath(source, sink)) != null) {

            // Calculate the bottleneck
            int b = Integer.MAX_VALUE;
            for (Edge e : path) b = Math.min(b, e.getCapacity() - e.getFlow());

            // Augment the flow on the path
            for (Edge e : path) {
                e.setFlow(e.getFlow() + b);
                e.getBackwards().setFlow(e.getCapacity() - e.getFlow());
            }
        }

        int flow = 0;
        for(Edge e : source.getEdges()) flow += e.getFlow();

        return flow;
    }

    private static List<Edge> findPath(Node start, Node end) {
        // Stores the edge that we used to reach the node
        Map<Node, Edge> mapPath = new HashMap<>();

        Queue<Node> bfs = new LinkedList<>();
        Node currentNode = start;
        bfs.add(currentNode);

        // Implement BFS to find the path from s to t
        while (!bfs.isEmpty()) {
            currentNode = bfs.remove();

            // If we reached t then there is a path
            if(currentNode == end) break;

            // using edges that have a flow < capacity (i.e. we can augment the flow)
            for (Edge e : currentNode.getEdges()) {
                Node to = e.getTo();
                if (to != start && mapPath.get(to) == null && e.getCapacity() - e.getFlow() > 0) {
                    bfs.add(e.getTo());
                    // Store how we got to the next node
                    mapPath.put(to, e);
                }
            }
        }

        // No path found
        if (bfs.isEmpty() && currentNode != end) return null;

        // Trace back path
        LinkedList<Edge> path = new LinkedList<Edge>();

        Node current = end;

        while (mapPath.get(current) != null) {
            Edge e = mapPath.get(current);
            path.addFirst(e);
            current = e.getFrom();
        }

        return path;
    }

    /**
     * Find the maximum flow in the given network with parameter Delta.
     *
     * @param g Graph representing the network.
     * @return The maximum flow of the network.
     */
    public static int maxFlowScaled(Graph g) {

        Node sink = g.getSink();
        Node source = g.getSource();

        List<Edge> path;

        int maxOutgoingCapacity = 0;

        // Find the edge with greatest capacity going out of the source
        for(Edge e : source.getEdges())
            maxOutgoingCapacity = Math.max(e.capacity, maxOutgoingCapacity);

        // Compute Delta
        double delta = 1;
        while(delta * 2 < maxOutgoingCapacity) delta *= 2;

        // As long as delta is positive, and we can find a path, increase the flow
        while(delta >= 1) {
            while ((path = findScaledPath(source, sink, delta)) != null) {
                // Calculate the bottleneck
                int b = Integer.MAX_VALUE;
                for (Edge e : path) b = Math.min(b, e.getCapacity() - e.getFlow());

                // Augment the flow on the path
                for (Edge e : path) {
                    e.setFlow(e.getFlow() + b);
                    e.getBackwards().setFlow(e.getCapacity() - e.getFlow());
                }
            }

            // halve Delta at each iteration
            delta /= 2;
        }

        int flow = 0;
        for(Edge e : source.getEdges()) flow += e.getFlow();

        return flow;
    }

    private static List<Edge> findScaledPath(Node start, Node end, double delta) {
        // Stores the edge that we used to reach the node
        Map<Node, Edge> mapPath = new HashMap<>();

        Queue<Node> bfs = new LinkedList<>();
        Node currentNode = start;
        bfs.add(currentNode);

        // Implement BFS to find the path from s to t
        while (!bfs.isEmpty()) {
            currentNode = bfs.remove();

            // If we reached t then there is a path
            if(currentNode == end) break;

            // using edges that have a flow < capacity (i.e. we can augment the flow)
            for (Edge e : currentNode.getEdges()) {
                    Node to = e.getTo();
                    if (to != start && mapPath.get(to) == null && e.getCapacity() - e.getFlow() > delta) {
                        bfs.add(e.getTo());
                        // Store how we got to the next node
                        mapPath.put(to, e);
                    }
            }
        }

        // No path found
        if (bfs.isEmpty() && currentNode != end) return null;

        // Trace back path
        LinkedList<Edge> path = new LinkedList<Edge>();

        Node current = end;

        while (mapPath.get(current) != null) {
            Edge e = mapPath.get(current);
            path.addFirst(e);
            current = e.getFrom();
        }

        return path;
    }

    /**
     *
     * Scaling Max-Flow
     *
     * Initially f(e) = 0 for all e in G
     * Initially set Delta to be the largest power of 2 that is no larger
     *   than the maximum capacity out of s: Delta ≤ max_e out of s c_e
     *
     *   While Delta ≥ 1
     *      While there is an s-t path in the graph Gf(Delta)
     *          Let P be a simple s-t path in Gf(Delta)
     *              f' = augment(f , P)
     *              Update f to be f and update Gf(Delta)
     *      Endwhile
     *      Delta = Delta/2
     *   Endwhile
     * Return f
     *
     * */
}

