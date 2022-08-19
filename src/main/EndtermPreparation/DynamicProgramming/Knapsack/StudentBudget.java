package main.EndtermPreparation.DynamicProgramming.Knapsack;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StudentBudget {
    // inputstream.
    public static String run(InputStream in) {
        return new StudentBudget().solve(in);
    }

    public String solve(InputStream in) {
        Scanner sc = new Scanner(in);

        int maxWeight = sc.nextInt();
        int n = sc.nextInt();

        int[] weights = new int[n];
        int[] values = new int[n];

        for(int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
        }

        sc.close();

        int[][] dp = new int[n][maxWeight+1];

        for(int i = 0; i < n; i++) dp[i][0] = 0;
        for(int i = 0; i <= maxWeight; i++) {
            if(weights[0] <= i) dp[0][i] = values[0];
            else dp[0][i] = 0;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= maxWeight; j++) {
                if(weights[i] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(dp[i-1][j], values[i] + dp[i-1][j-weights[i]]);
            }
        }

        return dp[n-1][maxWeight] + "";
    }

    public static int[][] getMatrix(int weights[], int values[], int maxWeight) {
        int n = weights.length;
        int[][] dp = new int[n][maxWeight+1];

        for(int i = 0; i < n; i++) dp[i][0] = 0;
        for(int i = 0; i <= maxWeight; i++) {
            if(weights[0] <= i) dp[0][i] = values[0];
            else dp[0][i] = 0;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= maxWeight; j++) {
                if(weights[i] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(dp[i-1][j], values[i] + dp[i-1][j-weights[i]]);
            }
        }

        return dp;
    }

    public static Set<Integer> getSubset(int weights[], int[][] dp, int maxWeight) {
        int i = weights.length - 1;
        int j = maxWeight;
        int max = dp[i][j];

        Set<Integer> result = new HashSet<>();

        while(j > 0) {
            if(i == 0) {
                if(j >= weights[i]) result.add(weights[i]);
                break;
            }
            if(dp[i][j] == dp[i-1][j]) i--;
//            if(dp[i-1][j-1] == max) {
//                i--;
//                j--;
//            }
            else {
                result.add(weights[i]);
                j = j - weights[i];
                i--;
                max = dp[i][j];
            }
        }

        return result;
    }
}
