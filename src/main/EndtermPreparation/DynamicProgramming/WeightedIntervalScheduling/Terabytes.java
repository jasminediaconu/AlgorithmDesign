package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class Terabytes {
    public static int findOptimalTerabytes(int n, int[] x, int[] s) {
        // Ex. 9 - Look up in the book, I don't quite understand this solution
        int mem[][] = new int[n+1][n+1];

        for(int i = 1; i <= n; i++) mem[n][i] = Math.min(x[n], s[i]);

        for(int i = n-1; i > 0; i--) {
            for(int j = 1; j <= i; j++) {
                mem[i][j] = Math.max(mem[i+1][1], Math.min(x[i], s[j]) +  mem[i+1][j+1]);
            }
        }

        return mem[1][1];
    }
}
