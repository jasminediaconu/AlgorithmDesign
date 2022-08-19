package main.EndtermPreparation.NetworkFlow.DoctorsWithoutWeekends;

import java.util.ArrayList;

public class DoctorsWithoutWeekends {

    /**
     * Pretend there were only two vacation periods.
     * For each vacation period there are m holidays.
     *
     * @param n The number of doctors.
     * @param m The number of holidays.
     * @param availability the availability c of each doctor.
     * @param daysVacationOne if doctor i is available to work on day i of holiday one.
     * @param daysVacationTwo if doctor i is available to work on day i of holiday two.
     * @return
     */
    public static boolean areThereEnoughDoctors(int n, int m, int[] availability,
                                                boolean[] daysVacationOne, boolean[] daysVacationTwo) {
        // Add sink and source nodes
        Node source = new Node(0, -m);
        Node sink = new Node(-1, m);

        ArrayList<Node> nodes = new ArrayList<>();

        nodes.add(source);

        // Create arrays for all doctor nodes
        Node doctors[] = new Node[n+1];

        Node vacation_1[] = new Node[n+1];
        Node vacation_2[] = new Node[n+1];

        // Add all the doctors
        for (int i = 1; i <= n; i++) {
            Node doctor = new Node(i, 0);
            // Create nodes for Mon and Tue for each student
            Node vacation1 = new Node(i * 2, 0);
            Node vacation2 = new Node(i * 3, 0);

            // Connect source to doctors
            source.addEdge(doctor, 0, availability[i]);

            // Connect doctors based on their availability
            doctor.addEdge(vacation1, 0, 1);
            doctor.addEdge(vacation2, 0, 1);

            // Add nodes to the arrays
            doctors[i] = doctor;
            vacation_1[i] = vacation1;
            vacation_2[i] = vacation2;
        }

        // Create nodes for all holidays
        Node[] holidays_One = new Node[m+1];
        Node[] holidays_Two = new Node[m+1];

        // Add all holidays
        for (int i = 1; i <= m; i++) {
            // Create nodes for holiday one and holiday two
            Node holidayOne = new Node(i + 3 * n, 0);
            Node holidayTwo = new Node(i + m + 3 * n, 0);

            // Connect holiday to sink
            holidayOne.addEdge(sink, 0, 1);
            holidayTwo.addEdge(sink, 0, 1);

            // Add nodes to the arrays
            holidays_One[i] = holidayOne;
            holidays_Two[i] = holidayTwo;
        }

        // Connect vacation period to days
        for (int i = 1; i <= n; i++) {
            Node vac_one = vacation_1[i];
            Node vac_two = vacation_2[i];

            for (int j = 1; j <= m; j++) {
                if (daysVacationOne[i]) vac_one.addEdge(holidays_One[i], 0, 1);
                if (daysVacationTwo[i]) vac_two.addEdge(holidays_Two[i], 0, 1);

            }
        }

        // Add all doctor nodes to the node list
        for (int i = 1; i <= n; i++) {
            nodes.add(doctors[i]);
            nodes.add(vacation_1[i]);
            nodes.add(vacation_2[i]);
        }

        // Add all holiday nodes to the node list
        for (int i = 1; i <= m; i++) {
            nodes.add(holidays_One[i]);
            nodes.add(holidays_Two[i]);
        }

        nodes.add(sink);

        Graph g = new Graph(nodes);

        return g.hasCirculation();
    }

}
