package main.MidtermPreparation.GreedyAlgorithms.DonutEmporium;

import java.io.InputStream;
import java.util.*;

/**
 * You are the owner of a new store chain (called Amazing Donuts, or AD for short),
 * and business is running well, so you decide to open up some more stores.
 * You decide to do this in the town of Scatterville,
 * where the houses of all residents are literally scattered around the village.
 *
 * Of course, you want to reach as many people with your donuts as possible.
 * Since you want to place k new stores in Scatterville,
 * you decide to divide the houses in k “clusters”,
 * and each cluster will get its own store.
 * Every house will belong to exactly one store cluster.
 *
 * Given the coordinates of the houses in Scatterville, and how many stores you will open,
 * can you calculate where the new stores should be placed?
 *
 * Example
 * Consider the following example.
 * Here, three stores will be opened in Scatterville.
 * The first and second clusters contain four houses each,
 * while the third cluster only contains two houses.
 * The stores will be placed at the centers of these clusters.
 */
public class DonutEmporium {
    // Implement the solve method to return the answer to the problem posed by the inputstream.
    public static String run(InputStream in) {
        return new DonutEmporium().solve(in);
    }

    public String solve(InputStream in) {
        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        List<House> houses = new ArrayList<>(n);

        for (int i = 0; i < n; i++) houses.add(new House(i, sc.nextInt(), sc.nextInt()));

        int m = n * (n - 1) / 2;

        List<Distance> distances = new ArrayList<>(m);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++)
                distances.add(new Distance(houses.get(i), houses.get(j)));
        }

        Collections.sort(distances, Comparator.comparingLong(a -> a.distance));

        UnionFind unionFind = new UnionFind(houses);

        int initCount = 0;
        int finalCount = n - k;

        while (initCount != finalCount && !distances.isEmpty()) {
            Distance d = distances.remove(0);

            if (!hasCycle(d.a, d.b, unionFind)) initCount++;
        }

        Collection<List<House>> clusters = unionFind.clusters();

        float[] xCoord = new float[clusters.size()];
        float[] yCoord = new float[clusters.size()];

        int i = 0;

        for(List cluster : clusters) {
            float size = cluster.size();
            for(int j = 0; j < size; j++) {
                xCoord[i] += ((House) cluster.get(j)).x;
                yCoord[i] += ((House) cluster.get(j)).y;
            }
            xCoord[i] = xCoord[i]/size;
            yCoord[i] = yCoord[i]/size;
            System.out.println(xCoord[i] + " " + yCoord[i]);
            i++;
        }

        StringBuilder sb = new StringBuilder();
        for(int l = 0; l < clusters.size(); l++) {
            sb.append(xCoord[l] + " " + yCoord[l] + "\n");
        }

        return sb.toString();
    }

    public static boolean hasCycle(House source, House destination, UnionFind unionFind) {
        House root = unionFind.find(source);
        House root2 = unionFind.find(destination);

        if (root == root2) return true;

        else unionFind.join(source, destination);

        return false;
    }
}

class House {

    int id, x, y;

    public House(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}

class Distance {

    House a, b;

    long distance;

    public Distance(House a, House b) {
        this.a = a;
        this.b = b;
        // Square Euclidean distance, to avoid floating-point errors
        this.distance = (long) (a.x - b.x) * (a.x - b.x) + (long) (a.y - b.y) * (a.y - b.y);
    }
}

class UnionFind {

    private List<House> houses;

    private int[] parent;

    private int[] rank;

    UnionFind(List<House> houses) {
        this.houses = houses;
        int n = houses.size();
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    /**
     * Joins two disjoint sets together, if they are not already joined.
     * @return false if x and y are in same set, true if the sets of x and y are now joined.
     */
    boolean join(House x, House y) {
        int xrt = find(x.id);
        int yrt = find(y.id);
        if (rank[xrt] > rank[yrt])
            parent[yrt] = xrt;
        else if (rank[xrt] < rank[yrt])
            parent[xrt] = yrt;
        else if (xrt != yrt)
            rank[parent[yrt] = xrt]++;
        return xrt != yrt;
    }

    /**
     * @return The house that is indicated as the "root" of the set of house h.
     */
    House find(House h) {
        return houses.get(find(h.id));
    }

    private int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    /**
     * @return All clusters of houses
     */
    Collection<List<House>> clusters() {
        Map<Integer, List<House>> map = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            int root = find(i);
            if (!map.containsKey(root))
                map.put(root, new ArrayList<>());
            map.get(root).add(houses.get(i));
        }
        return map.values();
    }
}
