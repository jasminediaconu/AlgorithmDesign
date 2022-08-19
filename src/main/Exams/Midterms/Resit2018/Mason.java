package main.Exams.Midterms.Resit2018;

import java.util.ArrayList;
import java.util.Collections;

public class Mason {
    public static /**
     *  You should implement this method.
     *  @param n the number of jobs.
     *  @param t an array of size n+1, containing the initialization times t_1 through t_n. You should ignore t[0].
     *  @param p an array of size n+1, containing the computation times p_1 through p_n. You should ignore p[0].
     *  @return The minimum latest end time.
     */
    int solve(int n, int[] t, int[] p) {
        ArrayList<Job> jobs = new ArrayList<>();

        for(int i = 1; i <=n; i++) jobs.add(new Job(t[i], p[i]));

        Collections.sort(jobs);

        int startTime = 0;

        for(int i = 0; i < n - 1; i++) {
            startTime += jobs.get(i).initializationTime;
        }

        return startTime;
    }

    static class Job implements Comparable<Job> {
        int initializationTime;
        int computationTime;

        public Job(int initializationTime, int computationTime) {
            this.initializationTime = initializationTime;
            this.computationTime = computationTime;
        }


        @Override
        public int compareTo(Job j) {
            return j.computationTime - this.computationTime;
        }
    }
}

