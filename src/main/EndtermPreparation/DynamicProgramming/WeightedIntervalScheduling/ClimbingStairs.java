package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class ClimbingStairs {
    public int climbStairs(int n) {
        if(n == 0) return 0;

        int mem[] = new int[n];

        mem[0] = 1;

        if(n > 1) mem[1] = 2;

        for (int i = 2; i < n; i++)
            mem[i] = mem[i-1] + mem[i-2];

        return mem[n-1];
//        return findCombinations(mem, n);
    }

//    public static int findCombinations(int[] mem, int n) {
//        if(n == 1) mem[0] = 1;
//        if(n == 2) mem[1] = 2;
//    }
}
