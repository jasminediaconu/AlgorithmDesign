package main.Exams.Endterms.Resit2019.DynamicProgramming;

public class AntiqueRoadshow {
    public static /**
     *  You should implement this method.
     *  @param n the number of items.
     *  @param v an array of size n+1, containing the values v_1 through v_n. You should ignore v[0].
     *  @param skip an array of size n+1, containing the values skip(1) through skip(n). You should ignore skip[0].
     *  @return The maximum value obtainable from these jobs.
     */
    int solve(int n, int[] v, int[] skip) {
        int mem[] = new int[n+1];

        // Decide whether to take the previous or the one before the skips + current value
        for(int i = 1; i <=n; i++) mem[i] = Math.max(mem[i-1], v[i] + mem[i - skip[i] - 1]);

        return mem[n];
    }
}
