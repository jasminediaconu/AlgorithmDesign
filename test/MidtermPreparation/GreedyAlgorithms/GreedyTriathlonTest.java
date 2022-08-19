package MidtermPreparation.GreedyAlgorithms;

import main.MidtermPreparation.GreedyAlgorithms.GreedyTriathlon.GreedyTriathlon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreedyTriathlonTest {
    @Test
    public void greedyTriathlon() {
        int[] swimming = { 6, 9 };
        int[] biking = { 9, 4 };
        int[] running = { 6, 7 };

        int result = 26;

        assertEquals(result,
                GreedyTriathlon.greedyTriathlon(swimming, biking, running));
    }

    @Test
    public void greedyTriathlon2() {
        int[] swimming = { 5, 2 };
        int[] biking = { 1, 2 };
        int[] running = { 4, 1 };

        int result = 10;

        assertEquals(result,
                GreedyTriathlon.greedyTriathlon(swimming, biking, running));
    }

    @Test
    public void greedyTriathlon3() {
        int[] swimming = { 10, 5 };
        int[] biking = { 4, 4 };
        int[] running = { 1, 5 };

        int result = 20;

        assertEquals(result,
                GreedyTriathlon.greedyTriathlon(swimming, biking, running));
    }

    @Test
    public void greedyTriathlon4() {
        int[] swimming = { 5, 2, 10, 5 };
        int[] biking = { 1, 2, 4, 4 };
        int[] running = { 4, 1, 1, 5 };

        int result = 25;

        assertEquals(result,
                GreedyTriathlon.greedyTriathlon(swimming, biking, running));
    }

}
