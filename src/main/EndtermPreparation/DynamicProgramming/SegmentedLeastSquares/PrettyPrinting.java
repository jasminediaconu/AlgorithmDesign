package main.EndtermPreparation.DynamicProgramming.SegmentedLeastSquares;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;

public class PrettyPrinting {
    public static List<String> solve(InputStream in) {
        Scanner sc = new Scanner(in);

        int L = sc.nextInt();
        int n = sc.nextInt();

        String[] text = new String[n];

        for(int i = 0; i < n; i++) text[i] = sc.next();

        sc.close();

        int infinity = Integer.MAX_VALUE;
        // Build up S matrix
        int[][] S = new int[n][n];

        for(int i = 0; i < n; i++) {
            // Compute sum
            int sum = -1;
            for(int j = i; j < n; j++) {
                sum += text[j].length() + 1;
                // If this is the first letter of the row check criteria
                if(sum <= L) S[i][j] = (int) Math.pow(L - sum, 2);
                    // If doesn't fit on the row, add infinity
                else S[i][j] = infinity;
            }
        }

        // Now that I have the matrix, I need to decide how to put the words on each line
        // such that I get the optimal solution
        int mem[] = new int[n];
        int idx[] = new int[n];
        for(int i = 1; i < n; i++) mem[i] = infinity;

        for(int i = 1; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(S[i-1][j-1] == infinity) continue;
                if(mem[j] > mem[i-1] + S[i-1][j-1]) {
                    mem[j] = mem[i-1] + S[i-1][j-1];
                    idx[i] = j;
                }
            }
        }

        LinkedList<String> result = new LinkedList<>();

        // Non recursive solution
        while(n > 0) {
            int index = 1;
            int current = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                if (S[i-1][n-1] == Integer.MAX_VALUE) continue;

                // As before, but we keep track of the index this time
                if (S[i-1][n-1] + mem[i-1] < current) {
                    current = S[i-1][n-1] + mem[i-1];
                    index = i;
                }
            }

            result.addFirst(String.join(" ", Arrays.asList(text).subList(index - 1, n)));
            n = index - 1;
        }

        return result;
    }

    private static List<String> getLines(int n, int[] mem, ArrayList<Integer> S, String[] text) {
        ArrayList<String> results = new ArrayList<>();

        // Base case: no words
        if(n == 0) return results;

        int index = 1;

        for(int i = 0; i < n; i++) {

        }


        return results;
    }
}
