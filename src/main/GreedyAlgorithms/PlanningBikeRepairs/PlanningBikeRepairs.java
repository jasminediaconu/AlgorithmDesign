package main.GreedyAlgorithms.PlanningBikeRepairs;

import java.io.InputStream;
import java.util.*;

/**
 * Mike runs a bike repair service for students.
 * Students can plan a time to bring in their bike on his website,
 * where they also enter a description of what is broken.
 * Mike is very good at his job and he can flawlessly predict how long each repair will cost.
 * To cater the need of students to have a working bike at all time,
 * he promises his customers to finish the repair as soon as possible after the bike is brought in.
 *
 * Now Mike wants to know how many of his employees to schedule for a day.
 * He has the list of repairs for this day and he wants you to develop an algorithm to tell him
 * how many people he needs.
 *
 * You get the list in the following format: On the first line is the amount of repairs.
 * The next lines list the repairs with on each line the time the repair starts followed
 * by the time the repair will take.
 */
public class PlanningBikeRepairs {
    // Implement the solve method to return the answer to the problem posed by the inputstream.
    public static String run(InputStream in) {
        return new PlanningBikeRepairs().solve(in);
    }

    public String solve(InputStream in) {
        Scanner scanner = new Scanner(in);

        int n = scanner.nextInt();

        Tuple[] repairs = new Tuple[n];

        for (int i = 0; i < n; i++) {
            int startTime = scanner.nextInt();
            repairs[i] = new Tuple(startTime, startTime + scanner.nextInt());
        }

        scanner.close();

        Arrays.sort(repairs);

        int employees = 0;

        for (int i = 1; i < n; i++) {
            int count = 1;
            for (int j = 0; j < i; j++) {
                if ((repairs[j].endTime) > repairs[i].startTime) {
                    count++;
                }
            }
            if (count > employees) {
                employees = count;
            }
        }

        return "" + employees;
    }

    class Tuple implements Comparable<Tuple> {
        public int startTime;
        public int endTime;

        public Tuple(int startTime, int duration) {
            this.startTime = startTime;
            this.endTime = duration;
        }

        @Override
        public int compareTo(Tuple t) {
            return this.startTime - t.startTime;
        }
    }
}
