package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

import java.util.Arrays;

public class WeightedIntervalScheduling {
    public static int solve(int n, int[] s, int[] f, int[] v, int[] p) {
        int[] mem = new int[n + 1];

        mem[0] = 0;

        for(int i = 1; i < mem.length; i++) {
            if(p[i] == -1) mem[i] = Math.max(v[i], mem[i-1]);
            else mem[i] = Math.max(v[i] + mem[p[i]], mem[i-1]);
        }
        return mem[n];
    }

    public static int findOptimalInterval(WeightedInterval[] intervals) {
        int n = intervals.length;

        // Base case
        if (n == 0) return 0;

        // Sort array
        Arrays.sort(intervals);

        int[] profits = new int[n];
        profits[0] = intervals[0].weight;

        // Find optimal value
        for(int i = 1; i < n; i++) {
            int profit = intervals[i].weight;
            int previousJob = findNonConflictingJob(intervals, i);
            if(previousJob != -1) profit += profits[previousJob];

            profits[i] = Math.max(profit, profits[i-1]);
        }

        return profits[n-1];
    }

    public static int findNonConflictingJob(WeightedInterval[] intervals, int i) {

        for(int j = i-1; j >= 0; j--) {
            if(intervals[j].f <= intervals[i].s) return j;
        }

        return -1;
    }


    public static int[] findPredecessors(int n, int[] s, int[] f, int[] v) {

        int[] predecessors = new int[n+1];

        Arrays.fill(predecessors, -1);

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j < i; j++) {
                if(f[j] <= s[i]) {
                    predecessors[i] = j;
                }
            }

        }
        return predecessors;
    }
}



