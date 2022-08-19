package main.EndtermPreparation.DynamicProgramming.ShorthestPaths;

import main.Exams.Endterms.Endterm2020.Quest.Edge;

import java.util.Arrays;

public class LongestPath {
    public static int findLongestPath(int n, Edge[] E) {

        int mem[] = new int[n+1];
        Arrays.fill(mem, Integer.MIN_VALUE);

        mem[0] = 0;
        mem[1] = 0;

        /**
         * 1. Traverse the nodes, for each node v, traverse its incoming edges u'.
         * 2. If we already visited u', then we can compute the distance between
         *    u + 1 and current distance at v. Take the maximum between the two, this will give us
         *    the longest path.
         */
        for(int i = 2; i <= n; i++) {
            for(Edge e : E) {
                if(e.getTo().getId() != i) continue;
                int from = e.getFrom().getId();
                if(mem[from] != Integer.MIN_VALUE) {
                    mem[i] = Math.max(mem[i], mem[from] + 1);
                }
            }
        }

        return mem[n];
    }
}
