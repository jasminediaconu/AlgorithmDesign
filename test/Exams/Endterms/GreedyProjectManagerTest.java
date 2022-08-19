package Exams.Endterms;

import main.Exams.Endterms.Endterm2020.GreedyProjectManager.GreedyProjectManager;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GreedyProjectManagerTest {
    @Test(timeout = 100)
    public void example() {
        int n = 2;
        int[] b = { 0, 8, 2 };
        int[] c = { 0, 2, 4 };
        int[][] dep = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        Set<Integer> answer = GreedyProjectManager.outputSelection(n, b, c, dep);
        assertEquals("Should take only project 1 as that has a net profit: Size", 1, answer.size());
        assertTrue("Should take only project 1 as that has a net profit: Project 1", answer.contains(1));
    }

    @Test
    public void example02() {
        int n = 2;
        int[] b = { 0, 8, 2 };
        int[] c = { 0, 2, 4 };
        int[][] dep = { { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 } };
        Set<Integer> answer = GreedyProjectManager.outputSelection(n, b, c, dep);
        assertEquals("Project 1 (profit 6) now requires 2 (loss 2), so we take both", 2, answer.size());
        assertTrue("Project 1 should be included", answer.contains(1));
        assertTrue("Project 2 should be included", answer.contains(2));
    }
}
