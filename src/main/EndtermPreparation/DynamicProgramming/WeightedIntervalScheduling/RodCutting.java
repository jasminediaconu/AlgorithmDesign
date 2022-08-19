package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class RodCutting {
    public static int getMaximumPrice(int prices[], int n) {
        int m = prices.length;

        int[][] result = new int[n+1][m];

        for(int i = 0; i < n+1; i++) {
            for(int j = 0; j < m; j++) {
                if(i == 0 || j == 0) result[i][j] = 0;
                else if(j < i) result[i][j] = result[i-1][j];
                else result[i][j] = Math.max(result[i-1][j], result[i][j-i] + prices[i]);
            }
        }

        return result[n][m-1];
    }
}
