package main.Tutorials.GreedyAlgorithms;

import java.util.Arrays;

public class AverageFinishingTime {
    public static /**
     * @param n the number of jobs
     * @param runtimes the rutimes of all jobs 1 through n. Note that runtimes[0] should be ignored!
     * @return an array with the end times of the jobs. Also here, do not use [0].
     */
    int[] minAvgEndtimes(int n, int[] runtimes) {
        int[] endtimes = new int[n+1];

        Arrays.sort(runtimes);

        for(int i = 1; i <= n; i++) endtimes[i] = endtimes[i-1] + runtimes[i];

        return endtimes;
    }
}
