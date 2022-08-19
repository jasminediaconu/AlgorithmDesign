package main.LeetCode.DynamicProgramming;

public class SortedVowels {
    public int countVowelStrings(int n) {
        if(n == 1) return 5;

        int[][] dp = new int[n][5];

        for(int i = 0; i < 5; i++) dp[1][i] = i+1;

        for (int i = 2; i < n; i++) {
            dp[i][0] = 1;
            dp[i][1] = dp[i][0] + dp[i-1][1];
            dp[i][2] = dp[i][1] + dp[i-1][2];
            dp[i][3] = dp[i][2] + dp[i-1][3];
            dp[i][4] = dp[i][3] + dp[i-1][4];
        }

        return dp[n-1][4];

        // Base case: 1 -> 5
        //            2 -> 25 - 10 = 15
        //            3 -> 125


        // 5


        // aa ae ai ao au
        // ea ee ei eo eu
        // is ie ii io iu
        // os oe oi oo ou
        // ua ue ui uo uu



    }
}
