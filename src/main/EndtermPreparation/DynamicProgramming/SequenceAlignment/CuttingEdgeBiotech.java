package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CuttingEdgeBiotech {

//    Set M[0]= 0
//    For all pairs 1≤ t ≤ j ≤ m
//      Compute the cost c(t, j) as follows:
//      For each string B ∈ L
//          Compute the optimal alignment of B with A[t : j]
//      Endfor
//
//      Choose the B that achieves the best alignment, and use this alignment cost as c(t, j)
//    Endfor
//
//    For j = 1, 2, . . . , n
//      Use the recurrence (6.37) to compute M[j]
//    Endfor
//    Return M[n]


    /**
     * I DON'T KNOW HOW TO DO THIS.
     * @param text
     * @param words
     * @return
     */

    public static int minCost(String text, String[] words) {
        if (text.length() == 0) return 0;

        Map<String, List<String>> result = findSubsequences(text, words, new HashMap<>());

        result.size();

        return -1;
    }

    public static Map<String, List<String>> findSubsequences(String s, String[] wordDict, Map<String, List<String>> mem) {
        if(mem.containsKey(s)) return mem;

        ArrayList<String> results = new ArrayList<>();

        // Base case
        if(s.length() == 0) return mem;

        for(String word : wordDict) {
            if(s.contains(word)) {
                // Solve subproblems
                String sub = s.replaceAll(word, "");
                findSubsequences(sub, wordDict, mem);
            }
        }

        mem.put(s, results);

        return mem;
    }
}
