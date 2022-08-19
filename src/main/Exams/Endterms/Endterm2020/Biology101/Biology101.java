package main.Exams.Endterms.Endterm2020.Biology101;

public class Biology101 {
    /**
     *  You should implement this method.
     *  @param n the number of bases.
     *  @param b an array of size n+1, containing the bases b_1 through b_n. Note that you should use b[1] through b[n]
     *  @param p an array of size n+1 by n+1, containing the validity for pair (i,j). A pair (i,j) is valid iff p[i][j] == true. Note that you should use p[1][1] through p[n][n].
     *  @return The size of the largest set of base pairs.
     */
    public static int solve(int n, char[] b, boolean[][] p) {
        int mem[][] = new int[n+1][n+1];

        for(int k = 5; k <= n; k++) {
            for(int i = 1; i <= n-4; i++) {
                if(i > k) continue;

                int max = 0;

                for(int t = i; t < k-4; t++) {
                    if(p[i][k]) max = Math.max(max, 1 + mem[i][t-1] + mem[t+1][k-1]);
                }

                mem[i][k] = Math.max(mem[i][k-1], max);
            }
        }
        return mem[1][n];
    }
}
