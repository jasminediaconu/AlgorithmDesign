package GreedyAlgorithms;

import main.GreedyAlgorithms.BetterAverage;
import main.GreedyAlgorithms.DonutEmporium.DonutEmporium;
import main.GraphGenerator;
import main.GreedyAlgorithms.Maze.Maze;
import main.GreedyAlgorithms.Maze.WeightedMaze;
import main.GreedyAlgorithms.OptimalWorkstationUse.OptimalWorkstationUse;
import main.GreedyAlgorithms.PackingTrucks.PackingTrucks;
import main.GreedyAlgorithms.ParallelProcessing.ParallelProcessing;
import main.GreedyAlgorithms.PlanningBikeRepairs.PlanningBikeRepairs;
import main.GreedyAlgorithms.RoutingTrains.RoutingTrains;
import main.GreedyAlgorithms.UnionFind.UnionFind;
import main.GreedyAlgorithms.WirelessNetwork.WirelessNetwork;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {

    @Test
    public void example() {
        int n = 4;
        double[] list = { 4, 2, 1, 3 };
        assertEquals(2.5, BetterAverage.solve(n, list), 1e-3);
    }

    @Test
    public void maze() throws IOException {
        InputStream in = new FileInputStream("src/assets/maze.txt");
        assertEquals("yes", Maze.run(in));
    }

    @Test
    public void routingTrains() throws IOException {
        InputStream in = new FileInputStream("src/assets/routingTrains.txt");
        assertEquals("yes", RoutingTrains.run(in));
    }

    @Test
    public void weightedMaze() throws IOException {
        InputStream in = new FileInputStream("src/assets/GreedyAlgorithms/weightedMaze.txt");
        assertEquals("118", WeightedMaze.run(in));

        InputStream in2 = new FileInputStream("src/assets/GreedyAlgorithms/weightedMaze2.txt");
        assertEquals("850", WeightedMaze.run(in2));
    }

    @Test
    public void packingTrucks() {
        int n = 4;
        int[] weights = { 0, 41, 29, 12, 26 };
        int maxWeight = 48;
        assertEquals(3, PackingTrucks.minAmountOfTrucks(n, weights, maxWeight));
    }

    @Test
    public void planningBikeRepairs() throws IOException {
        InputStream in = new FileInputStream("src/assets/planningBikeRepairs.txt");
        assertEquals("3", PlanningBikeRepairs.run(in));
    }

    @Test
    public void parallelProcessing() {
        int n = 5;
        int m = 2;
        int[] deadlines = { 0, 3, 1, 1, 1, 2 };
        assertEquals(1, ParallelProcessing.solve(n, m, deadlines));
        assertEquals(1, ParallelProcessing.solve(n, m, deadlines));
    }

    @Test
    public void optimalWorkstationUse() {
        int n = 5;
        int m = 10;
        int[] start = { 0, 2, 1, 17, 3, 15 };
        int[] end = { 0, 6, 2, 7, 9, 6 };
        assertEquals(3, OptimalWorkstationUse.solve(n, m, start, end));
    }

    @Test
    public void optimalWorkstationUse2() {
        int n = 3;
        int m = 5;
        int[] start = { 0, 2, 1, 10 };
        int[] end = { 0, 3, 2, 14};
        assertEquals(0, OptimalWorkstationUse.solve(n, m, start, end));
    }

    @Test
    public void unionFind() {
        UnionFind uf = new UnionFind(10);
        assertEquals(9, uf.find(9));
        assertTrue(uf.union(1, 2));
        assertTrue(uf.union(2, 3));
        assertTrue(uf.union(0, 1));
        assertTrue(uf.union(3, 4));
        // Test that joining any combination will have no effect, as they are already joined
        for (int i = 0; i < 5; i++) for (int j = 0; j < 5; j++) assertFalse(uf.union(i, j), "union(" + i + "," + j + ")");
        // Test whether all first five entries have the same root
        for (int i = 0; i < 4; i++) assertEquals("find(" + i + ")", uf.find(i), uf.find(i + 1));
        // Test whether all last five entries have themselves as root
        for (int i = 5; i < 10; i++) assertEquals("find(" + i + ")", i, uf.find(i));
    }

    @Test
    public void rankNoUnionTest() {
        UnionFind uf = new UnionFind(10);
        // Test whether all entries have themselves as root
        for (int i = 0; i < 10; i++) assertEquals("find(" + i + ")", i, uf.find(i));
    }

    @Test
    public void findWithUnionTest() {
        UnionFind uf = new UnionFind(10);
        assertTrue(uf.union(4, 2));
        assertFalse(uf.union(2, 4));
        assertEquals(uf.find(2), uf.find(4));
    }

    @Test
    public void fastEnough() {
        int n = 1_000_000;
        UnionFind uf = new UnionFind(n);
        Random rng = new Random(1234);
        for (int i = 0; i < n / 100; i++) {
            int a = rng.nextInt(n);
            int b = rng.nextInt(n);
            uf.union(a, b);
        }
    }

    @Test
    public void wirelessNetwork() throws IOException {
        InputStream in = new FileInputStream("src/assets/wirelessNetwork.txt");
        assertEquals("15 2", WirelessNetwork.run(in));
    }

    @Test
    public void donutEmporium() throws IOException {
        InputStream in = new FileInputStream("src/assets/donutEmporium.txt");
        assertEquals("6.75 7\n" +
                "10 21\n" +
                "24 13.25", DonutEmporium.run(in));
    }

    @Test
    public void graphGenerator() {
        GraphGenerator.main(new String[]{"7", "11", "3", "0", "6"});
    }
}