package main.Exams.Midterms.Midterm2018;

import java.io.InputStream;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * The first line of input has two numbers C and m the number of court cases and courtrooms respectively.
 * After that follow C lines each comprised of a single word representing the name of the
 * case and a single integer 0≤t≤10000 that represents the time the case takes in hours,
 * such that ith line gives the time of court case i.
 *
 * You should output the total number of hours we need to open the court room.
 */
public class SchedulingCourtCases {
    // Implement the solve method to return the answer to the problem posed by the inputstream.
    public static String run(InputStream in) {
        return new SchedulingCourtCases().solve(in);
    }

    private int numCases;

    private int numCourtRooms;

    private long[] caseTimes;

    public String solve(InputStream in) {
        parseInput(in);
        return Long.toString(computeLastFinish());
    }

    /**
     *  You should implement this method to compute when the last court case will be finished.
     */
    private long computeLastFinish() {

        PriorityQueue<Long> rooms = new PriorityQueue<>();

        long maxResult = 0;

        for(int i = 0; i < numCourtRooms; i++) {
            if(caseTimes[i] > maxResult) maxResult = caseTimes[i];
            rooms.add(caseTimes[i]);
        }

        if(numCases == numCourtRooms) return maxResult;

        for(int i = numCourtRooms; i < numCases; i++) {
            long firstRoom = rooms.poll();

            firstRoom += caseTimes[i] + 1;

            if(firstRoom > maxResult) maxResult = firstRoom;

            rooms.add(firstRoom);
        }

        return maxResult;
    }

    /**
     *  This method parses the input from an inputstream. You should not need to modify this method.
     */
    private void parseInput(InputStream in) {
        Scanner sc = new Scanner(in);
        this.numCases = sc.nextInt();
        this.numCourtRooms = sc.nextInt();
        this.caseTimes = new long[this.numCases];
        for (int i = 0; i < this.numCases; i++) {
            sc.next();
            this.caseTimes[i] = sc.nextLong();
        }
        sc.close();
    }
}
