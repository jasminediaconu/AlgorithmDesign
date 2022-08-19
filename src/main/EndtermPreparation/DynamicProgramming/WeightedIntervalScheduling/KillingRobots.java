package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class KillingRobots {

    public static int findMaxKills(int n, int[] f, int[] robots) {
        int mem[] = new int[n+1];

        /**
         * 1. 2 nested loops: first one to keep track of the index, second to find optimal solution
         * 2. Check whether to take the previous kills + the kills I have available now
         *    or to use the max power I have right now
         */
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                mem[i] = Math.max(mem[j] + Math.min(f[i - j], robots[i]), mem[i]);
            }
        }

        return mem[n];
    }
}
