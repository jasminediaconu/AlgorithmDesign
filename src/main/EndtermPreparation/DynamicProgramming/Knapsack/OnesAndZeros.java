package main.EndtermPreparation.DynamicProgramming.Knapsack;

public class OnesAndZeros {
    public static int findMaxForm(String[] strs, int m, int n) {
        int dp[][] = new int[m + 1][n + 1];

        // Base case: there is no subset
        if (strs == null || m < 0 || n < 0) return 0;

        for (String s : strs) {
            int ones = (int) s.chars().filter(num -> num == '1').count();
            int zeros = s.length() - ones;

            // Backtracking
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }

        return dp[m][n];
    }
}
