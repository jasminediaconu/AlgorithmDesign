package main.Exams.Endterms.Resit2020.TAs;


import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * In our adventure in changing courses to be ready for online education next year,
 * we are also reconsidering the TA distribution.
 * Students have already indicated what courses they can TA and assist in, we are now trying to figure out
 * whether we have sufficient TAs.
 *
 * To that end you have been provided with the following information:
 * For n courses you get the the number of TA hours ai that the course 1≤i≤n requires.
 * For m TAs you get the number of hours that TA is available bj for each TA 1≤j≤m.
 * Further more you are told which TAs are available for which courses through a large matrix ci,j where for 1≤i≤n,1≤j≤m
 * the value ci,j is true iff course i can be assisted by TA j.
 *
 * The method you should implement is shortageOfTAs() which answers the question:
 * When optimally using the TAs available, how many TA-hours are we still short for all courses combined?
 */
public class TADistribution {
    /**
     * You should implement this method
     *
     * @param n the number of courses
     * @param a the number of TA hours a course requires from index _1_ to _n_. You should ignore a[0].
     * @param m the number of available TAs
     * @param b the number of hours a TA is available from index _1_ to _m_. You should ignore b[0].
     * @param c a matrix indicating whether a TA is available to assist in a course. The value c[i][j] is true iff course i can be assisted by TA j. You should ignore c[0][j] and c[i][0].
     * @return the number of hours we are short when optimally using the available TAs.
     */
    public static int shortageOfTAs(int n, int[] a, int m, int[] b, boolean[][] c) {

        int totalHours = 0;

        // Find the total sum of hours that the courses need
        for(int i = 1; i <= n; i++) totalHours += a[i];

        // Create nodes
        Node source = new Node(0, -totalHours);
        Node sink = new Node(n+m+1, totalHours);

        ArrayList<Node> nodes = new ArrayList<>();

        nodes.add(source);

        // Connect source with TAs with max bound the max hours they can work
        for(int i = 1; i <= m; i++) {
            Node TA = new Node(i, 0);
            nodes.add(TA);
            source.addEdge(TA, 0, b[i]);
        }

        // Connect sink with courses, courses have a min hours demand
        for(int i = 1; i <= n; i++) {
            Node course = new Node(i + m, a[i]);
            nodes.add(course);
            course.addEdge(sink, 0, a[i]);
        }

        // Connect TAs with courses based on the availability
        for(int i = 1; i <= n; i++) {
            int k = i + m;
            for(int j = 1; j <= m; j++) {
                Node course = nodes.get(k);
                Node TA = nodes.get(j);

                if(c[i][j]) TA.addEdge(course, 0, b[j]);
            }
        }

        nodes.add(sink);

        Graph g = new Graph(nodes, source, sink);

        MaxFlow.maximizeFlow(g);

        int maxHours = 0;

        for(Edge e : source.getEdges()) {
            maxHours += e.getFlow();
        }

        return totalHours - maxHours;
    }
}















// TODO: WHAT THE FUCK IS D
//
//        int totalDemand = 0;
//
//        for (int i = 1; i <= n; i++) totalDemand += a[i];
//
//        Node source = new Node(0, -totalDemand);
//
//        Node sink = new Node(n+m+1, n);
//
//        ArrayList<Node> nodes = new ArrayList<>();
//
//        nodes.add(source);
//
//        // Add the courses
//        for(int i = 1; i <= n; i++) {
//            Node course = new Node(i, a[i]);
//            course.addEdge(sink, 0, a[i]);
//            nodes.add(course);
//        }
//
//        // Add the TAs
//        for(int i = m+1; i <= m+n; i++) {
//            Node ta = new Node(i, b[i-m]);
//            source.addEdge(ta, 0, b[i-m]);
//            nodes.add(ta);
//        }
//
//        // Add relations
//        for(int i = 1; i <= n; i++) {
//            Node course = nodes.get(i);
//            for(int j = 1; j <= m; j++) {
//                Node ta = nodes.get(n + j);
//                if(c[i][j]) ta.addEdge(course, 0, b[j]);
//            }
//        }
//
//        nodes.add(sink);
//
//        Graph g = new Graph(nodes, source, sink);
//
//        MaxFlow.maximizeFlow(g);
//
//        int left = 0;
//
//        for(Edge e : source.getEdges()) left += e.flow;
//
//
//        return totalDemand - left;