package main.Tutorials.GreedyAlgorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class KruskalMST {
    /**
     * Optimizes the provided Phorest to be as close to an MST as possible.
     * @param n the number of nodes in the network
     * @param g all edges in the full graph
     * @param p the edges in the Phorest
     * @return total edge weight of optimized Phorest
     */
    public static String run(int n, Edge[] g, Edge[] p) {
        return new KruskalMST().solve(n, g, p);
    }

    public String solve(int nodes, Edge[] graph, Edge[] phorest) {
        // The max allowed number of edges
        int finalCount = nodes - 1;
        // The initial number of edges
        int initCount = 0;
        // Weight of the minimum spanning tree
        int result = 0;

        HashMap<Integer, Integer> disjointSet = new HashMap<Integer, Integer>();

        for(int i = 0; i < nodes; i++) disjointSet.put(i, i);

        for(int i = 0; i < phorest.length; i++) {
            if (!hasCycle(phorest[i].x, phorest[i].y, disjointSet)) {
                initCount++;
                result += phorest[i].l;
            }
        }

        int i = 0;
        while (initCount != finalCount) {
            Edge edge = graph[i];

            if (!hasCycle(edge.x, edge.y, disjointSet)) {
                initCount++;
                result += edge.l;
            }

            i++;
        }

        return "" + result;
    }

    public static Edge[] makeGraph(Scanner sc) {
        int m = sc.nextInt();
        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());

        Arrays.sort(edges, Comparator.comparingInt(e -> e.l));

        return edges;
    }


    public static boolean hasCycle(Integer source, Integer destination, HashMap<Integer, Integer> disjointSet) {
        int root = find(disjointSet, source);
        int root2 = find(disjointSet, destination);

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

    public static void union(HashMap<Integer, Integer> parent, Integer start, Integer end) {
        Integer x = find(parent, start);
        Integer y = find(parent, end);

        parent.put(x, y);
    }
}
