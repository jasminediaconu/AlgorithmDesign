package Exams.Endterms;

import main.Exams.Endterms.Resit2020.WrightAndCo;
import org.junit.Assert;
import org.junit.Test;

public class WrightAndCoTest {
    @Test
    public void example() {
        int n = 4;
        int k = 2;
        int[][] c = { { 0, 0, 0, 0, 0 },
                      { 0, 0, 1, 100, 100 },
                      { 0, 1, 0, 1, 100 },
                      { 0, 100, 1, 0, 1 },
                      { 0, 100, 100, 1, 0 }
                    };
        // All false, Phoenix had no time to do this yet.
        boolean[][] e = new boolean[n + 1][n + 1];
        // Whatever grouping of two groups we make, the smallest score between two groups is always 1
        Assert.assertEquals(1, WrightAndCo.legalisingDocuments(n, k, c, e));
    }

    @Test
    public void keepGivenGroupsFromPhoenix() {
        int n = 4;
        int k = 2;
        int[][] c = { { 0, 0, 0, 0, 0 },
                      { 0, 0, 2, 3, 4 },
                      { 0, 2, 0, 1, 100 }, // 2&4
                      { 0, 3, 1, 0, 1 },
                      { 0, 4, 100, 1, 0 } }; // 2&4
        boolean[][] e = new boolean[n + 1][n + 1];
        e[2][4] = e[4][2] = true;
        // Group A: 2,3,4; Group B: 1; Smallest similarity between groups: 1 & 2 with 2 which has maximised the smallest score betweeen groups (so minimised the similarity score inside groups).
        Assert.assertEquals(2, WrightAndCo.legalisingDocuments(n, k, c, e));
    }
}
