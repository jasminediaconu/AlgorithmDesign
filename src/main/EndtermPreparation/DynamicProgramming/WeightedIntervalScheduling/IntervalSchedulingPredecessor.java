package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

import java.util.Arrays;

public class IntervalSchedulingPredecessor {
    public static int[] solve(int n, int[] s, int[] f, int[] v) {
        int[] result = new int[n+1];

        // Initialization
        result[0] = 0;
        result[1] = -1;

        // BC
        if(n == 1) return result;

        Interval[] intervals = new Interval[n+1];

        intervals[0] = new Interval(0,0,0);

        for(int i = 1; i <=n; i++)
            intervals[i] = new Interval(s[i], f[i], v[i]);

        Arrays.sort(intervals);

        int j;
        for(int i = n; i >= 2; i--) {
            j = i-1;
            int value = -1;
            while(j != 0) {
                if(intervals[j].f <= intervals[i].s) {
                    value = j;
                    break;
                }
                j--;
            }

            result[i] = value;

        }

        return result;
    }
}