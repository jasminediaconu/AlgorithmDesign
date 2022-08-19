package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class JumpsToReachEnd {
    public static int minimumJumps(int[] arr) {
        int n = arr.length;
        
        int jumps[] = new int[n];

        for (int i = 0; i < n; i++) jumps[i] = Integer.MAX_VALUE;

        jumps[0] = 0;
        for(int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                jumps[j] = Math.min(jumps[j], jumps[i] + 1);
            }
        }
        
        return jumps[n-1];
    }
}
