package main.Exams.Midterms.Resit2018;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class HighPriority {

    public static /**
     *  You should implement this method.
     *  @param n the number of jobs.
     *  @param t an array of size n+1, containing the initialization times t_1 through t_n. You should ignore t[0].
     *  @param p an array of size n+1, containing the computation times p_1 through p_n. You should ignore p[0].
     *  @param S an array of size n+1, containing at position S[i] the start time of the ith job that will run. I.e. S[1] contains the start time of the first job that will run.
     *  @param I an array of size n+1, containing at position I[i] the index j of the ith job that will run. I.e. If I[4] = 3, then the fourth job to run is the job with initialization time t[3] and processing time p[3].
     *  @param x the time at which the high priority job should be started.
     *  @return The number of jobs that are initialized at or later than x.
     */
    int solve(int n, int[] t, int[] p, int[] S, int[] I, int x) {
        // Mason is now asked whether he can run a high-priority job at time x. Before he decides,
        // he wants to know how many jobs would be delayed if he started the initialization of this
        // job as soon as the last job started before time x completed its initialization.
        // Implement a divide-and-conquer algorithm that returns for a given time x,
        // the number of jobs that are initialized at or later than x.
        // (Hint: an Ω(n) solution is worth at most 2 points.)

        return divide(S, x, 0, 1, n + 1);
    }

    public static int divide(int[] startTimes, int x, int count, int index, int end) {
        int middle = (index + end) / 2;

        if(index + 1 == end) {
            if(x <= startTimes[middle]) count++;
            return count;
        }

            int countLeft = divide(startTimes, x, count, middle, end);
            int countRight = divide(startTimes, x, count, index, middle);

            count = countLeft + countRight;

        return count;
    }


}
