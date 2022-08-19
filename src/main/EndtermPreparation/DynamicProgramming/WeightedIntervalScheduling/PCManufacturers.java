package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class PCManufacturers {
    public static int findPerfectSupply(int n, int[] supply,  int r, int c) {
        int mem[] = new int[n+1];

        /**
         * Like Fish salesman and bitcoin change, with a small variation.
         */
        for(int i = 1; i <= n; i++) {
            if(i < 4) mem[i] = Math.min(mem[i-1] + r * supply[i], 4 * c);
            else mem[i] = Math.min(mem[i-1] + r * supply[i], (4 * c) + mem[i-4]);
        }
        return mem[n];
    }
}
