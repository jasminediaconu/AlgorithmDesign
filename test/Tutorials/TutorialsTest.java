package Tutorials;

import main.MidtermPreparation.Tutorials.DivideAndConquer.*;
import main.MidtermPreparation.Tutorials.GreedyAlgorithms.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TutorialsTest {

    @Test
    public void example() {
        int n = 4;
        int[] runtimes = { 0, 41, 29, 12, 26 };
        int[] endtimes = { 0, 12, 38, 67, 108 };
        assertArrayEquals( endtimes, AverageFinishingTime.minAvgEndtimes(n, runtimes));
    }

    @Test
    public void knapsack() {
        int n = 2;
        int w = 5;
        int[] num = { 0, 2, 3 };
        int[] weight = { 0, 2, 1 };
        assertEquals(3, KnapsackProblem.run(n, w, num, weight));
    }

    @Test
    public void knapsack2() {
        int n = 4;
        int w = 50;
        int[] num = { 0, 2, 3, 18, 7 };
        int[] weight = { 0, 2, 1, 4, 21 };
        assertEquals(4, KnapsackProblem.run(n, w, num, weight));
    }

    @Test
    public void knapsack3() {
        int n = 4;
        int w = 30;
        int[] num = { 0, 1, 1, 1, 1 };
        int[] weight = { 0, 4, 5, 9, 21 };
        assertEquals(2, KnapsackProblem.run(n, w, num, weight));
    }

    @Test
    public void knapsack4() {
        int n = 2;
        int w = 30;
        int[] num = { 0, 30, 100 };
        int[] weight = { 0, 1, 42 };
        assertEquals(30, KnapsackProblem.run(n, w, num, weight));
    }

    @Test
    public void irrigation() throws IOException {
        InputStream in = new FileInputStream("src/assets/MidtermPreparation.GreedyAlgorithms/irrigation.txt");
        Assert.assertEquals("122", IrrigationArea.run(in));
    }

    @Test
    public void kruskalMST() throws FileNotFoundException {
        String fileName = "src/assets/MidtermPreparation.GreedyAlgorithms/example";
        Scanner sc = new Scanner(new FileInputStream(fileName + ".in"));
        int n = sc.nextInt();
        Edge[] graph = KruskalMST.makeGraph(sc);
        Edge[] phorest = KruskalMST.makeGraph(sc);
        assertEquals("19", KruskalMST.run(n, graph, phorest));
    }

    @Test
    public void testExampleA() {
        int[] a = new int[] { 3, -2, 6 };
        int[] b = new int[] { 2, 5, 8 };
        int[][] c = FastBinaryExponentiation.computeC(a, b);
        int[][] expected = new int[][] { { 9, 243, 6561 }, { 4, -32, 256 }, { 36, 7776, 1679616 } };
        assertArrayEquals(expected, c);
    }

    @Test
    public void testPartialSumsA() {
        Integer[] arr = new Integer[] { 1, 2 };
        Set<Integer> res = PartialSums.partialSums(arr);
        Set<Integer> expected = new HashSet<>(Arrays.asList(0, 1, 2, 3));
        assertEquals(expected, res);
    }

    @Test
    public void testPartialSumsB() {
        Integer[] arr = new Integer[] { 1, 2, 3, 4 };
        Set<Integer> res = PartialSums.partialSums(arr);
        Set<Integer> expected = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        assertEquals(expected, res);
    }

    @Test
    public void testPartialSumsC() {
        Integer[] arr = new Integer[] { 9, 10 };
        Set<Integer> res = PartialSums.partialSums(arr);
        Set<Integer> expected = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        assertEquals(expected, res);
    }

    @Test
    public void testPartialSumsEmpty() {
        Integer[] arr = new Integer[] {};
        Set<Integer> res = PartialSums.partialSums(arr);
        Set<Integer> expected = new HashSet<>(Collections.singletonList(0));
        assertEquals(expected, res);
    }

    @Test
    public void maximumSubarrayA() {
        int[] input = new int[] { 2, -3, 2, 1 };
        assertEquals(3, MaximumSubarray.largestSum(input));
    }

    @Test
    public void maximumSubarrayB() {
        int[] input = new int[] { 3, -3, -2, 42, -11, 2, 4, 4 };
        assertEquals(42, MaximumSubarray.largestSum(input));
    }

    @Test
    public void optimalDecisionTree() {
        List<Pair<Integer[], Integer>> samples = new ArrayList<>();
        samples.add(new Pair<Integer[], Integer>(new Integer[] { 0, 0, 0 }, 0));
        samples.add(new Pair<Integer[], Integer>(new Integer[] { 0, 1, 0 }, 1));
        assertEquals(2, OptimalDecisionTree.decisionTree(1, samples));
    }

}
