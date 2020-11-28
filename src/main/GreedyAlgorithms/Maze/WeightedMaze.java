package main.GreedyAlgorithms.Maze;

import org.openjdk.jmh.annotations.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * A maze is represented by a directed (and weighted, but you only need that for the next problem)
 * graph G=(V,E), where V denotes the set containing n vertices and E the set containing m directed edges.
 * Each vertex represents an intersection or end point in the maze and the edges represent paths between them.
 * A directed edge is used for (downhill) tunnels and holes that you can jump into,
 * but where it is impossible to get back.
 * Because of this, it may become impossible to reach the exit.
 *
 * Some edges take longer than others, which is expressed in their weight.
 *
 * Additionally the sheer number of options you can chose from in every vertex overwhelms you quite a bit,
 * so every vertex takes 1 time step per outgoing edge (because you have to find out what is the correct one).
 *
 * Design and implement an algorithm that determines the path from s to t that takes the least amount of time
 * (which is the sum of lengths of all edges plus for all vertices (except t) the number of outgoing edges).
 * Let the algorithm just print the total time of this path. Aim for the most efficient algorithm you can think of.
 * Extremely slow implementations will not be accepted.
 *
 * The input is structured the same as for Greedy task 0C: “Can we get out?”:
 *
 * The first line of the input contains four integers separated by a space: n, m, s and t.
 * The integers n and m are defined as above and you may assume the vertices are labelled 1 up to and including n.
 * E.g. if n equals 6, the vertices are labelled 1,2,3,4,5 and 6.
 * The integers s and t are the numbers of the vertices representing your starting point and the exit of the maze,
 * respectively.
 * The next m lines are the directed edges. Each line contains three integers: the two numbers of the vertices
 * associated with this edge and the length (i.e., positive weight) of the edge.
 * For example, a line containing the integers 3, 6 and 9 indicates that there is an edge from vertex 3 to vertex 6
 * with length 9.
 *
 * The output should be a single line with the time spent on the shortest path if one exists, or -1 otherwise.
 */
public class WeightedMaze {

    // Implement the solve method to return the answer to the problem posed by the inputstream.
    public static String run(InputStream in) {
        return new WeightedMaze().solve(in);
    }

    public String solve(InputStream in) {
        Scanner sc = new Scanner(in);
        /*
         * We already parse the input for you and should not need to make changes to this part of the code.
         * You are free to change this input format however.
         */
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        int t = sc.nextInt();

        ArrayList<HashMap<Integer, Integer>> nodes = new ArrayList<>();
        for (int i = 0; i <= n; i++) nodes.add(new HashMap<>());

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cost = sc.nextInt();
            nodes.get(u).put(v, cost);
        }

        sc.close();

        boolean foundPath = false;

        Queue<HashMap<Integer, Integer>> queue = new LinkedList<HashMap<Integer, Integer>>();

        queue.add(nodes.get(s));

        while(!queue.isEmpty()) {
            HashMap<Integer, Integer> first = queue.poll();

            if(first == nodes.get(t)){
                foundPath = true;
                break;
            }

            for (Map.Entry<Integer,Integer> neighbour : first.entrySet()) {
                queue.add(nodes.get(neighbour.getKey()));
            }
        }

        if(!foundPath) return "-1";

        Set<HashMap<Integer, Integer>> sptSet = new HashSet<HashMap<Integer, Integer>>();

        int[] distances = new int[n+1];
        boolean[] shortestPath = new boolean[n+1];

        for(int i = 0; i <= n; i++) {
            distances[i] = Integer.MAX_VALUE;
            shortestPath[i] = false;
        }

        distances[s] = 0;
        shortestPath[0] = true;

        int currentMin = -1;

        while (sptSet.size() != n) {

            int index = findNextMin(distances, shortestPath);

            if(shortestPath[index]) continue;

            shortestPath[index] = true;

            if(index == t) break;

            currentMin = distances[index];

            HashMap<Integer, Integer> first = nodes.get(index);

            for (Map.Entry<Integer,Integer> neighbour : first.entrySet()) {
                int key = neighbour.getKey();
                int weight = neighbour.getValue();
                int currentDist = currentMin + weight + first.size();
                if(distances[key] > currentDist)
                    distances[key] = currentDist;
            }
        }


        return "" + distances[t];
    }

    public static int findNextMin(int[] array, boolean[] shortestPath) {
        int min = Integer.MAX_VALUE;
        int index = -1;

        for(int i = 1; i < array.length; i++) {
            if(!shortestPath[i] && array[i] <= min) {
                index = i;
                min = array[i];
            }
        }

        return index;
    }

    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class Runner {
        public static void main(String[] args) throws Exception {
            InputStream in = new FileInputStream("src/assets/output.txt");
            WeightedMaze.run(in);
            org.openjdk.jmh.Main.main(args);
        }
    }
}
