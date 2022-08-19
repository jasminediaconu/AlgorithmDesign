package Exams.Midterms.Resit2018;

import main.Exams.Midterms.Resit2018.Mason;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MasonTest {
    @Test(timeout = 100)
    public void example() {
        int n = 2;
        int[] t = { 0, 1, 3 };
        int[] p = { 0, 5, 2 };
        assertEquals("Do job 1 first, then job 2", 1, Mason.solve(n, t, p));
    }

    @Test(timeout = 100)
    public void example_one_job() {
        int n = 1;
        int[] t = { 0, 10 };
        int[] p = { 0, 32 };
        assertEquals("Do job 1", 0, Mason.solve(n, t, p));
    }
}
