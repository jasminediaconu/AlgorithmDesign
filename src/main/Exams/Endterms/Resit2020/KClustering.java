package main.Exams.Endterms.Resit2020;

import java.util.*;

/**
 *
 * In these uncertain times you have taken to playing online boardgames with your
 * friends and family. After playing for a few weeks however, the group of people
 * interested in joining your Friday Games sessions has grown quite large.
 * The group is so large, that the speed and fun of the game is suffering.
 * It turns out that this speed is determined by the player who has the poorest network
 * connection. Obviously, you need to split up to play the games in smaller groups.
 * You decide to do this using a peer-to-peer implementation instead of relying on the
 * server.
 *
 * The quality of a game using the peer-to-peer clients depends on the network delay
 * (ping time) between the players in the game as follows: the highest quality is obtained
 * if all clients are (indirectly) connected to each other while minimising the sum of
 * the network delays of the used connections. To make matters worse for your planning,
 * some people have already decided that no matter what, they want to be directly connected.
 *
 * You have access to n premium accounts for the website and so want to create a total of
 * n groups. You are given the group of k≥n people, the ping times between them,
 * and the connections that you have to use as these players want to be in the same group
 * no matter what. Implement the function lanParty that given this information computes
 * the (minimum) sum of the ping times of the extra connections you need to make to create
 * n connected groups.
 *
 */
public class KClustering {
    /**
     *
     * You should implement this method.
     * @param n the number of groups that need to be created.
     * @param k the total number of friends.
     * @param c 2D-array with all the costs of connecting people, g[i][j] represents the costs of connecting friend \\(1 \leq i \leq n\\) with friend \\(1 \leq j \leq n\\). You should ignore g[0][j] and g[i][0].
     * @param e 2D-array with all connections that already exist, g[i][j] is true iff there is already a _direct_ connection between friend \\(1 \leq i \leq n\\) and friend \\(1 \leq j \leq n\\). You should ignore e[0][j] and e[i][0].
     * @return the costs of the connections that still need to be made.
     *
     */
    public static int lanParty(int n, int k, int[][] c, boolean[][] e) {
        // Find number of possible connections
        int size = k * (k - 1) / 2;

        // Check how many participants are already connected
        int alreadyConnected = 0;

        List<Integer> participants = new ArrayList<>();
        for(int i = 0; i < k; i++) participants.add(i);

        // Create clusters
        UnionFind unionFind = new UnionFind(participants);

        // 1. Add all the distances in a list and sort them
        List<Distance> distances = new ArrayList<>(size);
        for(int i = 1; i <=k; i++) {
            for(int j = i+1; j<=k; j++) {
                int first = participants.get(i-1);
                int second = participants.get(j-1);
                // If participants are already connected don't add distances,
                // put them in the same cluster.
                if(e[i][j]) {
                    unionFind.join(first, second);
                    alreadyConnected++;
                }
                else {
                    distances.add(new Distance(first, second, c[i][j]));
                }
            }
        }

        Collections.sort(distances);

        int initCount = 0;
        int minDistance = 0;
        // Number of connections still required
        int finalCount = k - n - alreadyConnected;

        // Traverse list, remove smallest distance
        while (initCount != finalCount && !distances.isEmpty()) {
            Distance d = distances.remove(0);
            // If the participants are already connected, don't increase count
            if (!hasCycle(d.a, d.b, unionFind)) {
                initCount++;
                minDistance += d.distance;
            }
        }

        return minDistance;
    }

    public static boolean hasCycle(int source, int destination, UnionFind unionFind) {
        int root = unionFind.find(source);
        int root2 = unionFind.find(destination);

        if (root == root2) return true;

        else unionFind.join(source, destination);

        return false;
    }
}

class Distance implements Comparable<Distance>{

    int a, b;

    int distance;

    public Distance(int a, int b, int distance) {
        this.a = a;
        this.b = b;
        this.distance = distance;
    }

    @Override
    public int compareTo(Distance o) {
        return this.distance - o.distance;
    }
}

class UnionFind {

    private List<Integer> participants;

    private int[] parent;

    private int[] rank;

    UnionFind(List<Integer> participants) {
        this.participants = participants;
        int n = participants.size();
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    /**
     * Joins two disjoint sets together, if they are not already joined.
     * @return false if x and y are in same set, true if the sets of x and y are now joined.
     */
    boolean join(int x, int y) {
        int xrt = find(x);
        int yrt = find(y);
        if (rank[xrt] > rank[yrt])
            parent[yrt] = xrt;
        else if (rank[xrt] < rank[yrt])
            parent[xrt] = yrt;
        else if (xrt != yrt)
            rank[parent[yrt] = xrt]++;
        return xrt != yrt;
    }

    int find(int p) {
        return participants.get(findElement(p));
    }

    private int findElement(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }
}