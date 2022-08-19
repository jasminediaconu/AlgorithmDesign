package main.Exams.Endterms.Endterm2020.Quest;

import java.lang.reflect.Array;
import java.util.*;

public class TheQuestForTheHolyGrail {
    /**
     *  You should implement this method.
     *  @param n the number of nodes.
     *  @param m the number of edges.
     *  @param g the index of the holy grail in V.
     *  @param V a list of Nodes, where V[1] is the current state and V[g] is the holy grail. You should not use V[0].
     *  @param E a set of Edges
     *  @return The shortest path from V[1] to V[g] where the collaborators are used at most once.
     */
    public static double solve(int n, int m, int g, Node[] V, Set<Edge> E) {
        double mem[][] = new double[n+1][2];

        // Initializing distances to nodes
        for (int i = 2; i <= n; i++) {
            mem[i][0] = Integer.MAX_VALUE;
            mem[i][1] = Integer.MAX_VALUE;
        }

        // Compute optimal values
        for (int i = 1; i < n; i++) {
            for(Edge e : E) {
                int u = e.getFrom().getId();
                int v = e.getTo().getId();
                int cost = e.getCost();
                if (mem[u][0] != Integer.MAX_VALUE && mem[u][0] + cost < mem[v][0]) {
                    mem[v][0] = mem[u][0] + cost;
                    mem[v][1] = Math.min(mem[v][1], Math.min(mem[u][1] + cost, mem[u][0] + cost * 0.5));
                }
            }
        }

        return mem[g][1];
    }
}

