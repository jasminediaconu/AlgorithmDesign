package main.Exams.Endterms.Endterm2019.FindSolution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DynamicProgramming {
    /**
     *   You should implement this method. Note that your solution should be _iterative_!
     *   @param n The number of updates to ship.
     *   @param c The cost of shipping one bundle of updates.
     *   @param costs The costs matrix of dimension (n+1)*(n+1), where costs[i][j] denotes the costs of bundling updates i,i+1,...,j; given for all 1 <= i <= j <= n
     *   @return The minimal costs of bundling.
     */
    public static int solve(int n, int c, int[][] costs) {
        int mem[] = new int[n+1];

        for(int i = 1; i <= n; i++) mem[i] = c + costs[1][i];

        for(int i = 2; i <= n; i++) {
            for(int j = i; j <= n; j++) {
                mem[j] = Math.min(mem[j], mem[i-1] + c + costs[i][j]);
            }
        }

        return mem[n];
    }

    /**
     *   You should implement this method.
     *   @param n The number of updates
     *   @param C the (monthly) cost budget
     *   @param benefits An array of dimension n+1 containing the benefits of all the code changes for 1 <= i <= n
     *   @param costs An array of dimension n+1 containing the costs of all the code changes for 1 <= i <= n
     *   @param M The memory array of dimension (n+1)*(C+1) filled based on the recursive formula.
     *   @return A list of all indices of the updates that should be included, in _increasing_ order.
     */
    public static List<Integer> solve(int n, int C, int[] benefits, int[] costs, int[][] M) {
        if(n == 0) return new ArrayList<>();

        LinkedList<Integer> updates = new LinkedList<>();

        int i = n;
        int j = C;

        int max = M[n][C];

        while(i > 0) {
            // If costs are less than j, then you wil get an IndexOutOfBounds Exception
            if(costs[i] <= j && M[i-1][j-costs[i]] == max - benefits[i]) {
                updates.addFirst(i);
                max -= benefits[i];
                j -= costs[i];
            }

            i--;
        }

        return updates;
    }
}