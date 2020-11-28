package main.GreedyAlgorithms.Maze;

import org.openjdk.jmh.annotations.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WeightedMazeWC {
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

            for (HashMap<Integer, Integer> neighbours : nodes) {
                for (Map.Entry<Integer,Integer> neighbour : neighbours.entrySet()) {
                    int key = neighbour.getKey();
                    int weight = neighbour.getValue();
                    int currentDist = currentMin + weight + first.size();
                    if(distances[key] > currentDist)
                        distances[key] = currentDist;
                }

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
