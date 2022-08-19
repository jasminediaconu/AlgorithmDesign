package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

import java.util.*;

public class WordSegmentation {
    /**
     * Given an input text, you have to split into words according to the ones available in the words array, in order
     * to get the highest value possible.
     * @param text the text to be split
     * @param words the availlable words
     * @param values the values for each word
     * @return
     */
    public static int wordSegmentation(String text, String[] words, int[] values) {

        if(text.length() == 0) return 0;

        int t = text.length();
        HashMap<String, Integer> splits = new HashMap<>();
        // Pair words with values so that it is easier to retrive the value for each key
        for(int i = 0; i < words.length; i++) splits.put(words[i], values[i]);

        List<String> combinations = wordSegmentationHelper(text, words, new HashMap());

        int n = combinations.size();

        // No combionation possible with the given words
        if(n == 0) return -1;

        int mem[] = new int[n];

        for(int i = 0; i < n; i++) {
            String[] split = combinations.get(i).split(" ");

            int value = 0;
            // Compute the value of the string
            for(String word : split) value += splits.get(word);

            if(i == 0) mem[i] = value;
            else mem[i] = Math.max(mem[i-1], value);
        }

        return mem[n-1];
    }

    public static List<String> wordSegmentationHelper(String text, String[] words, HashMap<String, List<String>> mem) {
        if(mem.containsKey(text)) return mem.get(text);

        ArrayList<String> results = new ArrayList<>();

        // Base case
        if(text.length() == 0) {
            results.add("");
            return results;
        }

        for(String word : words) {
            if(text.startsWith(word)) {
                // Solve subproblems
                String sub = text.substring(word.length());
                List<String> subStrings = wordSegmentationHelper(sub, words, mem);

                for(String subString : subStrings) {
                    String space = subString.isEmpty() ? "" : " ";
                    results.add(word + space + subString);
                }

            }
        }

        mem.put(text, results);

        return results;
    }



    public static List<String> wordBreak(String wordToBreak, List<String> wordDict) {
        int t = wordToBreak.length();
        return findSolutions(wordToBreak, wordDict, new HashMap<>());
    }

    public static List<String> findSolutions(String wordToBreak, List<String> wordDict, Map<String, List<String>> wordBrokenDict){
        if(wordBrokenDict.containsKey(wordToBreak)){
            return wordBrokenDict.get(wordToBreak);
        }

        List<String> solutions = new ArrayList<>();
        for(String word: wordDict){
            if(!wordToBreak.startsWith(word)){
                continue;
            }

            if(wordToBreak.length() == word.length()){
                solutions.add(word);
                continue;
            }

            List<String> subSolutions = findSolutions(wordToBreak.substring(word.length()), wordDict, wordBrokenDict);
            for(String subSolution: subSolutions) {
                StringBuilder strBuilder = new StringBuilder();
                strBuilder.append(word).append(' ').append(subSolution);
                solutions.add(strBuilder.toString());
            }
        }

        wordBrokenDict.put(wordToBreak, solutions);
        return solutions;
    }
}
