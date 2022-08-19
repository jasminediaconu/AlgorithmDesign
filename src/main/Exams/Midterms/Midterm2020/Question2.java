package main.Exams.Midterms.Midterm2020;

import java.util.*;

/**
 * You have a revolutionary new idea for a video conference system.
 * After all your computer networks lessons you remember the power of
 * Peer-to-Peer and plan to base your system on this!
 * Given n students that want to attend the video lecture, with “student 1”
 * (usually not a student but a lecturer) wanting to share their screen to all students,
 * you want to minimise the maximum failure probability across all students in the network.
 *
 * In other words, given a set of connections and their success rates 0≤rs≤1 between students,
 * how do you ensure that the failure probability of the worst connection is as low as possible,
 * and what is this smallest worst failure probability if all students want to see the video stream
 * from student 1?
 *
 * For example: if we have 3 students with:
 * - a connection from 1 to 2 with success rate 0.8
 * - a connection from 1 to 3 with success rate 0.7
 * - a connection from 2 to 3 with success rate 0.99
 *
 * The smallest worst failure probability is 0.208.
 * (Send video from 1 to 2 directly with a failure rate of 0.2,
 * and to 3 via 2 with a failure rate of 0.208. This is the optimal solution,
 * where the worst failure probability is 0.208)
 *
 * You may assume all students have infinite up/download speed, so only the failure rate is relevant here.
 *
 * Note: Remember that you should add a few lines (between 3 and 10) of comments explaining what your code does as the basis for the interview and an anti-fraud measure.
 *
 */
public class Question2 {
    public static double minimalLargestFailureChance(int n, Set<Connection> connections) {

        // Create MST Set
        Set<Integer> mstSet = new HashSet<>();

        // Create PQ
        PriorityQueue<Connection> q = new PriorityQueue<>(new Comparator<Connection>() {
            @Override
            public int compare(Connection o1, Connection o2) {
                int result = Integer.compare(o1.computer1, o2.computer1);
                if(result == 0) return Double.compare(o2.successRate, o1.successRate);
                return result;
            }});

        for(Connection c : connections) {
            if(c.computer1 == 1) {
                q.add(c);
                break;
            }
        }

        double[] distances = new double[n];

        for (int i = 0; i < distances.length; i++) distances[i] = Integer.MAX_VALUE;

        distances[0] = 0;

        double totalRate = 0;

        while (mstSet.size() != n && !q.isEmpty()) {
            Connection current = q.poll();

            mstSet.add(current.computer1);
            mstSet.add(current.computer2);
            if(totalRate == 0) totalRate = current.successRate;
            else totalRate *= current.successRate;

            for(Connection con : connections) {
                if(con.computer1 == current.computer2) {
//                    double currShort = current.successRate * con.successRate;
                    if (distances[con.computer2-1] > totalRate) {
                        q.add(con);
                        distances[con.computer2-1] = totalRate;
                    }
                }

            }

        }

        return 1 - totalRate;


//        ArrayList<Connection> connectionsList = new ArrayList<>();
//
//        for(Connection c : connections) connectionsList.add(c);
//
//        // Sort by computer and success rate (1 computer with greatest success rate at the top)
//        Collections.sort(connectionsList, new Comparator<Connection>() {
//            @Override
//            public int compare(Connection o1, Connection o2) {
//                int result = Integer.compare(o1.computer1, o2.computer1);
//                if(result == 0) return Double.compare(o2.successRate, o1.successRate);
//                return result;
//            }
//        });
//
//        Set<Integer> result = new HashSet<>();
//
//        Connection firstConnection = connectionsList.remove(0);
//        int currentComputer = firstConnection.computer2;
//        double maxSuccessRate = firstConnection.successRate;
//
//        result.add(firstConnection.computer1);
//        result.add(firstConnection.computer2);
//
//        // Remove connections from the list
//        while(!connectionsList.isEmpty()) {
//            Connection currentConnection = connectionsList.remove(0);
//
//            if(currentComputer == currentConnection.computer1 && !result.contains(currentConnection.computer2)) {
//            // Update failure rate with the greatest
//                result.add(currentConnection.computer2);
//                double successRate = currentConnection.successRate * maxSuccessRate;
//                if (successRate < maxSuccessRate)
//                    maxSuccessRate = successRate;
//
//                currentComputer = firstConnection.computer2;
//                }
//
//
//        }
//        return 1 - maxSuccessRate;
    }





}
