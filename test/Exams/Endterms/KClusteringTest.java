package Exams.Endterms;

import main.Exams.Endterms.Resit2020.KClustering;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KClusteringTest {

    @Test
    public void example() {
        int n = 1;
        int k = 4;
        int[][] c = {
                    { 0, 0,   0,   0,   0 },
                    { 0, 0,   1,   100, 100 },
                    { 0, 1,   0,   1,   100 },
                    { 0, 100, 1,   0,   1 },
                    { 0, 100, 100, 1,   0 } };
        // All false, nobody is connnected yet.
        boolean[][] e = new boolean[k + 1][k + 1];
        // Best solution is to connect 1 to 2, 2 to 3, and 3 to 4, this means three connections of cost 1.
        assertEquals(3, KClustering.lanParty(n, k, c, e));
    }

    @Test
    public void keepGivenConnections() {
        int n = 1;
        int k = 4;
        int[][] c = { { 0, 0, 0, 0, 0 },
                      { 0, 0, 1, 100, 100 }, // 1   1 -> 2
                      { 0, 1, 0, 1, 100 },   // 2   2 & 4
                      { 0, 100, 1, 0, 1 },   // 3   3 -> 4
                      { 0, 100, 100, 1, 0 }  // 4   2 & 4
        };
        // All false, nobody is connnected yet.
        boolean[][] e = new boolean[k + 1][k + 1];
        // But now we connect 2 and 4
        e[2][4] = true;
        // Best solution is to connect 1 to 2, and 3 to 4, this means two connections of cost 1 (since 2 and 4 are already connected).
        assertEquals(2, KClustering.lanParty(n, k, c, e));
    }
}
