package main.MidtermPreparation.GreedyAlgorithms.UnionFind;

/**
 * The Union-Find data structure allows us to maintain disjoint sets.
 * One way to implement the Union-Find data structure is to use Union-by-Rank.
 * This data structure can be implemented by using two arrays (root and rank).
 * The data structure for this implementation uses pointers.
 * Each node has an associated pointer to the name of the set that contains this node.
 * The Union-by-Rank data structure has been partly implemented.
 * Implement the missing find and union methods.
 */
public class UnionFind {
    private int[] parent;

    private int[] rank;

    // Union Find structure implemented with two arrays for Union by Rank
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) parent[i] = i;
    }

    /**
     * Merge two subtrees if they have a different parent, input is array indices
     * @param i a node in the first subtree
     * @param j a node in the second subtree
     * @return true iff i and j had different parents.
     */
    public boolean union(int i, int j) {
        int iSet = find(i);
        int jSet = find(j);

        if (iSet == jSet) return false;
        else if (rank[iSet] < rank[jSet]) parent[iSet] = jSet;
        else {
            parent[jSet] = iSet;
            if (rank[iSet] == rank[jSet]) rank[iSet]++;
            }

        return true;
    }

    /**
     * NB: this function should also do path compression
     * @param i index of a node
     * @return the root of the subtree containg i.
     */
    public int find(int i) {
        if(parent[i] != i) parent[i] = find(parent[i]);
        return parent[i];
    }

    // Return the rank of the trees
    public int[] getRank() {
        return rank;
    }

    // Return the parent of the trees
    public int[] getParent() {
        return parent;
    }
}


