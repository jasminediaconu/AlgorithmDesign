package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

/**
 * Like Longest Increasing Sequence and AstronomyDepartment,
 * however we always have to take the first element.
 */
public class RisingTrends {
    public static int risingTrend(int n, int[] stocks) {

        int mem[] = new int[n+1];

        // Replace all stocks smaller than the first one with the first one
        //for(int i = 2; i <= n; i++) if(stocks[i] < stocks[1]) stocks[i] = stocks[1];

        // Compute the optimal value

        mem[1] = 1;

        int max = 1;

        for(int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if(stocks[i] > stocks[1] && stocks[j] < stocks[i] && mem[i] <= mem[j]) {
                    mem[i] = mem[j] + 1;
                    max = Math.max(mem[i], max);
                }

                mem[i] = max;
            }

        }

        // If this solution doesn't work, substitute all values less than stocks[1] with stocks[1].

        /**
         * For i = 1, 2, . . . , n
         *    M[i] = 1
         *     For j = 1, 2, . . . , i − 1
         *       If P[i] > P[1] And P[j] < P[i] And M[i] < M[j]
         *        M[i] = 1 + M[j]
         * Essentially we are using the formula:
         * OPT(i) = 1 + maxj|j<i,P[j]<P[i] OPT(j)
         */

        return mem[n];
    }
}
