package main.EndtermPreparation.NetworkFlow.Aeronauts;

import java.util.ArrayList;

public class Aeronauts {
    /**
     * @param l the number of flights Lee has already done
     * @param n the number of competitors
     * @param m the number of open slots left
     * @param flights the number of flights each of the competitors has done. You should use flights[1] to flights[n]
     * @param compatible 2D boolean array such that slot i can be used by competitor j iff compatible[i][j] is true. Note that compatible[0][x] and compatible[x][0] should not be used.
     * @return
     */
    public static boolean solve(int l, int n, int m, int[] flights, boolean[][] compatible) {
        // Let's build up the graph!!
        // Step 1: sink and source nodes
        Node s = new Node(0, -(l+1));
        Node t = new Node(n+m+1, l+1);

        ArrayList<Node> nodes = new ArrayList<>();

        // Step 2: add nodes for slots
        for(int i = 1; i <= m; i++) {
            Node slot = new Node(i, 0);
            nodes.add(slot);
            s.addEdge(slot, 0, 1);
        }

        for(int i = m+1; i <= n+m; i++) {
            Node aeronaut = new Node(i, 0);
            nodes.add(aeronaut);

            // If an opponent has already at least l wins, Lee already lost
            if(flights[i-m] >= l) return false;
            aeronaut.addEdge(t, 0,  l -flights[i-m] - 1);
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                Node slot = nodes.get(i-1);
                Node aeronaut = nodes.get(j+m-1);
                if(compatible[i][j]) slot.addEdge(aeronaut, 0, 1);
            }
        }

        Graph g = new Graph(nodes, s, t);

        MaxFlow.maximizeFlow(g);

        for(Edge e : g.getSource().getEdges()) {
            if(e.getResidual() > 0) return false;
        }

        return true;
    }
}
