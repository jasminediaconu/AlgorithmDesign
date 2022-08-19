package main.EndtermPreparation.DynamicProgramming.ShorthestPaths;

public class FloydWarshall {
    // dist[][] is the matrix containing the distances from vertex v to w
    public static int[][] findMinimumDistance(int[][] dist) {
        // n = number of vertices
        int n = dist.length;

        for(int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // check if the distance from vertex s to t is smaller if
                    // we go through an intermediate vertex k
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }
//
//    public static int[][] findMinimumDistance(int[][] d, int s, int n, int m) {
//            // n = number of vertices
//            int n = d.length;
//            int dist[] = new int[];
//
//            // Initialize distances from s to all other vertices as INFINITE
//            for (int i = 0; i < n; ++i) dist[i] = Integer.MAX_VALUE;
//
//            dist[s] = 0;
//
//            // The shortest path has at most V - 1 edges
//            for (int i = 1; i < n; ++i) {
//                for (int j = 0; j < m; ++j) {
//                    if (dist[i] != Integer.MAX_VALUE && dist[i] + d[i][j] < dist[j])
//                        dist[j] = dist[i] + d[i][j];
//                }
//            }
//
//            // Step 3: check for negative-weight cycles. The above
//            // step guarantees shortest distances if graph doesn't
//            // contain negative weight cycle. If we get a shorter
//            // path, then there is a cycle.
////            for (int j = 0; j < m; ++j) {
////                int u = graph.edge[j].src;
////                int v = graph.edge[j].dest;
////                int weight = graph.edge[j].weight;
////                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
////                    System.out.println("Graph contains negative weight cycle");
////                    return;
////                }
////            }
//        }
}
