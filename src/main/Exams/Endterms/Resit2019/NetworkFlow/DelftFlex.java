package main.Exams.Endterms.Resit2019.NetworkFlow;

import java.util.ArrayList;

public class DelftFlex {
    /**
     *   You should implement the method below. Note that you can use the graph structure below.
     *   @param n The number of students.
     *   @param m The number of tasks.
     *   @param hours is an array of size (n+1)*2 that contains the minimum hours for student i in hours[i][0] and the maximum hours for student i in hours[i][1]. You should ignore hours[0].
     *   @param workb is an array of size (m+1) that contains the number of hours tasks 1 through m take. You should ignore work[0].
     *   @param skilled is an array of size (n+1)*(m+1) such that skilled[i][j] == 1 iff student i can do task j. You should ignore all skilled[i][0] and skilled[0][j].
     *   @return true iff all tasks can be completed.
     */
    public static boolean solve(int n, int m, int[][] hours, int[] work, int[][] skilled) {
        int maxHours = 0;

        // Total sum of hours
        for(int i = 1; i <= n; i++) maxHours += work[i];

        Node source = new Node(-1, -maxHours);
        Node sink = new Node(-2, maxHours);
        ArrayList<Node> nodes = new ArrayList<>();

        nodes.add(source);

        // Add assistants
        Node assistants[] = new Node[n+1];

        for(int i = 1; i <= n; i++) {
            // Demand 0 since we don't care if we are taking the assistant or not
            // We only care about covering all hours at the lab
            Node assistant = new Node(i, 0);
            assistants[i] = assistant;
            source.addEdge(assistant, hours[i][0], hours[i][1]);
            nodes.add(assistants[i]);
        }

        // Add tasks
        Node tasks[] = new Node[m+1];

        for(int i = 1; i <= m; i++) {
            // Demand is needed here since we want to check
            // whether we can cover up all tasks
            Node task = new Node(n+i, 0);
            task.addEdge(sink, 0, work[i]);
            tasks[i] = task;
            nodes.add(tasks[i]);

        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(skilled[i][j] == 1) {
                    // Capacity is the max capacity students can work
                    assistants[i].addEdge(tasks[j], 0, hours[i][1]);
                }
            }
        }

        nodes.add(sink);

        Graph g = new Graph(nodes);

        return g.hasCirculationOfAtLeast(maxHours);
    }
}




















//    int hoursNeeded = 0;
//        for(int i = 1; i <= m; i++) hoursNeeded += work[i];
//
//                // Create a node with demand equal to the total amount of hours
//                Node s = new Node(0, -hoursNeeded);
//
//                ArrayList<Node> nodes = new ArrayList<>();
//
//        nodes.add(s);
//
//        // Add students with edge equal to lower and upper bound of working hours
//        // The demand is not needed (can be calculated from the bounds)
//        for(int i = 1; i <= n; i++) {
//        Node student = new Node(i, 0);
//        nodes.add(student);
//        s.addEdge(student, hours[i][0], hours[i][1]);
//        }
//
//        // Add tasks with demand the amount of hours that they require
//        for(int i = 1; i <= m; i++) {
//        int k = i + n;
//        Node task = new Node(k, work[i]);
//        nodes.add(task);
//
//        // If the student can work on the task, add an edge with capacity, the max hours she can work
//        for(int j = 1; j <= n; j++) {
//        if(skilled[j][i] == 1) {
//        nodes.get(j).addEdge(task, 0, hours[i][1]);
//        }
//        }
//        }
//
//        Graph g = new Graph(nodes);
//
//        return g.hasCirculationOfAtLeast(hoursNeeded);