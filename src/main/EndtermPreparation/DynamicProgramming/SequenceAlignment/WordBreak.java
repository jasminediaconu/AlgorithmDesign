package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

import java.lang.reflect.Array;
import java.util.*;

public class WordBreak {
    /**
     * Given a non-empty string s and a dictionary wordDict containing a list
     * of non-empty words, determine if s can be segmented into a space-separated
     * sequence of one or more dictionary words.
     *
     * Note:
     * The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words.
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        int m = wordDict.size();

        if (n == 0 || m == 0) return false;

        // Create a HashSet
        HashSet<String> dictionary = new HashSet<>();
        for(String s1 : wordDict) dictionary.add(s1);

        boolean[][] mem = new boolean[n][n];

        for(int i = 0; i < n; i++) mem[0][i] = dictionary.contains(s.substring(0, i+1));

        for(int i = 1; i < n; i++) {
            for(int j = i; j < n; j++) {
                String subString = s.substring(i, j+1);

                if(dictionary.contains(subString) && mem[i-1][i-1]) mem[i][j] = true;

                else mem[i][j] = mem[i-1][j];

            }
        }

        return mem[n-1][n-1];
    }

    // Finds one of the possible solutions
    public static List<String> findPath(String s, List<String> wordDict) {
        // Get the matrix
        boolean[][] mem = findMatrix(s, wordDict);

        int i = mem.length-1;
        int j = i;

        boolean current = mem[i][j];

        List<String> words = new ArrayList<>();

        while(i >= 0) {
            if(i == 0) {
                words.add(s.substring(i, j+1));
                break;
            }
            if(mem[i-1][j]) i--;
            else {
                words.add(s.substring(i, j+1));
                int prev = j - i + 1;
                i--;
                j -= prev;
                current = mem[i][j];
            }
        }

        return words;
    }

    public static boolean[][] findMatrix(String s, List<String> wordDict) {
        int n = s.length();
        int m = wordDict.size();

        if (n == 0 || m == 0) return new boolean[][] {{false}};

        // Create a HashSet
        HashSet<String> dictionary = new HashSet<>();
        for(String s1 : wordDict) dictionary.add(s1);

        boolean[][] mem = new boolean[n][n];

        for(int i = 0; i < n; i++) mem[0][i] = dictionary.contains(s.substring(0, i+1));

        for(int i = 1; i < n; i++) {
            for(int j = i; j < n; j++) {
                String subString = s.substring(i, j+1);

                if(dictionary.contains(subString) && mem[i-1][i-1]) mem[i][j] = true;

                else mem[i][j] = mem[i-1][j];

            }
        }

        return mem;
    }

    /**
     * Given a non-empty string s and a dictionary wordDict containing a list
     * of non-empty words, add spaces in s to construct a sentence where each
     * word is a valid dictionary word. Return all such possible sentences.
     *
     * Note:
     *
     * The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words.
     */
    public static List<String> wordBreak2(String s, List<String> wordDict) {
        return wordBreakHelper(s, wordDict, new HashMap<>());
    }

    public static List<String> wordBreakHelper(String s, List<String> wordDict, Map<String, List<String>> mem) {
        if(mem.containsKey(s)) return mem.get(s);

        ArrayList<String> results = new ArrayList<>();

        // Base case
        if(s.length() == 0) {
            results.add("");
            return results;
        }

        for(String word : wordDict) {
            if(s.startsWith(word)) {
                // Solve subproblems
                String sub = s.substring(word.length());
                List<String> subStrings = wordBreakHelper(sub, wordDict, mem);

                for(String subString : subStrings) {
                    String space = subString.isEmpty() ? "" : " ";
                    results.add(word + space + subString);
                }

            }
        }

        mem.put(s, results);

        return results;
    }
}
