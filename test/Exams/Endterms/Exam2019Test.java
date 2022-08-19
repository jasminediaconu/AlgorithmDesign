package Exams.Endterms;

import main.Exams.Endterms.Endterm2019.BuildNF.BuildNF;
import main.Exams.Endterms.Endterm2019.DynamicProgramming.DynamicProgramming;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exam2019Test {
    @Test
    public void example() {
        int m = 4;
        int c = 8;
        int[][] R = {
                { 0, 0, 0, 0, 0 },
                { 0, 0, 2, 3, 4 },
                { 0, 0, 0, 2, 3 },
                { 0, 0, 0, 0, 2 },
                { 0, 0, 0, 0, 0 } };
        assertEquals(12, DynamicProgramming.solve(m, c, R));
    }

    @Test(timeout = 100)
    public void example02() {
        int m = 4;
        int c = 8;
        int[][] R = { { 0, 0, 0, 0, 0 },
                { 0, 0, 2, 100, 100 },
                { 0, 0, 0, 2, 100 },
                { 0, 0, 0, 0, 2 },
                { 0, 0, 0, 0, 0 } };
        assertEquals(20, DynamicProgramming.solve(m, c, R));
    }

    @Test(timeout = 100)
    public void example03() {
        int n = 3;
        int C = 5;
        int[] b = { 0, 8, 7, 2 };
        int[] c = { 0, 3, 3, 2 };
        int[][] M =
                {
                        { 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 8, 8, 8 },
                        { 0, 0, 0, 8, 8, 8 },
                        { 0, 0, 2, 8, 8, 10 }
                };
        List<Integer> chosenElements = new LinkedList<>();
        chosenElements.add(1);
        chosenElements.add(3);
        assertEquals(chosenElements, DynamicProgramming.solve(n, C, b, c, M));
    }

    @Test(timeout = 100)
    public void exampleNF() {
        int n = 2;
        int[] b = { 0, 8, 2 };
        int[] c = { 0, 2, 4 };
        int[][] dep = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        assertEquals(6, BuildNF.solve(n, b, c, dep));
    }

    @Test(timeout = 100)
    public void exampleNF02() {
        int n = 2;
        int[] b = { 0, 8, 2 };
        int[] c = { 0, 2, 4 };
        int[][] dep = { { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 } };
        assertEquals(4, BuildNF.solve(n, b, c, dep));
    }
}
