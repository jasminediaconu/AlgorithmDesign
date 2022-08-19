package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

public class PalindromicSubstring {
    public static String longestPalindrome(String s) {
        int n = s.length();
        char[] characters = s.toCharArray();

        boolean p[][] = new boolean[n][n];

        // Base Case 1: Fill in diagonal
        for(int i = 0; i < n; i ++) p[i][i] = true;

        int start = 0;
        int end = 1;

        // Base Case 2: check if two consecutive characters are the same
        for(int i = 0; i < n-1; i++) {
            if(characters[i] == characters[i+1]){
                p[i][i+1] = true;
                start = i;
                end = 2;
            }
        }

        // If substring is of length k, with k > 2
        for(int k = 1; k < n; k++) {
            for(int i = 0; i < n-k; i++) {
                int j = i + k;

                // if prev and this is true then put p(i,j) = true
                if(p[i+1][j-1] && characters[i] == characters[j]) {
                    p[i][j] = true;

                    if(k+1 > end) {
                        start = i;
                        end = k+1;
                    }
                }

            }
        }

        return s.substring(start, start + end);
    }
}
