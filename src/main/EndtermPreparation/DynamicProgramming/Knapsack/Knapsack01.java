package main.EndtermPreparation.DynamicProgramming.Knapsack;

public class Knapsack01 {

    // Find the max value you can obtain.
    // You get a subset of items you can combine to get the optimal solution.
    // Item is either in the optimal solution or not (0/1).
    // Constraints: weight limit, fixed amount of each item.
    public static int knapsackO1(int n, int maxWeight, int weights[], int values[]) {
        // Build up matrix
        int[][] dp = new int[n][maxWeight+1];

        // Base case: fill in first column with 0. (maxWeight = 0, cannot add anything)
        for(int i = 0; i < n; i++) dp[i][0] = 0;

        // Base case: fill in first row with the value of the item if it has weight less than maxWeight, else 0.
        for(int i = 0; i <= maxWeight; i++) dp[0][i] = (weights[0] <= i) ? values[0] : 0;

        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= maxWeight; j++) {
                // If the weight exceeds the limit, take prev optimal solution
                if(weights[i] > j) dp[i][j] = dp[i-1][j];
                // Else take the max between current optimal and previous optimal solution.
                else dp[i][j] = Math.max(dp[i-1][j], values[i] + dp[i-1][j-weights[i]]);
            }
        }

        return dp[n-1][maxWeight];
    }
}
