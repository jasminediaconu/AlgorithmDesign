package main.LeetCode.DynamicProgramming;

public class IntegerBreak {
    public int integerBreak(int n) {
        int dp[] = new int[n+1];

        if(n == 2) return 1;
        if(n == 3) return 2;

        dp[2] = 1;
        dp[3] = 2;

        for(int i = 4; i <= n; i++) {
            int ratio = i % 3;
            if(ratio == 0) dp[i] = (int) Math.pow(3, i/3);
            if(ratio == 1 || ratio == 2) {
                if(i-2 > 3) dp[i] = dp[i-2] * 2;
                else dp[i] = (i-2) * 2;
            }
        }

        return dp[n];
    }
}
