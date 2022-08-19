package main.EndtermPreparation.DynamicProgramming.RNAStructure;

public class TightOnTime {
    /**
     * Implement this method
     *
     * @param n - the number of assignments
     * @param h - the number of hours you can spend
     * @param f - the function in the form of a (n + 1) x (h + 1) matrix.
     *          Index 0 of the number of assignments should be ignored.
     *          Index 0 of the number of hours spend is the minimum grade for this assignment.
     * @return the max grade achievable
     */

    public static int maxGrade(int n, int h, int[][] f) {
        if(n == 0) return 0;
        if(n == 1) return f[n][h];

        return  findMaxGrade(n, h, 1, 0, f);
    }

    // Recursive solution: O(2^n)
    public static int findMaxGrade(int n, int h, int index, int totalGrade, int[][] f) {
        int maxGrade = 0;

        // Go through all the assignments one by one.
        if(index == n+1) return totalGrade;

        // Find optimal value for each assignment with the hours available
        for(int i = 0; i <= h; i++) {
            // Use i hours for this assignment then go to the next one
            int result = findMaxGrade(n, h - i, index+1, totalGrade+f[index][i], f);
            // If this increases the max grade, update it
            if(maxGrade < result) maxGrade = result;
        }

        return maxGrade;
    }

    // Memoization solution: O(n^3)
    public static int maxGradeDP(int n, int h, int[][] f) {
        if(n == 0) return 0;
        if(n == 1) return f[n][h];

        int dp[][] = new int[n+1][h+1];

        // Start with assignment at i = 1
        for(int i = 1; i <= n; i++) {
            // You can have an assignment with 0 hours
            for(int j = 0; j <= h; j++) {
                // Find the optimal value inside the loop
                int max = 0;

                // We check the value at j-k because k are the hours we invest in the current assignment.
                for(int k = 0; k <= j; k++)
                    max = max < dp[i-1][j-k] + f[i][k] ? dp[i-1][j-k] + f[i][k] : max;

                // Store the optimal value
                dp[i][j] = max;
            }
        }

        return dp[n][h];
    }


    public static int findMaxGradeSol(int n, int h, int[][] f ) {
        // Pseudo-polynomial algorithm based on h.
        int dp[][] = new int[n+1][h+1];

        // In n not in OPT(n, h) -> OPT(n-1, h)
        // Else OPT(n, h) -> OPT(n, h - h_n)

        // OPT(i, h) -> max(OPT(i-1, h), v_1 + (i-1, h - h_i))

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= h; j++) {


                int max = 0;

                for(int k = j; k <= h; k++) {
                    // find max
                    max = Math.max(max, f[i][j] + dp[i-1][h-k]);
                }

                dp[i][j] = Math.max(dp[i-1][h], max);

            }
        }


        return dp[n][h];
    }
}
