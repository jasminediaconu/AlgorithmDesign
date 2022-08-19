package main.EndtermPreparation.NetworkFlow.ResidualGraph;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResidualGraph {

    /**
     * Parses inputstream to graph.
     */
    static Graph parse(InputStream in) {
        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        int t = sc.nextInt();

        ArrayList<Node> nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) nodes.add(new Node(i));

        for (int i = 0; i < m; i++) {

            int from = sc.nextInt();
            int to = sc.nextInt();
            int cap = sc.nextInt();
            int flow = sc.nextInt();

            nodes.get(from).addEdge(nodes.get(to), cap - flow);
            nodes.get(to).addEdge(nodes.get(from), flow);
        }

        sc.close();

        return new Graph(nodes, nodes.get(s), nodes.get(t));
    }

    /**
     * Augments the flow over the given path by 1 if possible.
     *
     * @param g    Graph to operate on.
     * @param path List of nodes to represent the path.
     * @throws IllegalArgumentException if augmenting the flow in the given path is impossible.
     */
    static void augmentPath(Graph g, List<Integer> path) throws IllegalArgumentException {

        /**
         * augment(f , P)
         *  Let b = bottleneck(P, f)
         *  For each edge (u, v) ∈ P
         *    If e = (u, v) is a forward edge then
         *      increase f(e) in G by b
         *    Else ((u, v) is a backward edge, and let e = (v, u))
         *      decrease f(e) in G by b
         *    Endif
         *  Endfor
         *
         * Return(f)
         */

        List<Node> nodes = g.getNodes();

        for(int i = 1; i < path.size(); i++)
        {
            int from = path.get(i-1);
            int to = path.get(i);

            Edge e = findEdge(nodes.get(from), nodes.get(to));

            // If we cannot find an edge or the capacity is = 0, then we cannot use that edge
            if(e == null || e.getCapacity() == 0) throw new IllegalArgumentException();

            // We add 1 to the flow, therefore we reduce the capacity by 1 (forward edge)
            e.setCapacity(e.getCapacity() - 1);

            // Find the backward edge and increase capacity by 1
            Edge backward = findEdge(nodes.get(to), nodes.get(from));
            backward.setCapacity(e.getCapacity() + 1);
        }

    }

    private static Edge findEdge(Node from, Node to) {
        for (Edge edge : from.getEdges()) {
            if (edge.getTo().equals(to)) return edge;
        }
        return null;
    }

}
