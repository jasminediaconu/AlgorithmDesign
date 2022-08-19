package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

/**
 * Your are given an array of integers prices, for which the i-th element
 * is the price of a given stock on day i; and a non-negative integer
 * fee representing a transaction fee.
 *
 * You may complete as many transactions as you like, but you need to pay
 * the transaction fee for each transaction. You may not buy more than 1
 * share of a stock at a time (ie. you must sell the stock share before
 * you buy again.)
 *
 * Return the maximum profit you can make.
 */
public class BestTimeBuySellStocks {
//    Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
//    Output: 8
//    Explanation: The maximum profit can be achieved by:
//    Buying at prices[0] = 1
//    Selling at prices[3] = 8
//    Buying at prices[4] = 4
//    Selling at prices[5] = 9
//    The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;

        int[][] dp = new int[n][2];

        if(n == 0) return 0;

        // You don't have the stock yet.
        dp[0][0] = 0;
        // You paid for the stock, now you have to find when to sell it.
        dp[0][1] = -prices[0]-fee;

        for(int i = 1; i < n; i++) {
            // Decide whether to not do anything or sell
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            // Decide whether to not do anything or buy
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i] - fee);
        }

        return Math.max(dp[n-1][0], dp[n-1][1]);
    }
}
