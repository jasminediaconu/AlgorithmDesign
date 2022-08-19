package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

public class WildcardMatching {
    /**
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     */
    public static boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean dp[][] = new boolean[n+1][m+1];

        char[] pattern = p.toCharArray();

        // Base case
        dp[0][0] = true;

        // Filling the wildcards
        for(int i = 1; i <= m; i++) {
            if(pattern[i-1]=='*' && dp[0][i - 1]) dp[0][i] = true;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                // If the characters match or char of p is '?'
                // then we check the prev match
                if (s.charAt(i-1) == pattern[j-1] || pattern[j-1] == '?')
                    dp[i][j] = dp[i-1][j-1];
                // If wild char then we need to check left and up
                else if (pattern[j-1] == '*') {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }

            }
        }

        return dp[n][m];
    }
}
