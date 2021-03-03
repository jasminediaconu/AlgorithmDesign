package main.MidtermPreparation.GreedyAlgorithms.WirelessNetwork;

import java.io.InputStream;
import java.util.*;

/**
 * To set up internet connections in a large area in Cittagazze,
 * stations are planned at locations with a high population density.
 * These n stations are connected through pairs of quite expensive directional antennas.
 * The price of (a pair of) antennas depends on the required range,
 * and for each pair of locations (u,v) these costs are given by c(u,v)>0.
 * The main question of this exercise is which (undirected) connections to set up
 * such that all locations are connected and the installation costs are as low as possible.
 *
 * Unfortunately, there is insufficient budget
 * for setting up the connected network computed above at once.
 * The government decides to make as many connections of the optimal network as possible,
 * given the available budget B (and to complete the network in upcoming years).
 * For example, if the network looks like this,
 * a budget of 3 would select (a,b) and (d,e) and (f,g).
 *
 */
public class WirelessNetwork {
    // Implement the solve method to return the answer to the problem posed by the inputstream.
    public static String run(InputStream in) {
        return new WirelessNetwork().solve(in);
    }

    public String solve(InputStream in) {
        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int budget = sc.nextInt();

        ArrayList<Integer> vertices = new ArrayList<>();
        for(int i = 1; i <= n; i++) vertices.add(i);

        ArrayList<Edge> edges = new ArrayList<>();
        while(sc.hasNextInt()) {
            edges.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        sc.close();

        // Get all edges and sort them in ascendant order
        Queue<Edge> pq = new PriorityQueue<Edge>();

        for (Edge e : edges) pq.add(e);

        // The max allowed number of edges
        int finalCount = n - 1;
        // The initial number of edges
        int initCount = 0;
        // Weight of the minimum spanning tree
        int result = 0;

        int numberOfConnections = 0;

        HashMap<Integer, Integer> disjointSet = new HashMap<Integer, Integer>();

        for (Integer v : vertices)
            disjointSet.put(v, v);

        while (initCount != finalCount && !pq.isEmpty()) {
            Edge e = pq.remove();

            if (!hasCycle(e.getIncomingVertex(), e.getOutgoingVertex(), disjointSet)) {
                initCount++;
                result += e.getWeight();

                if(result <= budget) numberOfConnections++;
            }
        }

        return result + " " + numberOfConnections;
    }



    /**
     * Checks if the graph has cycles.
     * @param source
     * @param destination
     * @param disjointSet
     * @return
     */
    public static boolean hasCycle(Integer source, Integer destination, HashMap<Integer, Integer> disjointSet) {
        Integer root = find(disjointSet, source);
        Integer root2 = find(disjointSet, destination);

        if (root == root2) return true;

        else union(disjointSet, source, destination);

        return false;
    }

    public static Integer find(HashMap<Integer, Integer> parent, Integer v) {
        // Vertex is the root
        if (parent.get(v) == v) return v;

        // recursive call
        return find(parent, parent.get(v));
    }

    public static void union(HashMap<Integer, Integer> parent, Integer a, Integer b) {
        Integer x = find(parent, a);
        Integer y = find(parent, b);

        parent.put(x, y);
    }

}

class Edge implements Comparable<Edge> {
    int incomingVertex;
    int outgoingVertex;
    int weight;

    public Edge(int incomingVertex, int outgoingVertex, int weight) {
        this.incomingVertex = incomingVertex;
        this.outgoingVertex = outgoingVertex;
        this.weight = weight;
    }

    public int getIncomingVertex() {
        return incomingVertex;
    }

    public int getOutgoingVertex() {
        return outgoingVertex;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Edge e) {
        if (this.getWeight() < e.getWeight()) return -1;
        if (this.getWeight() > e.getWeight()) return 1;

        return 0;
    }
}