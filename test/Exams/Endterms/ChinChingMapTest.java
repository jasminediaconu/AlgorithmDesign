package Exams.Endterms;

import main.Exams.Endterms.Resit2020.NegativeCycles.ChingChingMap;
import main.Exams.Endterms.Resit2020.NegativeCycles.Road;
import main.Exams.Endterms.Resit2020.NegativeCycles.Town;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChinChingMapTest {
    @Test(timeout = 100)
    public void example_one_road() {
        int n = 2;
        int m = 1;
        Town[] V = new Town[n + 1];
        for (int i = 1; i <= n; i++) {
            V[i] = new Town(i);
        }
        Set<Road> roads = new HashSet<>();
        roads.add(new Road(V[1], V[2], 10));
        /*
         * Only one road this cannot create a loop.
         */
        assertFalse(ChingChingMap.solve(n, m, V, roads));
    }

    @Test(timeout = 100)
    public void example_simple_positive_loop() {
        int n = 2;
        int m = 1;
        Town[] V = new Town[n + 1];
        for (int i = 1; i <= n; i++) {
            V[i] = new Town(i);
        }
        Set<Road> roads = new HashSet<>();
        roads.add(new Road(V[1], V[2], 10));
        roads.add(new Road(V[2], V[1], 1));
        /*
         * There is a loop, but it costs you money
         */
        assertFalse(ChingChingMap.solve(n, m, V, roads));
    }

    @Test(timeout = 100)
    public void example_simple_negative_loop() {
        int n = 2;
        int m = 1;
        Town[] V = new Town[n + 1];
        for (int i = 1; i <= n; i++) {
            V[i] = new Town(i);
        }
        Set<Road> roads = new HashSet<>();
        roads.add(new Road(V[1], V[2], -10));
        roads.add(new Road(V[2], V[1], 1));
        /*
         * There is a loop, which gets you 9 units of money per repetition
         */
        assertTrue(ChingChingMap.solve(n, m, V, roads));
    }

    @Test(timeout = 100)
    public void example_simple_negative_loop_three_nodes() {
        int n = 3;
        int m = 1;
        Town[] V = new Town[n + 1];
        for (int i = 1; i <= n; i++) {
            V[i] = new Town(i);
        }
        Set<Road> roads = new HashSet<>();
        roads.add(new Road(V[1], V[2], -3));
        roads.add(new Road(V[2], V[3], 1));
        roads.add(new Road(V[3], V[1], 1));
        /*
         * There is a loop, which gets you 1 unit of water per repetition
         */
        assertTrue(ChingChingMap.solve(n, m, V, roads));
    }
}
