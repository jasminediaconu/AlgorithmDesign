package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class BuySellStocks {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int max = 0;

        for(int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                int price = prices[j] - prices[i];
                if(max < price) max = price;
            }
        }

        return max;
    }

    // 309. Best Time to Buy and Sell Stock with Cooldown - Leetcode
    public static int maxStockProfit(int[] prices) {
        int n = prices.length;

        int[][] dp = new int[n][2];

        if(n == 0) return 0;

        // You don't have the stock yet.
        dp[0][0] = 0;
        // You paid for the stock, now you have to find when to sell it.
        dp[0][1] = -prices[0];

        // Max between selling the stock and cooling down
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        // Max between buying the stock and cooling down
        dp[1][1] = Math.max(dp[0][1], dp[0][0] - prices[1]);

        for(int i = 2; i < n; i++) {
            // You check if it's better to buy or cool down
            dp[i][0] = Math.max(dp[i-1][1] - prices[i], dp[i-1][0]);
            // You check if it's better to sell or cool down
            dp[i][1] = Math.max(dp[i-2][0] + prices[i], dp[i-1][1]);
        }

        return Math.max(dp[n-1][0], dp[n-1][1]);
    }
}
