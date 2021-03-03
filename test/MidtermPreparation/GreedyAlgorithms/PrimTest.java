package MidtermPreparation.GreedyAlgorithms;

import main.MidtermPreparation.GreedyAlgorithms.PrimAlgorithm.Graph;
import main.MidtermPreparation.GreedyAlgorithms.PrimAlgorithm.Prim;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimTest {
    @Test
    public void testDisjoint() {
        Graph g = new Graph(2);

        g.addEdge(0, 0, 1);
        assertEquals(0, Prim.shortestPath(g, g.getVertex(0)));
    }

    /**
     * Tests the following graph with all edges having weight 1
     * 0 - 1 - 3
     * |   |
     * 2 - 4
     */
    @Test
    public void testUnweighted() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 1);
        g.addEdge(2, 4, 1);
        g.addEdge(1, 3, 1);
        g.addEdge(1, 4, 1);
        assertEquals(4, Prim.shortestPath(g, g.getVertex(0)));
    }

    /**
     * Tests the following graph:
     * 0 - 1 - 3
     * |    \  |
     * |     \ |
     * 2  ---  4
     * <p>
     * The weights are as follows:
     * 0 - 1: 1
     * 1 - 3: 6
     * 3 - 4: 4
     * 1 - 4: 1
     * 0 - 2: 7
     * 2 - 4: 4
     */
    @Test
    public void testWeighted() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 3, 6);
        g.addEdge(3, 4, 4);
        g.addEdge(1, 4, 1);
        g.addEdge(0, 2, 7);
        g.addEdge(2, 4, 4);
        // Path is 0-1-4
        assertEquals(10, Prim.shortestPath(g, g.getVertex(0)));
    }

    /**
     * Tests the following graph:
     * B - C - D
     * / |    |    \
     * /  |   I      E
     * A  | / |     /
     * \ H - G - F
     *
     * <p>
     * The weights are as follows:
     * H - G: 1
     * C - I: 2
     * G - F: 2
     * C - F: 4
     * A - B: 4
     * I - G: 6
     * H - I: 7
     * C - D: 7
     * B - C: 8
     * A - H: 8
     * D - E: 9
     * E - F: 10
     * B - H: 11
     * D - F: 14
     */
    @Test
    public void testWeightedFinal() {
        Graph g = new Graph(9);
        g.addEdge(0, 1, 4);
        g.addEdge(1, 7, 11);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(7, 8, 7);
        g.addEdge(7, 6, 1);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 5, 4);
        g.addEdge(2, 8, 2);
        g.addEdge(8, 6, 6);
        g.addEdge(6, 5, 2);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(5, 4, 10);

        assertEquals(37, Prim.shortestPath(g, g.getVertex(0)));
    }

    @Test
    public void testFinal() {
        Graph g = new Graph(9);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 3, 5);
        g.addEdge(2, 3, 4);
        g.addEdge(3, 1, 15);

        assertEquals(19, Prim.shortestPath(g, g.getVertex(0)));
    }
}
