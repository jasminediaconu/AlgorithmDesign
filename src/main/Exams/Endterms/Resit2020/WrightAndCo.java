package main.Exams.Endterms.Resit2020;

import java.util.*;

public class WrightAndCo {

    /**
     * You should implement this method.
     * @param n the number of documents to sort.
     * @param k the number of groups that they need to be sorted in.
     * @param d 2D-array with all the dissimilarity scores, d[i][j] represents the dissimilarity score of document \\(1 \leq i \leq n\\) and document \\(1 \leq j \leq n\\). You should ignore d[0][j] and d[i][0].
     * @param e 2D-array indicating which documents Phoenix has judged similar, ei][j] is true iff Phoenix has determined that a document \\(1 \leq i \leq n\\) and document \\(1 \leq j \leq n\\) must be in the same group. You should ignore e[0][j] and e[i][0].
     * @return the smallest dissimilarity score between different groups in the optimal grouping.
     */
    public static int legalisingDocuments(int n, int k, int[][] d, boolean[][] e) {
        // Storing all documents
        ArrayList<Integer> documents = new ArrayList<>();
        for(int i = 0; i < n; i++) documents.add(i);

        // Create a cluster for each document
        UnionFind2 UnionFind2 = new UnionFind2(documents);

        // Max number of similarities to check
        int size = n * (n - 1) / 2;

        int alreadyPaired = 0;

        ArrayList<Similarity> similarities = new ArrayList<>(size);
        for(int i = 1; i <= n; i++) {
            for(int j = i + 1; j <= n; j++) {
                int firstDocument = documents.get(i-1);
                int secondDocument = documents.get(j-1);

                if(e[i][j]) {
                    UnionFind2.join(firstDocument, secondDocument);
                    alreadyPaired++;
                } else {
                    similarities.add(new Similarity(firstDocument, secondDocument, d[i][j]));
                }
            }
        }

        // Sort by decreasing similarities (increasing number)
        Collections.sort(similarities);

        int initialCount = 0;
        int finalCount = n - k - alreadyPaired;

        while(initialCount != finalCount && !similarities.isEmpty()) {
            Similarity first = similarities.remove(0);

            // If two documents are already together, don't add them again
            if(!hasCycle(first.a, first.b, UnionFind2)) initialCount++;
        }

        while(!similarities.isEmpty()) {
            Similarity similarity = similarities.remove(0);
            if(!hasCycle(similarity.a, similarity.b, UnionFind2)) {
                return similarity.difference;

            }
        }
        return 0;
    }

    public static boolean hasCycle(int source, int destination, UnionFind2 UnionFind2) {
        int root = UnionFind2.find(source);
        int root2 = UnionFind2.find(destination);

        if (root == root2) return true;

        else UnionFind2.join(source, destination);

        return false;
    }
}

class Similarity implements Comparable<Similarity>{

    int a, b;

    int difference;

    public Similarity(int a, int b, int difference) {
        this.a = a;
        this.b = b;
        this.difference = difference;
    }

    @Override
    public int compareTo(Similarity o) {
        return this.difference - o.difference;
    }
}

class UnionFind2 {

    private List<Integer> documents;

    private int[] parent;

    private int[] rank;

    UnionFind2(List<Integer> documents) {
        this.documents = documents;
        int n = documents.size();
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    /**
     * Joins two disjoint sets together, if they are not already joined.
     * @return false if x and y are in same set, true if the sets of x and y are now joined.
     */
    boolean join(int x, int y) {
        int xrt = find(x);
        int yrt = find(y);
        if (rank[xrt] > rank[yrt])
            parent[yrt] = xrt;
        else if (rank[xrt] < rank[yrt])
            parent[xrt] = yrt;
        else if (xrt != yrt)
            rank[parent[yrt] = xrt]++;
        return xrt != yrt;
    }

    int find(int p) {
        return documents.get(findElement(p));
    }

    private int findElement(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }
}