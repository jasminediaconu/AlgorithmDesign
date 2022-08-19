package Exams.Endterms;

import main.Exams.Endterms.Resit2020.Tournament;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TournamentTest {
    @Test
    public void example() {
        int n = 2;
        int[] a = { 0, 1, 3 };
        int[] b = { 0, 5, 2 };
        /*
         * Participant 1 then 2 gives a total time of: 6 + 2 = 8
         * Participant 2 then 1 gives a total time of: 5 + 5 = 10
         */
        assertEquals("Participant 1 first", 8, Tournament.boardgameTime(n, a, b));
}

    @Test
    public void example_one_job() {
        int n = 1;
        int[] a = { 0, 10 };
        int[] b = { 0, 32 };
        // This one person will be done at time 42
        assertEquals("Just the one", 42, Tournament.boardgameTime(n, a, b));
    }
}
