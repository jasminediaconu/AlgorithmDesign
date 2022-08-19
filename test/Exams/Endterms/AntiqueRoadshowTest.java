package Exams.Endterms;

import main.Exams.Endterms.Resit2019.DynamicProgramming.AntiqueRoadshow;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AntiqueRoadshowTest {
    @Test(timeout = 100)
    public void example_atmost_one() {
        int n = 4;
        int[] v = { 0, 2, 4, 8, 5 };
        int[] skip = { 0, 0, 1, 2, 3 };
        /* We can not combine any (due to the skip values), so taking only 8 is optimal
         */
        assertEquals(8, AntiqueRoadshow.solve(n, v, skip));
    }

    @Test(timeout = 100)
    public void example_combining() {
        int n = 4;
        int[] v = { 0, 2, 4, 8, 5 };
        int[] skip = { 0, 0, 1, 2, 1 };
        /* We cannot combine 2 with 4 nor 8 with any other.
         * Best option is therefore: 5 + 4 = 9.
         */
        assertEquals(9, AntiqueRoadshow.solve(n, v, skip));
    }
}
