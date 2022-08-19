package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

// 115. Distinct Subsequences - Leetcode

/**
 * Given two strings s and t,
 * return the number of distinct subsequences of s which equals t.
 *
 * A string's subsequence is a new string formed from the original string
 * by deleting some (can be none) of the characters without disturbing the
 * relative positions of the remaining characters.
 *
 * (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 */
public class DistinctSubsequences {
    // Using memoization: O(n^3)
    public static int numDistinct(String s, String t) {
        int n = t.length(), m = s.length();

        if(m == 0) return 0;
        if(n == 0) return 1;

        int[][] dp = new int[n][m];

        // Base case: check how many ways of starting the row we have.
        for(int i = 0; i < m; i++) dp[0][i] = s.charAt(i) == t.charAt(0) ? 1 : 0;

        // Memoization step
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(s.charAt(j) == t.charAt(i)) {
                    int max = 0;
                    for(int k = 0; k < j; k++) max += dp[i-1][k];
                    dp[i][j] = max;
                }
            }
        }

        int result = 0;
        for(int i = 0; i < m; i++) result += dp[n-1][i];

        return result;
    }

    // Using memoization: O(n^2)
    public static int optimalNumDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        if(n == 0) return 0;
        if(m == 1) return 1;

        int dp[][] = new int[n+1][1+m];

        // If s.length > 0 and t.length == 0,
        // there is one combination: delete everything from s.
        for(int i = 0; i <=n; i++) dp[i][0] = 1;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(s.charAt(i-1) == t.charAt(j-1))
                    dp[i][j] = dp[i-1][j]+dp[i-1][j-1]; // If they match, sum the prev combo
                else
                    dp[i][j] = dp[i-1][j]; // If not take the prev match
            }
        }

        return dp[n][m];
    }

    // Using recursion: O(2^n)
    public static int findSubsequences(String s, int i, String t, int j) {
        int combinations = 0;

        // If we traversed t, then we found a combination
        if(j == t.length()) return 1;
        // If we traversed all s and not t, then we didn't find a combination
        if(i == s.length()) return 0;

        // Case 1: characters match
        if(s.charAt(i) == t.charAt(j)) {
            int combo = findSubsequences(s, i+1, t, j+1);
            combo += findSubsequences(s, i+1, t, j);
            if(combo > combinations) combinations = combo;
        }
        // Case 2: characters don't match
        else {
            int combo = findSubsequences(s, i+1, t, j);
            if(combo > combinations) combinations = combo;
        }
        return combinations;
    }

}
