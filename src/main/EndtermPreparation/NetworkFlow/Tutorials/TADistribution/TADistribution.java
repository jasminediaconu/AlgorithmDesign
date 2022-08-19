package main.EndtermPreparation.NetworkFlow.Tutorials.TADistribution;

import java.util.ArrayList;

/**
 * In our adventure in changing courses to be ready for online education next year,
 * we are also reconsidering the TA distribution.
 * Students have already indicated what courses they can TA and assist in,
 * we are now trying to figure out whether we have sufficient TAs.
 *
 * To that end you have been provided with the following information:
 * For n TAs you get the number of hours that TA is available a_i for each TA 1≤i≤n.
 * For m courses you get the the number of TA hours b_j that the course 1≤j≤m requires.
 * Further more you are told which TAs are available for which courses through
 * a large matrix c_(i,j) where for 1≤i≤n, 1≤j≤m the value c_(i,j) is true iff TA i can assist course j.
 *
 * The method you should implement is shortageOfTAs() which answers the question:
 * When optimally using the TAs available, how many TA-hours are we still short for all courses combined?
 *
 * For this assignment you are advised to use the code made available on WebLab for this assignment specifically.
 */
public class TADistribution {

    /**
     * You should implement this method
     *
     * @param n the number of TAs
     * @param a the number of hours a TA is available from index _1_ to _n_. You should ignore a[0].
     * @param m the number of available TAs
     * @param b the number of TA hours a course requires from index _1_ to _m_. You should ignore b[0].
     * @param c a matrix indicating whether a TA is available to assist in a course. The value c[i][j] is true iff TA i can assist course j. You should ignore c[0][j] and c[i][0].
     * @return the number of hours we are short when optimally using the available TAs.
     */
    public static int shortageOfTAs(int n, int[] a, int m, int[] b, boolean[][] c) {
        int demand = 0;

        for(int i = 1; i <= m; i++) demand += b[i];

        // Create source and sink nodes with demand/supply equal to the amount of hours needed
        Node source = new Node(0, 0);
        Node sink = new Node(-1, 0);

        ArrayList<Node> nodes = new ArrayList<>();

        nodes.add(source);

        Node[] TAs = new Node[n+1];
        for(int i = 1; i <= n; i++) {
            TAs[i] = new Node(i, 0);
            nodes.add(TAs[i]);
            source.addEdge(TAs[i], 0, a[i]);
        }

        Node[] courses = new Node[m+1];
        for(int i = 1; i <= m; i++) {
            courses[i] = new Node(i+n, 0);
            nodes.add(courses[i]);
            courses[i].addEdge(sink, 0, b[i]);
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                Node TA = TAs[j];
                Node course = courses[i];

                if(c[j][i]) TA.addEdge(course, 0, Integer.MAX_VALUE/2);
            }
        }

        nodes.add(sink);

        Graph g = new Graph(nodes, source, sink);

        int maxFlow = MaxFlow.maximizeFlow(g);

        return demand - maxFlow;
    }

}
