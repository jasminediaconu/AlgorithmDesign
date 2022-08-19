package NetworkFlow;

import main.EndtermPreparation.NetworkFlow.MaxEdgeDisjointPaths.MaxEdgeDisjointPaths;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxEdgeDisjointPathsTest {

    @Test(timeout = 100)
    public void test1() {
        assertEquals(2, MaxEdgeDisjointPaths.maximizeFlow(MaxEdgeDisjointPaths.buildGraph(3, 3)));
    }
}
