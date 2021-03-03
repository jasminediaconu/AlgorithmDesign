package main.MidtermPreparation.GreedyAlgorithms.RoutingTrains;

import main.MidtermPreparation.GreedyAlgorithms.Maze.Node;

import java.io.InputStream;
import java.util.*;

/**
 * The train company that you work for is looking to have trains run in cycles instead of between two endpoints.
 * To speed up the decision process, they asked you to create an algorithm to determine quickly if it is
 * even possible for a piece of map to create a cyclic route.
 *
 * The map is represented by a directed graph G=(V,E), where V denotes the set containing n vertices and E
 * the set containing m directed edges.
 * Each vertex represents a station on the map and the edges represent railways between them.
 * The company also provides a central station, s, from which it should be possible to reach the cycle,
 * otherwise they will not consider it.
 *
 * Design and implement an algorithm that determines whether there exist a cycle reachable from s in the map.
 * Your algorithm should print yes or no and run in O(n+m) time.
 *
 * The first line of the input contains three integers separated by a space: n, m and s. The integers n and m
 * are defined as above and you may assume the vertices are labelled 1 up to and including n. E.g. if n equals 6,
 * the vertices are labelled 1,2,3,4,5 and 6. The integer s is the number of the vertex representing the central
 * station.
 * The next m lines are the directed edges. Each line contains three integers: the two numbers of the vertices
 * associated with this edge and the length (i.e., positive weight) of the edge. For example,
 * a line containing the integers 3, 6 and 9 indicates that there is an edge from vertex 3 to vertex 6
 * with length 9.
 */

public class RoutingTrains {

    // Implement the solve method to return the answer to the problem posed by the inputstream.
    public static String run(InputStream in) {
        return new RoutingTrains().solve(in);
    }

    public String solve(InputStream in) {
        Scanner scanner = new Scanner(in);

        // Scanning first row
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(0, new Node());
        // Initialize list
        for(int i = 1; i <= n; i++)
            nodes.add(i, new Node());

        // Scan the rest and store in Node
        while (scanner.hasNextInt()) {
            int first = scanner.nextInt();
            int second = scanner.nextInt();

            Node node = nodes.get(first);
            node.outgoingEdges.add(nodes.get(second));

            // Skip the edge weight
            scanner.nextInt();
        }

        scanner.close();

        HashMap<Node, Node> disjointSet = new HashMap<Node, Node>();

        for (Node v : nodes)
            disjointSet.put(v, v);

        int initCount = 0;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(nodes.get(s));

        while (initCount != m && !queue.isEmpty()) {
            Node first = queue.poll();

            for(Node end : first.outgoingEdges) {
                queue.add(end);
                if (hasCycle(first, end, disjointSet)) {
                    initCount++;
                    return "yes";
                }
            }
        }


        return "no";
    }


    public static boolean hasCycle(Node source, Node destination, HashMap<Node, Node> disjointSet) {
        Node root = find(disjointSet, source);
        Node root2 = find(disjointSet, destination);

        if (root == root2) return true;

        else union(disjointSet, source, destination);

        return false;
    }

    public static Node find(HashMap<Node, Node> parent, Node v) {
        // Vertex is the root
        if (parent.get(v) == v) return v;

        // recursive call
        return find(parent, parent.get(v));
    }

    public static void union(HashMap<Node, Node> parent, Node start, Node end) {
        Node x = find(parent, start);
        Node y = find(parent, end);

        parent.put(x, y);
    }
}