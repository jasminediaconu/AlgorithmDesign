package main.Exams.Endterms.Resit2020.NegativeCycles;

import main.Exams.Endterms.Resit2020.NegativeCycles.Road;
import main.Exams.Endterms.Resit2020.NegativeCycles.Town;

import java.util.Set;

/**
 * Now that it looks like the camping grounds will open again for summer,
 * you are starting to plan your annual biking trip.
 * Having visited Paris, Berlin, and Copenhagen before, you have now set
 * your sights on London.
 * Your last few trips have taught you however, that it might be possible
 * to earn some money along the way.
 *
 * To that end you have made a thing you decided to call a chingching-map.
 * Like a regular map you have plotted the different towns and cities that
 * you plan to visit on it.
 * Instead of using the directed edges to denote the distance between these
 * towns and cities however, you instead mark the amount of money you need
 * to spend to travel across that road (paying for ferries, tolls, etc).
 * However there are also directed roads that can earn you some money. There
 * are stores that want you to deliver some goods, people that need “things”
 * transported from one city to another (like some good old books) etc.
 * You denote these with negative weights, indicating that instead of using
 * money you actually get money.
 *
 * Since you figure that you need a lot of money in London, you wonder if
 * there is some loop in the graph that you can bike a large number of times
 * to grow your amount of money arbitrarily large.
 *
 * Implement the method toLondonWeGo() that returns true iff there is a loop
 * in the chingching-map that allows you to gain infinite amounts of money.
 */
public class ChingChingMap {
    /**
     * You should implement this method.
     *
     * @param n the number of nodes.
     * @param m the number of edges.
     * @param V the array of towns at indices 1 <= i <= n.
     * @param E a set of Edges
     * @return true iff there is a cycle that allows you to get infinite amounts of money.
     */
    public static boolean solve(int n, int m, Town[] V, Set<Road> E) {
        int[] distance = new int[n+1];

        int infinity = Integer.MAX_VALUE/2;

        // Initialize distances
        for (int i = 1; i <= n; i++) distance[i] = infinity;

        distance[1] = 0;

        // Compute the shortest path
        for (int i = 1; i <= n; i++) {
            for(Road r : E) {
                int u = r.from.getId();
                int v = r.to.getId();
                int weight = r.getCost();
                if (distance[u] != infinity && distance[u] + weight < distance[v])
                    distance[v] = distance[u] + weight;
            }
        }

        // Look for negative weight cycles
        for(Road r : E) {
            int u = r.from.getId();
            int v = r.to.getId();
            int weight = r.getCost();
            if (distance[u] != infinity && distance[u] + weight < distance[v])
                return true;
        }

        return false;
    }
}
