package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class LongestValidParentheses {
    public static int longestValidParentheses(String s) {
        int n = s.length();

        int[] dp = new int[n];

        int max = 0;

        for(int i = 1; i < n; i++) {
            if(s.charAt(i) == ')') {
                if(s.charAt(i-1) == '(') dp[i] = i >= 2 ? dp[i-2] + 2 : 2;
                else if(i - dp[i - 1] > 0 && s.charAt(i - dp[i-1]-1) == '(')
                    dp[i] = (i - dp[i-1]) >= 2 ? dp[i-1] + dp[i - dp[i-1] - 2] + 2 : dp[i-1] + 2;

                Math.max(max, dp[i]);
            }
        }

        return max;

    }
}
