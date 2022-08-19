package main.Exams.Endterms.Endterm2020.ResearchProject;

import java.util.ArrayList;

public class ResearchProject {
    /**
     *   You should implement the method below. Note that you can use the graph structure below.
     *   @param n The number of students.
     *   @param m The number of supervisors.
     *   @param student_availability_mon is an array of size (n+1) that is true iff student i is available on Monday. You should ignore student_availability_mon[0].
     *   @param student_availability_tues is an array of size (n+1) that is true iff student i is available on Tuesday. You should ignore student_availability_tues[0].
     *   @param supervisor_availability_mon is an array of size (m+1) that is true iff supervisor j is available on Monday. You should ignore supervisor_availability_mon[0].
     *   @param supervisor_availability_tues is an array of size (m+1) that is true iff supervisor j is available on Tuesday. You should ignore supervisor_availability_tues[0].
     *   @param selected is an array of size (n+1)x(m+1) that is true iff student i selected supervisor j. You should use entries selected[1][1] through selected[n][m].
     *   @return true iff there is a valid allocation of students over supervisors.
     */
    public static boolean areThereGroups(int n, int m, boolean[] student_availability_mon,
                                         boolean[] student_availability_tues, boolean[] supervisor_availability_mon, boolean[] supervisor_availability_tues, boolean[][] selected) {

        Node source = new Node(0, -n);
        Node sink = new Node(-1, n);

        ArrayList<Node> nodes = new ArrayList<>();

        nodes.add(source);

        // Create arrays for all student nodes
        Node students[] = new Node[n+1];
        Node students_mon[] = new Node[n+1];
        Node students_tue[] = new Node[n+1];

        // Add all the students
        for(int i = 1; i <= n; i++) {
            Node student =  new Node(i, 0);
            // Create nodes for Mon and Tue for each student
            Node student_mon = new Node(i*2, 0);
            Node student_tue = new Node(i*3, 0);

            // Connect source to students
            source.addEdge(student, 0, 1);

            // Connect students based on their availability
            if(student_availability_mon[i]) student.addEdge(student_mon, 0, 1);
            if(student_availability_tues[i]) student.addEdge(student_tue, 0, 1);

            // Add nodes to the arrays
            students[i] = student;
            students_mon[i] = student_mon;
            students_tue[i] = student_tue;
        }

        // Create nodes for all supervisors
        Node[] supervisors = new Node[m+1];
        Node[] supervisors_mon = new Node[m+1];
        Node[] supervisors_tue = new Node[m+1];

        // Add all supervisors
        for(int i = 1; i <= m; i++) {
            Node supervisor = new Node(i + 3*n, 0);

            // Create nodes for Mon and Tue for each supervisor
            Node supervisor_mon = new Node(i + m + 3*n, 0);
            Node supervisor_tue = new Node(i + 2*m + 3*n, 0);

            // Connect supervisor to sink
            supervisor.addEdge(sink, 3, 12);

            // Connect supervisors based on their availability
            if(supervisor_availability_mon[i]) supervisor_mon.addEdge(supervisor, 0, 10);
            if(supervisor_availability_tues[i]) supervisor_tue.addEdge(supervisor, 0, 10);

            // Add nodes to the arrays
            supervisors[i] = supervisor;
            supervisors_mon[i] = supervisor_mon;
            supervisors_tue[i] = supervisor_tue;
        }

        // Connect students to supervisors
        for(int i = 1; i <= n; i++) {
            Node student_mon = students_mon[i];
            Node student_tue = students_tue[i];

            for(int j = 1; j <= m; j++) {
                Node supMon = supervisors_mon[j];
                Node supTue = supervisors_tue[j];

                if(selected[i][j]) {
                    student_mon.addEdge(supMon, 0, 1);
                    student_tue.addEdge(supTue, 0, 1);
                }
            }
        }

        // Add all student nodes to the node list
        for(int i = 1; i <= n; i++) {
            nodes.add(students[i]);
            nodes.add(students_mon[i]);
            nodes.add(students_tue[i]);
        }

        // Add all supervisor nodes to the node list
        for(int i = 1; i <= m; i++) {
            nodes.add(supervisors[i]);
            nodes.add(supervisors_mon[i]);
            nodes.add(supervisors_tue[i]);
        }

        nodes.add(sink);

        Graph g = new Graph(nodes);

        return g.hasCirculation();
    }
}
