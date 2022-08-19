package Exams.Endterms;

import main.Exams.Endterms.Resit2019.NetworkFlow.DelftFlex;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DelftFlexTest {
    @Test(timeout = 100)
    public void example_onestudent_onetask() {
        int n = 1;
        int m = 1;
        int[][] h = { {}, { 8, 15 } };
        int[] w = { 0, 10 };
        int[][] s = { { 0, 0 }, { 0, 1 } };
        /*
         * This test models the situation where:
         * Student 1 can work for 8 to 15 hours in total and has the skills to work on task 1.
         * Task 1 takes 10 hours.
         * This is doable by having student 1 work on task 1 for 10 hours.
         */
        assertTrue(DelftFlex.solve(n, m, h, w, s));
    }

    @Test(timeout = 100)
    public void example() {
        int n = 2;
        int m = 2;
        int[][] h = { {}, { 8, 15 }, { 2, 6 } };
        int[] w = { 0, 10, 8 };
        int[][] s = { { 0, 0, 0 }, { 0, 1, 1 }, { 0, 0, 1 } };
        /*
         * This test models the situation where:
         * Student 1 can work for 8 to 15 hours in total and has the skills to work on task 1 and task 2.
         * Student 2 can work for 2 to 6 hours in total and has the skills to work on task 2 only.
         * Task 1 takes 10 hours and task 2 takes 8.
         * This is doable by having student 1 work on task 1 for 10 hours and on task 2 for 2. Student 2 does the remaining 6 hours of work on task 2.
         */
        assertTrue(DelftFlex.solve(n, m, h, w, s));
    }

    @Test(timeout = 100)
    public void example_notdoable() {
        int n = 2;
        int m = 2;
        int[][] h = { {}, { 8, 15 }, { 2, 6 } };
        int[] w = { 0, 10, 8 };
        int[][] s = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
        /*
         * This test models the situation where:
         * Student 1 can work for 8 to 15 hours in total and has the skills to work on task 1 only.
         * Student 2 can work for 2 to 6 hours in total and has the skills to work on task 2 only.
         * Task 1 takes 10 hours and task 2 takes 8.
         * This is not doable as we cannot do task 2.
         */
        assertFalse(DelftFlex.solve(n, m, h, w, s));
    }
}
