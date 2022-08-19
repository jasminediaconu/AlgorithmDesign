package main.Exams.Midterms.Midterm2019;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Huffman {

    /**
     *  You should implement this method.
     *  @param node A Node in the Huffman encoding tree
     *  @return the encoded string representing the character in this node.
     */
    public static String encode(Node node) {
        // Implement this method for question 2a
        return encodeChar(node, "");
    }

    public static String encodeChar(Node node, String s) {
        while(node.parent != null) {
            if(node == node.parent.leftChild ) s = "0" + s;
            else if(node == node.parent.rightChild) s = "1" + s;

            node = node.parent;
        }

        return s;
    }

    /**
     *  You should implement this method.
     *  @param n the number of characters that need to be encoded.
     *  @param characters The characters c_1 through c_n. Note you should use only characters[1] up to and including characters[n]!
     *  @param frequencies The frequencies f_1 through f_n. Note you should use only frequencies[1] up to and including frequencies[n]!
     *  @return The rootnode of an optimal Huffman tree that represents the encoding of the characters given.
     */
    public static Node buildHuffman(int n, char[] characters, double[] frequencies) {
        // Implement this method for question 2b
        Comparator<Node> nodeComparator = new NodeComparator();
        PriorityQueue<Node> pq = new PriorityQueue<>(n, nodeComparator);

        for(int i = 1; i <= n; i++) pq.add(new Node(characters[i], frequencies[i]));

        while(pq.size() > 1) {
            Node first = pq.remove();
            Node second = pq.remove();

            Node pair = new Node('-', first.frequency + second.frequency, first, second);

            first.setParent(pair);
            second.setParent(pair);

            pq.add(pair);
        }

        return pq.poll();
    }
}

class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node x, Node y) {
        return Double.compare(x.frequency, y.frequency);
    }
}