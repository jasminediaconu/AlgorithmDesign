package main.Exams.Endterms.Resit2019.FindSolution;

import java.util.ArrayList;
import java.util.List;

public class AuctionTime {
    /**
     *   You should implement this method.
     *   @param n the number of items.
     *   @param V the maximum value you can put up for auction.
     *   @param v an array of size n+1, containing the values v_1 through v_n. You should ignore v[0].
     *   @param M The memory array of dimension (n+1)*(V+1) filled based on the recursive formula.
     *   @return A list of all indices of the items that should be put up for auction, in _decreasing_ order.
     */
    public static List<Integer> solve(int n, int V, int[] v, int[][] M) {
        int i = n;
        int j = V;

        ArrayList<Integer> result = new ArrayList<>();

        while(i > 0) {
            if(v[i] <= j && M[i][j] == M[i-1][j-v[i]]) {
                result.add(i);
                j -= v[i];
            }

            i--;
        }

        return result;
    }
}
