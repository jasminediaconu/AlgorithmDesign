package main.EndtermPreparation.NetworkFlow.ImageSegmentation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ImageSegmentation {
    public static int solve(InputStream in) {
        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        Node source = new Node(0);
        Node sink = new Node(n+1);

        ArrayList<Node> nodes = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            int id = sc.nextInt();
            int f_i = sc.nextInt();
            int b_i = sc.nextInt();

            Node node = new Node(id);
            nodes.add(node);
            source.addEdge(node, f_i);
            node.addEdge(sink, b_i);
        }

        nodes.add(sink);

        while (sc.hasNextInt()) {
            Node from = nodes.get(sc.nextInt());
            Node to = nodes.get(sc.nextInt());
            from.addEdge(to, 10);
            to.addEdge(from, 10);
        }

        sc.close();

        Graph g = new Graph(nodes, source, sink);

        // Maximize the flow
        MaxFlow.maximizeFlow(g);

        int maxFlow = 0;
        for(Edge e : g.getSource().getEdges()) maxFlow += e.getFlow();

        return maxFlow;
    }
}
