package main.EndtermPreparation.DynamicProgramming.WeightedIntervalScheduling;

public class StressJobs {

    /**
     * If you are taking a high stress job, skip the previous week.
     * If you are taking a low stress job, take the max between
     * the prev low stress job and the prev high.
     * @param n
     * @param lowStress
     * @param highStress
     * @return
     */
    public static int findOptimalJobs(int n, int[] lowStress, int[] highStress) {
        int[][] mem = new int[n+1][3];

        for(int i = 1; i <= n; i++) {
            mem[i][0] = Math.max(mem[i-1][0], mem[i-1][2]) + lowStress[i];
            mem[i][1] = Math.max(mem[i-1][0], mem[i-1][2]);
            mem[i][2] = Math.max(mem[i-1][1] + highStress[i], mem[i-1][2]);
        }

        return Math.max(mem[n][0], mem[n][2]);
    }

    public static int findOptimalJobs2(int n, int[] lowStress, int[] highStress) {
        int[][] mem = new int[n+1][2];

        mem[1][0] = lowStress[1];
        mem[1][1] = highStress[1];
        for(int i = 2; i <= n; i++) {
            mem[i][0] = Math.max(mem[i-1][0], mem[i-1][1]) + lowStress[i];
            mem[i][1] = Math.max(mem[i-2][1], mem[i-2][1]) + highStress[i];
        }

        return Math.max(mem[n][0], mem[n][1]);
    }
}
