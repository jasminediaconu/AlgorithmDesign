package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

public class IsSubsequence {
    public static boolean isSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();

        int dp[][] = new int[n+1][m+1];

        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                boolean isEqual = s.charAt(i-1) == t.charAt(j-1);
                dp[i][j] = isEqual ? dp[i-1][j-1]+1 : Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[n][m] == n;
    }
}
