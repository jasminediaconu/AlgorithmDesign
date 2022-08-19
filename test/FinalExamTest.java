
import main.FinalExam.Question1.VacinationTime;
import main.FinalExam.Question2.RecoverYourItems;
import main.FinalExam.Question3.NoMoreExams;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FinalExamTest {

    @Test(timeout = 100)
    public void example_one_day() {
        int t = 1;
        int[] p = { 0, 100 };
        int c = 10;
        int f = 20;
        int s = 100;
        /**
         * Only one day so we just buy exactly what we need in one order for a cost of c = 10.
         */
        assertEquals(10, VacinationTime.minimiseCosts(t, p, c, f, s));
    }

    @Test(timeout = 100)
    public void example_two_days_buying() {
        int t = 2;
        int[] p = { 0, 100, 80 };
        int c = 10;
        int f = 1;
        int s = 200;
        /**
         * Only two days, here are our options:
         * - Buy 100 on day 1, and 80 on day 2, for a total of 2*c = 20.
         * - Buy 180 on day 1, and store 80 for day 2, for a total of c + 80*1 = 90.
         */
        assertEquals(20, VacinationTime.minimiseCosts(t, p, c, f, s));
    }

    @Test(timeout = 100)
    public void example_two_days_storing() {
        int t = 2;
        int[] p = { 0, 100, 80 };
        int c = 100;
        int f = 1;
        int s = 200;
        /**
         * Only two days, here are our options:
         * - Buy 100 on day 1, and 80 on day 2, for a total of 2*c = 200.
         * - Buy 180 on day 1, and store 80 for day 2, for a total of c + 80*1 = 180.
         */
        assertEquals(180, VacinationTime.minimiseCosts(t, p, c, f, s));
    }

    @Test(timeout = 100)
    public void example_two_days_can_store() {
        int t = 2;
        int[] p = { 0, 100, 80 };
        int c = 100;
        int f = 1;
        int s = 100;
        /**
         * Only two days, here are our options:
         * - Buy 100 on day 1, and 80 on day 2, for a total of 2*c = 200.
         * - Buy 180 on day 1, and store 80 for day 2, for a total of c + 80*1 = 180.
         *
         * We can store just fine still, because we use our 100 vaccinations on day 1 having 80 left which fits in our space of 100.
         */
        assertEquals(180, VacinationTime.minimiseCosts(t, p, c, f, s));
    }

    @Test(timeout = 100)
    public void example_two_days_cannot_store() {
        int t = 2;
        int[] p = { 0, 100, 80 };
        int c = 100;
        int f = 1;
        int s = 50;
        /**
         * Only two days, here are our options:
         * - Buy 100 on day 1, and 80 on day 2, for a total of 2*c = 200.
         * - Buy 180 on day 1, and store 80 for day 2, for a total of c + 80*1 = 180.
         *
         * Ideally then we would buty and store, but we cannot store sufficiently many, as we can only store 50! So alternative 2 becomes:
         *
         * - Buy 150 on day 1, store 50 for day 2, for a total of c + 50*1 = 150 and buy another 30 on day 2, so + c = 250. This is worse than just the two orders!
         */
        assertEquals(200, VacinationTime.minimiseCosts(t, p, c, f, s));
    }

    @Test(timeout = 100)
    public void example_one_item() {
        int n = 2;
        int m = 2;
        int[] v = { 0, 2, 1 };
        int[][] mem = { { 1, 1, 1 }, { 2, 1, 1 }, { 2, 1, 1 } };
        /*
         * Only item 1 should be included.
         */
        Set<Integer> ans = RecoverYourItems.solve(n, m, v, mem);
        assertEquals(1, ans.size());
        assertTrue(ans.contains(1));
    }

    @Test(timeout = 100)
    public void example_multiple_items() {
        int n = 5;
        int m = 10;
        // The best answer is to take 5, 3 and 2 to get 30, so indices 1, 2, and 5
        int[] v = { 0, 5, 3, 1, 8, 2 };
        int[][] mem = {
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 1 },
                { 15, 15, 15, 5, 5, 5, 3, 3, 1, 1, 1 },
                { 15, 15, 15, 5, 5, 5, 3, 3, 1, 1, 1 },
                { 15, 15, 15, 5, 5, 5, 3, 3, 1, 1, 1 },
                { 30, 15, 15, 10, 6, 6, 3, 3, 2, 1, 1 } };
        Set<Integer> ans = RecoverYourItems.solve(n, m, v, mem);
        assertEquals(3, ans.size());
        assertTrue(ans.contains(1));
        assertTrue(ans.contains(2));
        assertTrue(ans.contains(5));
    }

    @Test()
    public void exampleOneBrand() {
        int n = 2;
        int t = 3;
        int m = 1;
        int[] f = { 0, 1, 2 };
        Set<Integer>[] D = new Set[n + 1];
        Set<Integer>[] M = new Set[m + 1];
        for (int i = 1; i <= n; i++) {
            D[i] = new HashSet<>();
        }
        D[1].add(1);
        D[2].add(1);
        for (int i = 1; i <= m; i++) {
            M[i] = new HashSet<>();
        }
        M[1].add(1);
        M[1].add(2);
        M[1].add(3);
        /**
         * This test models the situation where:
         * Lecturer 1 can work on brand 1
         * Lecturer 2 can work on brand 1
         * The only available brand is okay to use on each day.
         *
         * This is doable, for example by having lecturer 1 do day 1 and lecturer 2 do day 2 and 3.
         */
        Assert.assertTrue(NoMoreExams.servingFoodAndChangingLives(n, t, m, f, D, M));
    }

    @Test()
    public void exampleOneBrandLimit() {
        int n = 2;
        int t = 9;
        int m = 1;
        int[] f = { 0, 9, 9 };
        Set<Integer>[] D = new Set[n + 1];
        Set<Integer>[] M = new Set[m + 1];
        for (int i = 1; i <= n; i++) {
            D[i] = new HashSet<>();
        }
        D[1].add(1);
        D[2].add(1);
        for (int i = 1; i <= m; i++) {
            M[i] = new HashSet<>();
            for (int j = 1; j <= t; j++) {
                M[i].add(j);
            }
        }
        /**
         * This test models the situation where:
         * Lecturer 1 can work on brand 1
         * Lecturer 2 can work on brand 1
         * The only available brand is okay to use on each day.
         *
         * This is _not_ doable, as each lecturer can do at most _4_ days of one brand.
         */
        Assert.assertFalse(NoMoreExams.servingFoodAndChangingLives(n, t, m, f, D, M));
    }

    @Test()
    public void exampleTwoBrands() {
        int n = 2;
        int t = 5;
        int m = 2;
        int[] f = { 0, 5, 5 };
        Set<Integer>[] D = new Set[n + 1];
        Set<Integer>[] M = new Set[m + 1];
        for (int i = 1; i <= n; i++) {
            D[i] = new HashSet<>();
            for (int j = 1; j <= m; j++) {
                D[i].add(j);
            }
        }
        for (int i = 1; i <= m; i++) {
            M[i] = new HashSet<>();
        }
        M[1].add(1);
        M[1].add(2);
        M[1].add(3);
        M[1].add(4);
        M[2].add(4);
        M[2].add(5);
        /**
         * This test models the situation where:
         * Lecturer 1 can do all brands, and can work 5 days.
         * Lecturer 2 can do all brands, and can work 5 days.
         * The first brand can only be done on days 1 through 4, the second brand on day 3 and later.
         *
         * This is doable, in a variety of ways.
         */
        Assert.assertTrue(NoMoreExams.servingFoodAndChangingLives(n, t, m, f, D, M));
    }

    @Test()
    public void exampleTwoBrandsImpossible() {
        int n = 2;
        int t = 9;
        int m = 2;
        int[] f = { 0, 5, 4 };
        Set<Integer>[] D = new Set[n + 1];
        Set<Integer>[] M = new Set[m + 1];
        for (int i = 1; i <= n; i++) {
            D[i] = new HashSet<>();
        }
        D[1].add(1);
        D[2].add(1);
        D[2].add(2);
        for (int i = 1; i <= m; i++) {
            M[i] = new HashSet<>();
        }
        M[1].add(1);
        M[1].add(2);
        M[1].add(3);
        M[1].add(4);
        M[1].add(5);
        M[2].add(5);
        M[2].add(6);
        M[2].add(7);
        M[2].add(8);
        M[2].add(9);
        /**
         * This test models the situation where:
         * Lecturer 1 can only work brand 1, and at most 5 days.
         * Lecturer 2 can work both brands, but only 4 days.
         * Brand 1 is available for the first 5 days, and brand 2 on the last 5.
         *
         * This is _not_ doable, as lecturer 1 cannot do all of their days as they can only do brand 1 so at most 4 days. Which means lecturer 2 needs to do 5 days, which is too many.
         */
        Assert.assertFalse(NoMoreExams.servingFoodAndChangingLives(n, t, m, f, D, M));
    }
}
