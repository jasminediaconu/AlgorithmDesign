package Exams.Midterms.Resit2018;

import main.Exams.Midterms.Resit2018.HighPriority;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class HighPriorityTest {

    @Test
    public void example() {
        int n = 2;
        int[] t = { 0, 2, 3 };
        int[] p = { 0, 5, 2 };
        int[] S = { 0, 0, 20 };
        int[] I = { 0, 2, 1 };
        /* The input represents the following:
         * Start the job with t=3 and p=2 at time 0.
         * Start the job with t=2 and p=5 at time 20.
         */
        assertEquals("One job to postpone", 1, HighPriority.solve(n, t, p, S, I, 17));
        assertEquals("One job to postpone", 1, HighPriority.solve(n, t, p, S, I, 20));
        assertEquals("No jobs to postpone", 0, HighPriority.solve(n, t, p, S, I, 21));
        assertEquals("No jobs to postpone", 0, HighPriority.solve(n, t, p, S, I, 25));
    }

    @Test
    public void example_one_job() {
        int n = 1;
        int[] t = { 0, 10 };
        int[] p = { 0, 32 };
        int[] S = { 0, 8 };
        int[] I = { 0, 1 };
        assertEquals("One job to postpone", 1, HighPriority.solve(n, t, p, S, I, 7));
        assertEquals("No jobs to postpone", 0, HighPriority.solve(n, t, p, S, I, 10));
    }

    @Test
    public void example_three_jobs() {
        int n = 3;
        int[] t = { 0, 2, 3, 5 };
        int[] p = { 0, 5, 2, 8 };
        int[] S = { 0, 0, 3, 5 };
        int[] I = { 0, 2, 1, 3 };
        /* The input represents the following:
         * Start the job with t=3 and p=2 at time 0.
         * Start the job with t=2 and p=5 at time 3.
         * Start the job with t=5 and p=8 at time 5.
         */
        assertEquals("Two jobs to postpone", 2, HighPriority.solve(n, t, p, S, I, 2));
        assertEquals("One job to postpone", 1, HighPriority.solve(n, t, p, S, I, 4));
        assertEquals("No jobs to postpone", 0, HighPriority.solve(n, t, p, S, I, 6));
        int[] correctanswers = { 3, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 };
        for (int x = 0; x < correctanswers.length; x++) {
            assertEquals(correctanswers[x], HighPriority.solve(n, t, p, S, I, x));
        }
    }
}
