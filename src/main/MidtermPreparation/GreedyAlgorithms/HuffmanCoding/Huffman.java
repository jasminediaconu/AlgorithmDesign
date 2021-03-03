package main.MidtermPreparation.GreedyAlgorithms.HuffmanCoding;

import java.util.*;

public class Huffman {
    public static String[] huffmanCoding(String[] letters, int[] frequencies) {
        int size = letters.length;

        if (size == 1) return new String[] {"0"};

        PriorityQueue<Tree> pq = new PriorityQueue<>();
        String[] result = new String[size];

        for(int i = 0; i < size; i++) {
            pq.add(new Tree(letters[i], frequencies[i], null, null, ""));
        }

        while(pq.size() > 1) {
            Tree first = pq.remove();
            Tree second = pq.remove();

            Tree pair = new Tree(first.letter + " " + second.letter,
                    first.frequency + second.frequency, first, second, "");

            pq.add(pair);
        }

        Tree tree = pq.remove();

        ArrayList<Tree> encodings = preOrder(tree, "", new ArrayList<Tree>());
        encodings.sort(new Comparator<Tree>() {
            @Override
            public int compare(Tree o1, Tree o2) {
                return o1.letter.compareTo(o2.letter);
            }
        });

        for(int i = 0; i < size; i++) result[i] = encodings.get(i).encoding;

        return result;
    }

    public static ArrayList<Tree> preOrder(Tree tree, String s, ArrayList<Tree> result) {
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

class Tree implements Comparable<Tree> {

    String letter;
    int frequency;

    Tree leftNode;
    Tree rightNode;

    String encoding;

    public Tree(String letter, int frequency, Tree leftNode, Tree rightNode, String encoding) {
        this.letter = letter;
        this.frequency = frequency;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.encoding = encoding;
    }

    @Override
    public int compareTo(Tree o) {
        return this.frequency - o.frequency;
    }
}