package main.Exams.Endterms.Endterm2019.BuildNF;

import java.util.ArrayList;

public class BuildNF {
    /**
     *   You should implement the method below. Note that you can use the graph structure below.
     *   @param n The number of code changes.
     *   @param benefits An array of dimension n+1 containing the benefits of all the code changes for 1 <= i <= n
     *   @param costs An array of dimension n+1 containing the costs of all the code changes for 1 <= i <= n
     *   @param dependencies is an array of dimension (n+1)*(n+1) that contains value 1 iff code change i depends on j and 0 otherwise, for all 1 <= i,j <= n.
     *   @return
     */
    public static int solve(int n, int[] benefits, int[] costs, int[][] dependencies) {
        int maxBenefits = 0;

        // Compute max benefits
        for(int i = 1; i <= n; i++) maxBenefits += benefits[i];

        // Create source and sink nodes
        Node source = new Node(-1);
        Node sink = new Node(-2);

        ArrayList<Node> nodes = new ArrayList<>();

        nodes.add(source);

        // Create job nodes, connect them to the source and sink
        for(int i = 1; i <= n; i++) {
            Node job = new Node(i);
            nodes.add(job);
            source.addEdge(job, benefits[i]);
            job.addEdge(sink, costs[i]);
        }

        // Add dependencies between them
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(dependencies[i][j] == 1)
                    nodes.get(i).addEdge(nodes.get(j), Integer.MAX_VALUE);
            }
        }

        nodes.add(sink);

        Graph g = new Graph(nodes, source, sink);

        MaxFlow.maximizeFlow(g);

        // Find max profit
        int maxFlow = 0;

        for(Edge e : g.getSource().getEdges()) {
            maxFlow += e.getFlow();
        }

        // The total benefit is given from the total possible one - the costs (maxFlow).
        return maxBenefits - maxFlow;
    }

}








































//
//    int totalBenefits = 0;
//
//        for(int i = 1; i <= n; i++) totalBenefits += benefits[i];
//
//                Node s = new Node(0);
//                Node t = new Node(n+1);
//
//                ArrayList<Node> nodes = new ArrayList<>();
//        nodes.add(s);
//
//        for(int i = 1; i <= n; i++) {
//        Node job = new Node(i);
//        s.addEdge(job, benefits[i]);
//        job.addEdge(t, costs[i]);
//        nodes.add(job);
//        }
//
//        for(int i = 1; i <= n; i++) {
//        for(int j = 1; j <= n; j++) {
//        if(dependencies[i][j] == 1) {
//        nodes.get(i).addEdge(nodes.get(j), Integer.MAX_VALUE);
//        }
//        }
//        }
//
//        nodes.add(t);
//
//        Graph g = new Graph(nodes, s, t);
//
//        MaxFlow.maximizeFlow(g);
//
//        int maxFlow = 0;
//
//        for(Edge e : g.getSource().getEdges()) {
//        maxFlow += e.flow;
//        }
//
//        return totalBenefits - maxFlow;