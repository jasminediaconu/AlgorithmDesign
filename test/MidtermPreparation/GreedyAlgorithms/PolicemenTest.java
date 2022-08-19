package MidtermPreparation.GreedyAlgorithms;

import main.MidtermPreparation.GreedyAlgorithms.Policemen.Policemen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolicemenTest {

    @Test
    public void twoElements() {
        char[] arr = { 'P', 'T'};

        assertEquals(1, Policemen.findThieves(arr, 2, 1));
    }

    @Test
    public void moreElements() {
        char[] arr = { 'P', 'T', 'T', 'P', 'T'};

        assertEquals(2, Policemen.findThieves(arr, 5, 1));
    }

    @Test
    public void moreElements2() {
        char[] arr = { 'T', 'T', 'P', 'P', 'T', 'P'};
        char[] arr2 = {'P', 'T', 'P', 'T', 'T', 'P'};
        assertEquals(3, Policemen.findThieves(arr, 6, 2));
        assertEquals(3, Policemen.findThieves(arr, 6, 3));
    }
}
