package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

import java.util.ArrayList;
import java.util.List;

/**
 * The sequence alignment problem aims to find the optimal alignment of two strings such that
 * the mismatch penalty is minimized. For each mismatched character, you are allowed to add
 * a penalty of ‘1’.
 *
 * You are given two strings, of lengths n and m respectively, on two separate rows.
 * The lengths of the strings can be different. The given strings are also entirely lower case,
 * you do not have to re-format the given input to account for that.
 * Your algorithm must calculated the minimum sequence alignment cost, and return it as an integer.
 * An example input is given below.
 */
public class AligningSequences {

    // Memoization solution: Theta(nm) time & space.
    public static int solve(String firstString, String secondString) {
        int n = firstString.length();
        int m = secondString.length();

        int dp[][] = new int[n+1][m+1];

        // Initialize first row
        for(int i = 0; i <= m; i++) dp[0][i] = i;

        // Initialize first column
        for(int i = 0; i <= n; i++) dp[i][0] = i;

        // Use memoization
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                // Check if the characters are equivalent to apply penalty
                if(firstString.charAt(i-1) == secondString.charAt(j-1))
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1));
                else dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;

                // Formula: min(dp[i-1][j-1] + penalty, dp[i-1][j] + 1, dp[i][j-1] + 1).
            }
        }

        return dp[n][m];
    }

    // Memoization solution: Theta(nm) time & O(m) space.
    public static int spaceEfficientAlignment(String firstString, String secondString) {
        // We need to take the second string as longest
        // because we need to create the array based on the longest string
        if(firstString.length() > secondString.length()) {
            String temp = firstString;
            firstString = secondString;
            secondString = temp;
        }

        int n = firstString.length();
        int m = secondString.length();

        // Assuming that m is always the longest string
        int mem[][] = new int[m+1][2];

        // Initialize first array
        for(int i = 1; i <= m; i++) mem[i][0] = i;

        for(int j = 1; j <= n; j++) {
            mem[0][1] = j;

            for(int i = 1; i <= m; i++) {
                if(firstString.charAt(j-1) == secondString.charAt(i-1))
                    mem[i][1] = Math.min(mem[i-1][0], Math.min(mem[i-1][1] + 1, mem[i][0] + 1));
                else
                    mem[i][1] = Math.min(mem[i-1][0], Math.min(mem[i-1][1], mem[i][0])) + 1;
            }

            // Move optimal solution to 1st array
            for(int i = 0; i <= m; i++) mem[i][0] = mem[i][1];
        }

        return mem[m][1];
    }

    // Memoization solution: Theta(nm) time & O(m) space.
    public static int[] spaceEfficientAlignmentArray(String firstString, String secondString) {
        int n = firstString.length();
        int m = secondString.length();

        // Assuming that m is always the longest string
        int mem[][] = new int[2][n+1];

        // Initialize first array
        for(int i = 1; i <= n; i++) mem[0][i] = i;

        for(int j = 1; j <= m; j++) {
            mem[1][0] = j;

            for(int i = 1; i <= n; i++) {
                if(firstString.charAt(i-1) == secondString.charAt(j-1))
                    mem[1][i] = Math.min(mem[0][i-1], Math.min(mem[1][i-1] + 1, mem[0][i] + 1));
                else
                    mem[1][i] = Math.min(mem[0][i-1], Math.min(mem[1][i-1], mem[0][i])) + 1;
            }

            // Move optimal solution to 1st array
            for(int i = 0; i <= n; i++) mem[0][i] = mem[1][i];
        }

        return mem[1];
    }

    // Retrieves the alignment of the two strings -> BROKEN
    public static List<Integer> findAlignment(String firstString, String secondString) {
        /**
         * Divide-and-Conquer-Alignment(X,Y)
         * Let m be the number of symbols in X
         * Let n be the number of symbols in Y
         * If m ≤ 2 or n ≤ 2 then
         * Compute optimal alignment using Alignment(X,Y)
         * Call Space-Efficient-Alignment(X,Y[1 : n/2])
         * Call Backward-Space-Efficient-Alignment(X,Y[n/2 + 1 : n])
         * Let q be the index minimizing f(q, n/2) + g(q, n/2)
         * Add (q, n/2) to global list P
         * Divide-and-Conquer-Alignment(X[1 : q],Y[1 : n/2])
         * Divide-and-Conquer-Alignment(X[q + 1 : n],Y[n/2 + 1 : n])
         * Return P
         */
        int n = firstString.length();
        int m = secondString.length();

        ArrayList<Integer> p = new ArrayList<>();

        // Base case, we use the old algorithm
        if(n <= 2 || m <= 2) {
            p.add(solve(firstString, secondString));
            return p;
        }

        // Get back the alignment
        int f[] = spaceEfficientAlignmentArray(firstString, secondString.substring(0, m/2+1));
        int g[] = spaceEfficientAlignmentArray(firstString, secondString.substring(m/2, m));

        int index = 0;

        int minSum = Integer.MAX_VALUE;

        // Find the index of the correct alignment
        for(int i = 0; i < f.length; i++) {
            if(f[i] + g[i] < minSum) {
                minSum = f[i] + g[i];
                index = i;
            }
        }

        p.add(index);
        p.add(m/2);

        p.addAll(findAlignment(firstString.substring(0, index+1), secondString.substring(0, m/2+1)));
        p.addAll(findAlignment(firstString.substring(index, n), secondString.substring(m/2, m)));

        return p;
    }
}
