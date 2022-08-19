package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class AstronomyDepartment {
    public static int optimalEvents(int n, int[] coordinates) {

        /**
         * 1. Initialize position one with 1
         *    (otherwise the if condition might not always hold when compared with 0)
         * 2. Go through the array with two nested loops
         * 3. The first loop is for the index
         * 4. The second one computes the optimal value till the index
         * 5. In order to update the optimal value, we need to check whether
         *    the abs distance between coordinate i and j is less or equal to i - j
         */

        int mem[] = new int[n+1];

        if(n >= 1) mem[1] = 1;

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                // If this condition is satisfied then add to the optimal solution
                if(Math.abs(coordinates[i] - coordinates[j]) <= (i - j)) mem[i] = mem[j] + 1;
            }
        }

        return mem[n];
    }
}
