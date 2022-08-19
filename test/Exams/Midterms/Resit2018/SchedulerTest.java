package Exams.Midterms.Resit2018;

import main.Exams.Midterms.Resit2018.Scheduler;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class SchedulerTest {

    @Test
    public void example() {
        int n = 2;
        int[] t = { 0, 1, 3 };
        int[] p = { 0, 5, 2 };
        int[] S = { 0, 0, 20 };
        int[] I = { 0, 1, 2 };
        /* The input represents the following:
         * Start the job with t=3 and p=2 at time 0.
         * Start the job with t=1 and p=5 at time 20.
         * Thus the last job finishes at time 26.
         */
        assertEquals("Do job 1 first, then job 2", 26, Scheduler.solve(n, t, p, S, I));
    }

    @Test
    public void example_one_job() {
        int n = 1;
        int[] t = { 0, 10 };
        int[] p = { 0, 32 };
        int[] S = { 0, 8 };
        int[] I = { 0, 1 };
        /* The input represents the following:
         * Start the job with t=10 and p=32 at time 8.
         * Thus the last job finishes at time 50.
         */
        assertEquals("Do job 1", 50, Scheduler.solve(n, t, p, S, I));
    }
}
