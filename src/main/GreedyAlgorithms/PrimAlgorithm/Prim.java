package main.GreedyAlgorithms.PrimAlgorithm;

import java.util.*;

public class Prim {

    public static int shortestPath(Graph g, Vertex v) {
        int n = g.getAllVertices().size();
        int totalWeight = 0;

        // Create MST Set
        Set<Vertex> mstSet = new HashSet<Vertex>();

        // Create adaptable PQ
        AdaptablePQ pq = new AdaptablePQ();

        int[] distances = new int[n];

        for (int i = 0; i < distances.length; i++) distances[i] = Integer.MAX_VALUE;

        pq.insertOrReplace(v, 0);
        distances[v.getId()] = 0;

        while(mstSet.size() != n && !pq.isEmpty()) {
            VertexNumPair first = pq.removeMin();

            mstSet.add(first.getVertex());
            totalWeight += first.getNum();

            for(VertexNumPair u : first.getVertex().getNeighbours()) {
                int currShort = first.getNum() + u.getNum();
                if (!mstSet.contains(u.getVertex()) &&
                        distances[u.getVertex().getId()] > u.getNum()) {
                        pq.insertOrReplace(u.getVertex(), u.getNum());
                        distances[u.getVertex().getId()] = currShort;
                    }
                }
            }

        return totalWeight;
    }

}
