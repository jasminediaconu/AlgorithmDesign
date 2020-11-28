package main.GreedyAlgorithms.HuffmanCoding;

import java.util.*;

public class Huffman {
    public static String[] huffmanCoding(String[] letters, int[] frequencies) {
        int size = letters.length;

        if (size == 1) return new String[] {"0"};

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        String[] result = new String[size];

        for(int i = 0; i < size; i++) {
            pq.add(new Pair(letters[i], frequencies[i], null, null, ""));
        }

        while(pq.size() > 1) {
            Pair first = pq.remove();
            Pair second = pq.remove();

            Pair pair = new Pair(first.letter + " " + second.letter,
                    first.frequency + second.frequency, first, second, "");

            pq.add(pair);
        }

        Pair tree = pq.remove();

        ArrayList<Pair> encodings = preOrder(tree, "", new ArrayList<Pair>());
        encodings.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.letter.compareTo(o2.letter);
            }
        });

        for(int i = 0; i < size; i++) result[i] = encodings.get(i).encoding;

        return result;
    }

    public static ArrayList<Pair> preOrder(Pair tree, String s, ArrayList<Pair> result) {
        if(tree == null) return result;

        if(tree.leftNode == null && tree.rightNode == null) {
            tree.encoding = s;
            result.add(tree);
            return result;
        }

        preOrder(tree.leftNode, s + "0", result);
        preOrder(tree.rightNode, s + "1", result);
        return result;
    }

}

class Pair implements Comparable<Pair> {

    String letter;
    int frequency;

    Pair leftNode;
    Pair rightNode;

    String encoding;

    public Pair(String letter, int frequency, Pair leftNode, Pair rightNode, String encoding) {
        this.letter = letter;
        this.frequency = frequency;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.encoding = encoding;
    }

    @Override
    public int compareTo(Pair o) {
        return this.frequency - o.frequency;
    }
}