package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

/**
 * A gene string can be represented by an 8-character long string,
 * with choices from "A", "C", "G", "T".
 *
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"),
 * where ONE mutation is defined as ONE single character changed in the gene string.
 *
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 *
 * Also, there is a given gene "bank", which records all the valid gene mutations.
 * A gene must be in the bank to make it a valid gene string.
 *
 * Now, given 3 things - start, end, bank, your task is to determine what is the
 * minimum number of mutations needed to mutate from "start" to "end".
 * If there is no such a mutation, return -1.
 *
 * Note:
 * Starting point is assumed to be valid, so it might not be included in the bank.
 * If multiple mutations are needed, all mutations during in the sequence must be valid.
 * You may assume start and end string is not the same.
 */
public class MinimumGeneticMutation {
    public static int minMutation(String start, String end, String[] bank) {
        int n = start.length();
        int m = end.length();

        if(bank.length == 0) return -1;

        int dp[][] = new int[n+1][m+1];

        // Initialize first row
        for(int i = 0; i <= m; i++) dp[0][i] = i;

        // Initialize first column
        for(int i = 0; i <= n; i++) dp[i][0] = i;

        // Use memoization
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                // Check if the characters are equivalent to apply penalty
                if(start.charAt(i-1) == end.charAt(j-1))
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1));
                else {
                    String sub = start.substring(0, j) + end.substring(j+1, m);
                    boolean isValid = false;
                    for(int k = 0; k < bank.length; k++) {
                        if(bank[k].equals(sub) || sub.equals(start)) {
                            isValid = true;
                            break;
                        }
                    }

                    if(!isValid) return -1;

                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }
        return dp[n][m];
    }
}
