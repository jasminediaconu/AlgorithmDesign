package main.EndtermPreparation.DynamicProgramming.Knapsack;

import java.util.HashSet;
import java.util.Set;

public class UnboundedKnapsack {

    // 518. Coin change - Leetcode
    // Unbounded Knapsack: you have an unbounded quantity of each item type, instead of a bounded quantity.

    /**
     * You are given coins of different denominations and a total amount of money.
     * Write a function to compute the number of combinations that make up that amount.
     * You may assume that you have infinite number of each kind of coin.
     * @param amount
     * @param coins
     * @return number of combinations
     */
    public static int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount+1];

        // Base cases, with amount = 0 there is only one combination.
        if(amount == 0) return 1;
        // If we don't have any coin we have 0 combinations.
        if(n == 0) return 0;

        // Fill in first row with 1 if amount is divisible by coin, else 0.
        for(int j = 1; j <= amount; j++) dp[0][j] = (j % coins[0] == 0) ? 1 : 0;
        // Fill in first column with 1: one combination for 0 amount.
        for(int i = 0; i < n; i++) dp[i][0] = 1;

        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= amount; j++) {
                // If the coin is greater than the amount, it cannot be used, take prev solution.
                if(coins[i] > j) dp[i][j] = dp[i-1][j];
                    // If coin can be used, # combinations = prev solution + solution without the coin.
                else dp[i][j] = dp[i-1][j] + dp[i][j - coins[i]];
            }
        }

        return dp[n-1][amount];
    }

    /**
     * Given coins of different denominations and a total, in how many ways can we combine these coins
     * to get the total?
     */
    public static int coinChange(int[] coins, int total) {
        int n = coins.length;
        int[][] dp = new int[n][total+1];

        for(int i = 0; i < n; i++) dp[i][0] = 1;
        for(int j = 1; j <= total; j++) dp[0][j] = dp[0][j - coins[0]];

        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= total; j++) {
                if(coins[i] > j) dp[i][j] = dp[i-1][j];
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j - coins[i]];
            }
        }

        return dp[n-1][total];
    }

    /**
     * Find the MINIMUM number of coins to reach the amount.
     * 322. Coin Change - Leetcode
     * @param coins
     * @param amount
     * @return
     */
    public static int minimumAmountOfCoins(int[] coins, int amount) {
        int n = coins.length;

        if(amount == 0) return 0;

        int dp[][] = new int[n][amount+1];

        for(int i = 0; i < n; i++) dp[i][0] = 0;
        for(int i = 0; i <= amount; i++) dp[0][i] = i % coins[0] == 0 ? i/coins[0] : Integer.MAX_VALUE/2;

        for(int i = 1; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                if(j < coins[i]) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.min(dp[i-1][j], dp[i][j-coins[i]]+1);

            }
        }

        return dp[n-1][amount] == Integer.MAX_VALUE/2 ? -1 : dp[n-1][amount];
    }

    /**
     * This is to find the DP matrix to check which ones are the coins part of the minimum subset.
     * @param coins
     * @param total
     * @return
     */
    public static int[][] minimumNumberOfCoins(int[] coins, int total) {
        int n = coins.length;
        int[][] dp = new int[n][total+1];

        for(int i = 0; i < n; i++) dp[i][0] = 0;
        for(int j = 1; j <= total; j++) dp[0][j] = j;

        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= total; j++) {
                if(coins[i] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.min(dp[i-1][j], dp[i][j-coins[i]] + 1);
            }
        }

        return dp;
    }

    public static Set<Integer> minimumSubSetOfCoins(int[] coins, int[][] dp, int total) {
        int i = coins.length - 1;
        int j = total;
        int min = dp[i][j];

        Set<Integer> result = new HashSet<>();

        while(j > 0) {
            if(i == 0) {
                if(j % coins[i] == 0) result.add(coins[i]);
                break;
            }
            if(dp[i-1][j] == min) i--;
            else {
                result.add(coins[i]);
                j = j - coins[i];
                min = dp[i][j];
            }
        }

        return result;
    }



}
