package MidtermPreparation.GreedyAlgorithms;

import main.MidtermPreparation.GreedyAlgorithms.HuffmanCoding.Huffman;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HuffmanTest {

    @Test
    public void oneElementTest() {
        String[] letters = {"a"};
        int[] frequencies = { 5 };
        String[] result = new String[] {"0"};

        assertArrayEquals(result, Huffman.huffmanCoding(letters, frequencies));
    }

    @Test
    public void huffmanTest() {
        String[] letters = {"a", "b", "c", "d", "e", "f"};
        int[] frequencies = { 5, 9, 12, 13, 16, 45 };
        String[] result = new String[]{"1100", "1101", "100", "101", "111", "0"};

        assertArrayEquals(result, Huffman.huffmanCoding(letters, frequencies));
    }

    @Test
    public void huffmanTest2() {
        String[] letters = {"a", "b", "c", "d", "e"};
        int[] frequencies = { 3, 5, 6, 4, 2 };
        String[] result = {"011", "10", "11", "00", "010"};

        assertArrayEquals(result, Huffman.huffmanCoding(letters, frequencies));
    }

    @Test
    public void huffmanTestTwoElements() {
        String[] letters = {"a", "b", };
        int[] frequencies = { 6, 5 };
        String[] result = { "1", "0" };

        assertArrayEquals(result, Huffman.huffmanCoding(letters, frequencies));
    }

    @Test
    public void huffmanTestMultiple() {
        String[] letters = { "a", "b", "c", "d", "e", "f", "g", "h",
                             "i", "j", "k", "l", "m", "n", "o", "p",
                             "q", "r", "s", "t"};
        int[] frequencies = { 4, 4, 17, 28, 38, 41, 48, 55, 56, 57, 66,
                              69, 71, 71, 72, 74, 75, 75, 77, 96};
        String[] result = { "0001000", "0001001", "000101", "00011", "11100",
                            "11101", "0000", "0010", "0011", "0100", "0101",
                            "0110", "0111", "1000", "1001", "1010", "1011", "1100",
                            "1101", "1111" };

        assertArrayEquals(result, Huffman.huffmanCoding(letters, frequencies));
    }
}
