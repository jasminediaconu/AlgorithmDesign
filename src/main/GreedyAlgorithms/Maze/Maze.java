package main.GreedyAlgorithms.Maze;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * A maze is represented by a directed (and weighted, but you only need that for the next problem)
 * graph G=(V,E), where V denotes the set containing n vertices and E the set containing m directed edges.
 * Each vertex represents an intersection or end point in the maze and the edges represent paths between them.
 * A directed edge is used for (downhill) tunnels and holes that you can jump into, but where it is impossible
 * to get back.
 * Because of this, it may become impossible to reach the exit.
 *
 * Design and implement an algorithm that determines whether it is possible
 * to get from a given point s to the exit of the maze t.
 * Your algorithm should print yes or no and run in O(n+m) time.
 *
 * Some exercises (like this one) require you to think about how to store your input data in an efficient
 * format. In some of these exercises we will therefore ask you to read the data from files yourself.
 * Below we give some explanation as to how reading such files works in WebLab.
 *
 * Reading input and writing output
 * The input for this exercise will be read from a file that is stored on WebLab
 * and passed to your solve method as an InputStream.
 * You can wrap this stream in a Scanner to make reading easier.
 * This has already been done in the solution template on the right.
 * The output should be returned as a String.
 *
 * Description of input and output
 * The first line of the input contains four integers separated by a space: n, m, s and t.
 * The integers n and m are defined as above and you may assume the vertices are labelled 1 up to
 * and including n. E.g. if n equals 6, the vertices are labelled 1,2,3,4,5 and 6.
 * The integers s and t are the numbers of the vertices representing your starting point and the exit
 * of the maze, respectively.
 * The next m lines are the directed edges. Each line contains three integers:
 * the two numbers of the vertices associated with this edge and the length (i.e., positive weight)
 * of the edge. For example, a line containing the integers 3, 6 and 9 indicates that there is an edge
 * from vertex 3 to vertex 6 with length 9.
 */
public class Maze {
    // Implement the solve method to return the answer to the problem posed by the Input Stream.
    public static String run(InputStream in) {
        return new Maze().solve(in);
    }

    public String solve(InputStream in) {
        Scanner scanner = new Scanner(in);

        // Scanning first row
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();
        int t = scanner.nextInt();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(0, new Node());
        // Initialize list
        for(int i = 1; i <= n; i++)
            nodes.add(i, new Node());

        // Scan the rest and store in Node
        while (scanner.hasNextInt()) {
            int first = scanner.nextInt();
            int second = scanner.nextInt();

            nodes.get(first).outgoingEdges.add(nodes.get(second));

            // Skip the edge weight
            scanner.nextInt();
        }

        scanner.close();

        Queue<Node> queue = new LinkedList<Node>();

        queue.add(nodes.get(s));

        while(!queue.isEmpty()) {
            Node first = queue.poll();

            if(first.marked) continue;

            first.marked = true;

            if(first == nodes.get(t)) return "yes";

            for(Node next : first.outgoingEdges) {
                    queue.add(next);
            }
        }

        return "no";
    }
}