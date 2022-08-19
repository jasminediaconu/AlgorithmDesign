package main.EndtermPreparation.DynamicProgramming.ShorthestPaths;

import java.util.Objects;
import java.util.Set;

public class LoopWithNetNegativePoints {
    /**
     * You should implement this method.
     *
     * @param n the number of intersections.
     * @param m the number of edges.
     * @param E a set of directed Edges, you may assume the endpoints are labelled 1 <= label <= n.
     * @return true iff there is a negative net loop in the set of roads.
     */
    public static boolean solve(int n, int m, Set<Road> E) {
        int dp[][] = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
               }
            }

        // Initialize the matrix
        for(Road r : E) dp[r.from][r.to] = r.points;

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    int negative = dp[i][k] + dp[k][j];
                    if(dp[i][j] > negative) {
                        if(negative < 0) return true;
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }
        return false;
    }
}

class Road {

    protected int points;

    protected int from;

    protected int to;

    protected Road(int from, int to, int points) {
        this.from = from;
        this.to = to;
        this.points = points;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Road edge = (Road) o;
        return points == edge.points && from == edge.from && to == edge.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, from, to);
    }
}