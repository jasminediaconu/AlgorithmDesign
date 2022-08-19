package main.FinalExam.Question2;

import java.util.HashSet;
import java.util.Set;

public class RecoverYourItems {

    /**
     *  You should implement this method.
     *
     *  @param n   the number of items n.
     *  @param m   the maximum weight.
     *  @param v   the array containing the weights of the items, in index 1 through n. Note this means you should ignore v[0] and use v[1] through v[n].
     *  @param mem the memory filled by the dynamic programming algorithm using the provided recursive formulation.
     *  @return the set of indices of items that together form the optimal solution.
     */
    public static Set<Integer> solve(int n, int m, int[] v, int[][] mem) {
        int i = n;
        int j = 0;

        Set result = new HashSet();


        while(i > 0) {
            // Check if the matrix value corresponds, if it does then the item is in the optimal solution set
            if(v[i] + j < m && (mem[i-1][j+v[i]] * v[i]) == mem[i][j]) {
                result.add(i);
                j += v[i];
            }
            // Whether or not you picked up the item, go to the previous item
            i--;
        }

        // We need an extra condition when we reach i = 0 to decide whether to take it or not
        if(i == 0 && m - j > 0 ) {
            result.add(mem[i][j]);
        }

        return result;
    }
}
