package main.DivideAndConquer.ExploringAMaze;

public class ExploringAMaze {
    /**
     * Recursively searches for the element.
     * Returns true if element can be found, else false.
     *
     * @param tree
     *     - tree that you need to look in.
     * @param element
     *     - the element that you are looking for.
     * @return true if found, else false.
     */
    public boolean search(BinaryTree tree, int element) {
        if (tree == null) return false;

        if(tree.getKey() == element) return true;

        return search(tree.getLeft(), element) || search(tree.getRight(), element);
    }

}
