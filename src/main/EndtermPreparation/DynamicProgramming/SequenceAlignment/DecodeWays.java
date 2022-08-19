package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

import java.util.HashMap;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "111" can have each of its "1"s be mapped into 'A's to make "AAA", or it could be mapped to "11" and "1" ('K' and 'A' respectively) to make "KA". Note that "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a non-empty string num containing only digits, return the number of ways to decode it.
 *
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 *
 */
public class DecodeWays {
    public static int numDecodings(String s) {
        int n = s.length();

        HashMap<Integer, Character> encodings = new HashMap<>();

        // Building up a HashMap for each letter
        for (int i = 0; i < 26; i++) encodings.put(i+1, (char)('A' + i));

        // Base cases
        Integer key = Integer.parseInt(String.valueOf(s.charAt(0)));
        if(n == 0 || (n == 1 && !encodings.containsKey(key))) return 0;
        if(n == 1 && encodings.containsKey(key)) return 1;

        // Building up dp array
        int dp[] = new int[n+1];

        return findCombinations(s, 0, encodings, dp);
    }

    public static int findCombinations(String s, int index, HashMap<Integer, Character> encodings, int[] dp) {
        int result = 0;

        int n = s.length();
        // We found the solution
        if(index == n || index == n - 1) return 1;

        // If the element is not in the hashmap, then the encoding is invalid
        if(!encodings.containsKey(Integer.parseInt(String.valueOf(s.charAt(index))))) return 0;

        if(dp[index] > 0) return dp[index];

        result = findCombinations(s, index+1, encodings, dp);

        if(index+1 != n) {
            String twoDig = s.substring(index, index + 2);
            Integer twoDigits = Integer.parseInt(twoDig);
            if (encodings.containsKey(twoDigits)) {
                result += findCombinations(s, index + 2, encodings, dp);
            }
        }

        dp[index] = result;

        return result;
    }
}
