package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

import java.util.Arrays;

public class FishSalesman {
    /**
     * @param n the number of days
     * @param P the profits that can be made on day 1 through n on location P are stored in P[1] through P[n].
     * @param Q the profits that can be made on day 1 through n on location Q are stored in Q[1] through Q[n].
     * @return the maximum obtainable profit.
     */
    public static int solve(int n, int[] P, int[] Q) {
        int[][] res = new int[n][3];

        for(int i = 1; i <= n; i++) res[i-1][0] = P[i];
        for(int i = 1; i <= n; i++) res[i-1][1] = 0;
        for(int i = 1; i <= n; i++) res[i-1][2] = Q[i];

        return findMaxProfit(res);
    }

    public static int findMaxProfit(int[][] mat) {
        int rows = mat.length;
        int columns = mat[0].length;

        // Base case
        int maxProfit = -1;

         for (int i = 0; i < columns; i++) maxProfit = Math.max(maxProfit, mat[0][i]);

        for (int i = 1; i < rows; i++) {
            maxProfit = -1;

            for (int j = 0; j < columns; j++) {
                // Check P => Check prev P and prev mid column
                if (j == 0) mat[i][j] += Math.max(mat[i - 1][j], mat[i - 1][j + 1]);
                // Check middle column => Check prev P and prev Q
                else if (j == 1) mat[i][j] += Math.max(mat[i - 1][j - 1], mat[i - 1][j + 1]);
                // Check Q => Check prev Q and prev mid column
                else if (j == 2) mat[i][j] += Math.max(mat[i - 1][j], mat[i - 1][j - 1]);

                // Store max path sum
                maxProfit = Math.max(mat[i][j], maxProfit);
            }
        }

        return maxProfit;
    }
}
