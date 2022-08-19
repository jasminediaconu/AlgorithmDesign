package NetworkFlow;

import main.Exams.Endterms.Endterm2019.BuildNF.BuildNF;
import main.Exams.Endterms.Endterm2020.ResearchProject.ResearchProject;
import main.Exams.Endterms.Resit2020.TAs.TADistribution;
import main.EndtermPreparation.NetworkFlow.Baseball.Baseball;
import main.EndtermPreparation.NetworkFlow.FordFulkerson.FordFulkerson;
import main.EndtermPreparation.NetworkFlow.FordFulkerson.Graph;
import main.EndtermPreparation.NetworkFlow.FordFulkerson.Node;
import main.EndtermPreparation.NetworkFlow.ImageSegmentation.ImageSegmentation;
import main.EndtermPreparation.NetworkFlow.ProjectSelection.ProjectSelection;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class NetworkFlowTest {
    private ByteArrayInputStream getStream(String fileName) {
        return new ByteArrayInputStream(("src/assets/NetworkFlow/" + fileName).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Tests augmenting a valid path in a small network.
     */
//    @Test(timeout = 100)
//    public void augment1() throws FileNotFoundException {
//        Node node0 = new Node(0);
//        Node source = new Node(1);
//        Node sink = new Node(2);
//        Node node3 = new Node(3);
//        // Add edge between the source and node 3 with capacity 1 and no flow.
//        source.addEdge(node3, 1, 0, false);
//        node3.addEdge(source, 0, 0, true);
//        // Add edge between node 3 and node 0 with capacity 1 and no flow.
//        node3.addEdge(node0, 1, 0, false);
//        node0.addEdge(node3, 0, 0, true);
//        // Add edge between node 0 and the sink with capacity 1 and no flow.
//        node0.addEdge(sink, 1, 0, false);
//        sink.addEdge(node0, 0, 0, true);
//        List<Node> nodes = new ArrayList();
//        nodes.add(node0);
//        nodes.add(source);
//        nodes.add(sink);
//        nodes.add(node3);
//        Graph g = new Graph(nodes, source, sink);
//        ResidualGraph.augmentPath(g, Arrays.asList(1, 3, 0, 2));
//        assertEquals(ResidualGraph.parse(new FileInputStream("src/assets/NetworkFlow/augment1.out")), g);
//    }

    @Test
    public void baseballTest() throws FileNotFoundException {
        InputStream in = new FileInputStream("src/assets/NetworkFlow/baseball.txt");
        assertEquals(false, Baseball.solve(in));
    }

    @Test
    public void imageSegmentationTest() throws FileNotFoundException {
        InputStream in = new FileInputStream("src/assets/NetworkFlow/imageSegmentation.txt");
        assertEquals(3, ImageSegmentation.solve(in));
    }

    @Test
    public void projectSelectTest() throws FileNotFoundException {
        InputStream in = new FileInputStream("src/assets/NetworkFlow/projectSelection.txt");
        assertEquals(true, ProjectSelection.solve(in));
    }

    @Test(timeout = 100)
    public void exampleThreeStudentsPossible() {
        int n = 3;
        int m = 1;
        boolean[] student_mon = { false, true, true, true };
        boolean[] student_tues = { false, false, false, false };
        boolean[] supervisor_mon = { false, true };
        boolean[] supervisor_tues = { false, true };
        boolean[][] selected = { {}, { false, true }, { false, true }, { false, true } };
        /*
         * This test models the situation where:
         * Students 1,2,3 are available on Mondays
         * Supervisor 1 is available on both days.
         * Every student can be paired to supervisor 1.
         * This is doable as it matches all criteria.
         */
        assertTrue(ResearchProject.areThereGroups(n, m, student_mon, student_tues, supervisor_mon, supervisor_tues, selected));
    }

    @Test(timeout = 100)
    public void exampleSixStudentsPossible() {
        int n = 6;
        int m = 2;
        boolean[] student_mon = { false, true, true, true, false, false, false };
        boolean[] student_tues = { false, false, false, false, true, true, true };
        boolean[] supervisor_mon = { false, true, true };
        boolean[] supervisor_tues = { false, true, false };
        boolean[][] selected = { {}, { false, true, true },
                { false, true, true },
                { false, true, true },
                { false, true, true },
                { false, true, true },
                { false, true, true } };
        /*
         * This test models the situation where:
         * Students 1,2,3 are available on Mondays and Students 4,5,6 are available on Tuesday.
         * Supervisor 1 is available on both days and supervisor 2 only on Mondays.
         * Every student can be paired to supervisor 1 and supervisor 2.
         * This is doable as we can match students 1,2,3 to supervisor 2 and 4,5,6 to supervisor 1.
         */
        assertTrue(ResearchProject.areThereGroups(n, m, student_mon, student_tues, supervisor_mon, supervisor_tues, selected));
    }

    @Test
    public void exampleSixStudentsImpossible() {
        int n = 6;
        int m = 2;
        boolean[] student_mon = { false, true, true, true, true, false, false };
        boolean[] student_tues = { false, false, false, false, false, true, true };
        boolean[] supervisor_mon = { false, false, true };
        boolean[] supervisor_tues = { false, true, false };
        boolean[][] selected = { {}, { false, true, true }, { false, true, true }, { false, true, true }, { false, true, true }, { false, true, true }, { false, true, true } };
        /*
         * This test models the situation where:
         * Students 1,2,3,4 are available on Mondays and Students 5,6 are available on Tuesday.
         * Supervisor 1 is only available on Tuesdays and supervisor 2 only on Mondays.
         * Every student can be paired to supervisor 1 and supervisor 2.
         * This is impossible as we cannot give the minimum of 3 students to supervisor 2.
         */
        assertFalse(ResearchProject.areThereGroups(n, m, student_mon, student_tues, supervisor_mon, supervisor_tues, selected));
    }

    @Test(timeout = 100)
    public void exampleTwoOfEach() {
        int n = 2;
        int m = 2;
        int[] need = { 0, 100, 50 };
        int[] available = { 0, 75, 75 };
        boolean[][] match = new boolean[n + 1][m + 1];
        match[1][1] = true;
        match[1][2] = true;
        match[2][1] = true;
        match[2][2] = true;
        /*
         * This test models the situation where:
         * Course 1 requires 100 TA hours and course 2 requires 50
         * Both TAs have 75 hours available.
         * Both TAs can work both courses.
         * So we are not short any hours.
         */
        Assert.assertEquals(0, TADistribution.shortageOfTAs(n, need, m, available, match));
    }

    @Test(timeout = 100)
    public void exampleShortage() {
        int n = 2;
        int m = 2;
        int[] need = { 0, 100, 50 };
        int[] available = { 0, 250, 25 };
        boolean[][] match = new boolean[n + 1][m + 1];
        match[1][1] = true;
        match[1][2] = true;
        match[2][2] = true;
        /*
         * This test models the situation where:
         * Course 1 requires 100 TA hours and course 2 requires 50
         * TA 1 can work on course 1 for 250 hours.
         * TA 2 can work on both courses for 25 hours.
         * So we are short a total of 25 hours for course 2 by 25 hours when optimally assigning our current TAs.
         */
        Assert.assertEquals(25, TADistribution.shortageOfTAs(n, need, m, available, match));
    }

    @Test(timeout = 100)
    public void example() {
        int n = 2;
        int[] b = { 0, 8, 2 };
        int[] c = { 0, 2, 4 };
        int[][] dep = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        assertEquals(6, BuildNF.solve(n, b, c, dep));
    }

    @Test(timeout = 100)
    public void example02() {
        int n = 2;
        int[] b = { 0, 8, 2 };
        int[] c = { 0, 2, 4 };
        int[][] dep = { { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 } };
        assertEquals(4, BuildNF.solve(n, b, c, dep));
    }

    @Test(timeout = 100)
    public void example03() {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int x = 0; x < 4; x++) nodes.add(new Node(x));
        nodes.get(0).addEdge(nodes.get(1), 10);
        nodes.get(0).addEdge(nodes.get(2), 10);
        nodes.get(1).addEdge(nodes.get(3), 10);
        nodes.get(2).addEdge(nodes.get(3), 10);
        nodes.get(1).addEdge(nodes.get(2), 2);
        Graph g = new Graph(nodes, nodes.get(0), nodes.get(3));
        assertEquals(20, FordFulkerson.maxFlowScaled(g));
    }
}
