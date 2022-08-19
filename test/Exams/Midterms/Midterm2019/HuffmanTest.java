package Exams.Midterms.Midterm2019;

import main.Exams.Midterms.Midterm2019.Huffman;
import main.Exams.Midterms.Midterm2019.Node;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HuffmanTest {
    @Test
    public void exampleEncoding() {
        Node root = new Node((char) 0, 1);
        Node eNode = new Node('e', 0.6, root);
        Node xNode = new Node('x', 0.4, root);
        root.setLeftChild(eNode);
        root.setRightChild(xNode);
        assertEquals("0", Huffman.encode(eNode));
        assertEquals("1", Huffman.encode(xNode));
    }

    /**
     * Tree looks like this:
     *               root
     *     combined         combined
     *   e          x     f          g
     */
    @Test
    public void exampleEncoding2() {
        Node root = new Node((char) 0, 1);
        Node leftRoot = new Node((char) 0, 0.5, root);
        root.setLeftChild(leftRoot);
        Node eNode = new Node('e', 0.25, leftRoot);
        leftRoot.setLeftChild(eNode);
        Node xNode = new Node('x', 0.25, leftRoot);
        leftRoot.setRightChild(xNode);
        Node rightRoot = new Node((char) 0, 0.5, root);
        root.setRightChild(rightRoot);
        Node fNode = new Node('f', 0.25, rightRoot);
        rightRoot.setLeftChild(fNode);
        Node gNode = new Node('g', 0.25, rightRoot);
        rightRoot.setRightChild(gNode);
        assertEquals("00", Huffman.encode(eNode));
        assertEquals("01", Huffman.encode(xNode));
        assertEquals("10", Huffman.encode(fNode));
        assertEquals("11", Huffman.encode(gNode));
    }

    @Test
    public void exampleBuildTree() {
        int n = 4;
        char[] chars = { 0, 'c', 'd', 'e', 'f' };
        double[] freq = { 0,  0.25, 0.25, 0.25, 0.25 };
        Node tree = Huffman.buildHuffman(n, chars, freq);
        assertNotNull(tree);
        assertNotNull(tree.leftChild);
        assertNotNull(tree.rightChild);
        if (tree.leftChild.symbol != 'a') {
            assertEquals('a', tree.rightChild.symbol);
            assertEquals('b', tree.leftChild.symbol);
        } else {
            assertEquals('a', tree.leftChild.symbol);
            assertEquals('b', tree.rightChild.symbol);
        }
    }
}
