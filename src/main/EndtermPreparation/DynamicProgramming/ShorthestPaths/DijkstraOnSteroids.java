package main.EndtermPreparation.DynamicProgramming.ShorthestPaths;

import java.util.Arrays;

/**
 *
 * You are playing a game and you are faced with a puzzle. In this puzzle,
 * you have to ﬁnd your way past a group of towers. Each tower has a different height,
 * and there is no way to get past it other than by using a ladder to climb to the top.
 * (From the top of the tower, you can then jump down or climb up to the next tower, \
 * or the ground if you are at the end.)
 *
 * The towers are arranged in a rectangular n x m grid and you can only move from a tower
 * to the one north, west, south or east of you. If you want to go up any height different N,
 * you will need a ladder of length N as well. If you want to go down, you can simply jump
 * without the use of a ladder. A ladder’s cost increases linearly with its length and
 * you need to get from the northwest tower to the southeast one. Of course, you want the
 * cheapest ladder possible, which means finding the minimum length of ladder needed.
 * You may assume you are dropped on top of the northwest tower at the start of the puzzle
 * and want to get to the southeast tower at the end of the puzzle.
 *
 * Given the following problem instance, of size 2 x 3:
 *
 * 3 5 6
 * 4 2 1
 *
 * we expect 1 as our output.
 *
 * Give an iterative dynamic solution to find the cheapest ladder you can use to
 * reach the southeasternmost tower.
 *
 * NB: Please keep in mind that you can take your ladder with you.
 * Thus if you go: 3 -> 4 -> 5 you still only require a ladder of length 1!
 *
 */
public class DijkstraOnSteroids {
    public static int solve(int n, int m, int[][] graph) {
        int[][] mem = new int[n][m];

        int infinity = Integer.MAX_VALUE/2;

         for(int i = 0; i < n; i++) {
             for(int j = 0; j < m; j++) {
                 mem[i][j] = infinity;
             }
         }

        mem[0][0] = 0;

        for(int k = 0; k < n * m; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(i == j && i == 0) continue;

                    // Find size of the ladder => left
                    mem[i][j] = j > 0 ? Math.max(mem[i][j-1], graph[i][j] - graph[i][j-1]) : mem[i][j];
                    // Find size of the ladder => right
                    mem[i][j] = j < m-1 ? Math.min(mem[i][j], Math.max(mem[i][j+1], graph[i][j] - graph[i][j+1])) : mem[i][j];
                    // Find size of the ladder => up
                    mem[i][j] = i > 0 ? Math.min(mem[i][j], Math.max(mem[i-1][j], graph[i][j] - graph[i-1][j])) : mem[i][j];
                    // Find size of the ladder => down
                    mem[i][j] = i < n-1 ? Math.min(mem[i][j], Math.max(mem[i+1][j], graph[i][j] - graph[i+1][j])) : mem[i][j];
                }
            }
        }

        return mem[n-1][m-1];
    }
}
