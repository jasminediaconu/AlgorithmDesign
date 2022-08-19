package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

/**
 * Algorithm for calculating the length of the Longest Common Subsequence of two
 * strings s1 and s2.
 */
public class LongestCommonSubsequence {
    public static int LCSlength(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int table[][] = new int[n+1][m+1];

        // Initialize first row (representing s1)
        for(int i = 0; i <= n; i++) table[i][0] = 0;

        // Initialize first column (representing s2)
        for(int i = 0; i <= m; i++) table[0][i] = 0;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) table[i][j] = table[i-1][j-1] + 1;
                else table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
            }
        }

        return table[n][m];
    }
}
