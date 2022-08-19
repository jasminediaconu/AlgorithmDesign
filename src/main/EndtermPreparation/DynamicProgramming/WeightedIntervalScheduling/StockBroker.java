package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class StockBroker {
    public static int findMaxProfit(int n, int c, int ratio[][]) {
        // Opt(i,k) = min_(y<=k) {p_i y + opt(i + 1, k - y) - kf(y)}

        // y = stocks at price p_i y

        /**
         * ex. 25
         * 1. Build up mem matrix: rows are days and columns are stocks to sell
         * 2. We fill the matrix from bottom to top
         * 3. We return the value at mem[1][1]
         */

        int mem[][] = new int[n+1][c];

//        for(int i = 0; i <= n; i++) {
//            for(int j = 0; j <= n; j++) {
//                mem[i][j] = Math.min();
//            }
//        }

        return mem[1][1];
    }
}
