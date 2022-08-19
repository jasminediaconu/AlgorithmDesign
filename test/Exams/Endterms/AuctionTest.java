package Exams.Endterms;

import main.Exams.Endterms.Resit2019.FindSolution.AuctionTime;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AuctionTest {
    @Test(timeout = 100)
    public void example() {
        int n = 3;
        int V = 5;
        int[] v = { 0, 3, 4, 2 };
        int[][] M = {
                { 0, 1, 2, 3, 4, 5 },
                { 0, 1, 2, 0, 1, 2 },
                { 0, 1, 2, 0, 0, 2 },
                { 0, 1, 0, 0, 0, 0 } };
        List<Integer> chosenElements = new LinkedList<>();
        chosenElements.add(3);
        chosenElements.add(1);
        assertEquals("Should pick the first and last item", chosenElements, AuctionTime.solve(n, V, v, M));
    }
}
