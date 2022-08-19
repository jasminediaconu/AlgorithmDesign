package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class LongestIncreasingSequence {

    /**
     * Find the longest increasing subsequence of numbers.
     * @param numbers
     * @return
     */
    public static int findMaxIncreasingSequence(int[] numbers) {
        int n = numbers.length;
        int mem[] = new int[n];

        for(int i = 0; i < n; i++) mem[i] = 1;

        int max = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(numbers[j] <= numbers[i]) mem[i] = Math.max(mem[i], mem[j] + 1);
                if(mem[i] > max) max = mem[i];
            }
        }

        return max;
    }

    public static int findMaxSumSequence(int[] numbers) {
        int n = numbers.length;
        int mem[] = new  int[n];

        for(int i = 0; i < n; i++) mem[i] = numbers[i];

        if(numbers[0] < 0) mem[0] = 0;

        int max = 0;

        for(int i = 1; i < n; i++) {
            if(mem[i-1] > 0) mem[i] += mem[i-1];
            max = Math.max(mem[i], max);
        }

        return max;
    }

    public static void main(String[] args) {
        int res = LongestIncreasingSequence.findMaxIncreasingSequence(new int[] { -1, 3, 3, 3, 4, 5, 2, 2, 2, 2 });
        System.out.println(res);

        int maxSum = LongestIncreasingSequence.findMaxSumSequence(new int[] { 2, 5, -3, 8, 1, -20, 1, 5});
        System.out.println("Max sum is: " + maxSum);
    }
}
