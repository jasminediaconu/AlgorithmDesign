package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

public class MaximumScoreWords {

    static int maxScore = 0;

    public static int maxScoreWords(String[] words, char[] letters, int[] score) {

        int frequency[] = new int[26];

        // Setting up a frequency array to store the amount of each letter available.
        for(int i = 0; i < letters.length; i++) frequency[letters[i] - 'a']++;

        findOptimalSubset(words, frequency, score, 0, 0);
        return maxScore;
    }

    public static boolean canBeUsed(char[] word, int[] frequency) {
        int[] wordFreq = new int[26];
        for(char c : word) {
            wordFreq[c-'a']++;
        }

        for(int i = 0; i < 26; i++) {
            if (wordFreq[i] > frequency[i]) return false;
        }
        return true;
    }

    public static void findOptimalSubset(String[] words, int[] frequency, int[] score,
                                  int index, int totalScore) {
        if(index == words.length) return;

        char[] word = words[index].toCharArray();
        int currentScore = totalScore;
        int[] copy = frequency.clone();

        if(canBeUsed(word, frequency)) {
            for(char c : word) {
                int idx = c - 'a';
                totalScore += score[idx];
                frequency[idx]--;
            }

            findOptimalSubset(words, frequency, score, index+1, totalScore);
        }

        if(maxScore < totalScore) maxScore = totalScore;

        findOptimalSubset(words, copy, score, index+1, currentScore);
    }
}
