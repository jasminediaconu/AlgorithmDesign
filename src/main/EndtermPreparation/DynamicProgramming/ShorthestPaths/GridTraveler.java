package main.EndtermPreparation.DynamicProgramming.ShorthestPaths;

/**
 * Given a grid, find the total number of moves to get from the top left corner
 * to the bottom right.
 */
public class GridTraveler {

    public static int findMoves(int n, int m, int[][] mem) {
        // Base cases
        if(n == 0 || m == 0) return 0;
        if(n == 1 && m == 1) return 1;

        for(int i = 0; i < n; i++) mem[i][0] = 1;
        for(int i = 0; i < m; i++) mem[0][i] = 1;

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                mem[i][j] = mem[i-1][j] + mem[i][j-1];
            }
        }

        return mem[n-1][m-1];
    }

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        // Base cases
        if(n == 0 || m == 0) return 0;
        if(n == 1 && m == 1) return grid[0][0];

        for(int i = 1; i < n; i++) grid[i][0] += grid[i-1][0];
        for(int i = 1; i < m; i++) grid[0][i] += grid[0][i-1];

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }

        return grid[n-1][m-1];
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        return findPaths(n, m, obstacleGrid, new int[n][m]);
    }

    public static int findPaths(int n, int m, int[][] obstacleGrid, int[][] mem) {
        // Base cases
        if(n == 0 || m == 0) return 0;
        if(n == 1 && m == 1) return obstacleGrid[n-1][m-1] == 1 ? 0 : 1;

        mem[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;

        for(int i = 1; i < n; i++) mem[i][0] = obstacleGrid[i][0] == 1 || mem[i-1][0] == 0 ? 0 : 1;
        for(int i = 1; i < m; i++) mem[0][i] = obstacleGrid[0][i] == 1 || mem[0][i-1] == 0 ? 0 : 1;

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(obstacleGrid[i][j] == 1) mem[i][j] = 0;
                else mem[i][j] = mem[i-1][j] + mem[i][j-1];
            }
        }

        return mem[n-1][m-1];
    }

    // TODO: Finish this
    public static int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;

        if (n == 0 || m == 0) return 0;

        int mem[][] = new int[n][m];

        mem[0][0] = dungeon[0][0] > 0 ? dungeon[0][0] : - dungeon[0][0] + 1;

        for(int i = 1; i < n; i++) {
            if(dungeon[i][0] > 0) mem[i][0] = mem[i-1][0];
            else if(mem[i-1][0] + dungeon[i][0] > 0) mem[i][0] = mem[i-1][0];
            else mem[i][0] = mem[0][i-1] - (mem[i-1][0] + dungeon[i][0]) + 1;
        }

        for(int i = 1; i < m; i++) {
            if(dungeon[0][i] > 0) mem[0][i] = mem[0][i-1];
            else if(mem[0][i-1] + dungeon[0][i] > 0) mem[0][i] = mem[0][i-1];
            else mem[0][i] = mem[0][i-1] - (mem[0][i-1] + dungeon[0][i]) + 1;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(dungeon[i][j] > 0) mem[i][j] = Math.min(mem[i-1][j], mem[i][j-1]);

            }
        }

        return 0;
    }

    // TODO: Finish this
    public static int uniquePathsIII(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        if (n == 0 || m == 0) return 0;

        int mem[][] = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) mem[i][j] = 1;
                if(grid[i][j] == -1) mem[i][j] = -Integer.MAX_VALUE/2;
                mem[i][j] = Integer.MAX_VALUE/2;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {



            }
        }
        return 0;
    }
}
