package main.EndtermPreparation.DynamicProgramming.SequenceAlignment;

public class DynamicTimeWarping {
    public static int findMinDistance(int first[], int second[]) {
        int n = first.length;
        int m = second.length;

        int result[][] = new int[n][m];

        // Initialization
        for(int i = 0; i < n; i++) result[i][0] = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) result[0][i] = Integer.MAX_VALUE;

        result[0][0] = 0;

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                result[i][j] = Math.abs(first[i] - second[j]) +
                        Math.min(Math.min(result[i-1][j], result[i-1][j-1]), (result[i][j-1]));
            }
        }

        return result[n-1][m-1];
    }
}
