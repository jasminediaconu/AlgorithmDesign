package MidtermPreparation.GreedyAlgorithms;

import main.MidtermPreparation.GreedyAlgorithms.JobSequencing.JobSequencing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobSequencingTest {

    @Test
    public void jobSequencing() {
        int[] deadlines = { 4, 1, 1, 1 };
        int[] profits = { 20, 10, 40, 30 };

        int result = 60;

        assertEquals(result, JobSequencing.jobSequencing(deadlines, profits));
    }

    @Test
    public void jobSequencing2() {
        int[] deadlines = { 2, 1, 2, 1, 3 };
        int[] profits = { 100, 19, 27, 25, 15 };

        int result = 142;

        assertEquals(result, JobSequencing.jobSequencing(deadlines, profits));
    }
}
