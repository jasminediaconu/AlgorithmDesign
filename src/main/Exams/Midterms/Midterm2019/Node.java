package main.Exams.Midterms.Midterm2019;

/**
 * NOTE: You should ensure that if a Node is a part of a tree, then all nodes in the tree have their `parent`, `leftChild`, and `rightChild` set appropriately!
 * You may add methods to this class, provided you do not change the names or types of existing methods or fields!
 */
public class Node {

    public char symbol;

    double frequency;

    Node parent;

    public Node leftChild;

    public Node rightChild;

    public Node(char symbol, double frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
    }

    public Node(char symbol, double frequency, Node parent) {
        this(symbol, frequency);
        this.parent = parent;
    }

    public Node(char symbol, double frequency, Node leftChild, Node rightChild) {
        this(symbol, frequency);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public char getSymbol() {
        return symbol;
    }

    public double getFrequency() {
        return frequency;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}
